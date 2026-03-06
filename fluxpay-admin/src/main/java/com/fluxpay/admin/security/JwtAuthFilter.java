package com.fluxpay.admin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fluxpay.common.result.Result;
import com.fluxpay.common.result.ResultCode;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * JWT 认证过滤器
 *
 * <p>每次请求执行一次：
 * <ol>
 *   <li>从 Authorization: Bearer &lt;token&gt; 提取 Token</li>
 *   <li>校验 Token 签名与过期时间</li>
 *   <li>检查 Redis 黑名单（登出后的 Token）</li>
 *   <li>将用户信息写入 SecurityContext</li>
 * </ol>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final String HEADER_NAME    = "Authorization";
    private static final String TOKEN_PREFIX   = "Bearer ";
    /** Redis 中 Token 黑名单的键前缀 */
    public  static final String BLACKLIST_KEY  = "jwt:blacklist:";

    private final JwtUtils            jwtUtils;
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper        objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = extractToken(request);

        // 无 Token 则直接放行，由 Spring Security 鉴权拦截未认证请求
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // 1. 验签 + 过期
            if (!jwtUtils.isValid(token)) {
                writeUnauthorized(response, ResultCode.TOKEN_INVALID);
                return;
            }

            // 2. Redis 黑名单检测（已登出的 Token 拒绝使用）
            Boolean inBlacklist = redisTemplate.hasKey(BLACKLIST_KEY + token);
            if (Boolean.TRUE.equals(inBlacklist)) {
                writeUnauthorized(response, ResultCode.TOKEN_INVALID);
                return;
            }

            // 3. 设置认证信息到 SecurityContext
            Long   userId   = jwtUtils.getUserId(token);
            String username = jwtUtils.getUsername(token);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
                    );
            // 将用户 ID 存储到 details，方便 Controller 层获取
            authentication.setDetails(userId);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException ex) {
            log.warn("JWT 解析失败: {}", ex.getMessage());
            writeUnauthorized(response, ResultCode.TOKEN_EXPIRED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    // ── 私有工具 ───────────────────────────────────────────────

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader(HEADER_NAME);
        if (StringUtils.hasText(header) && header.startsWith(TOKEN_PREFIX)) {
            return header.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    private void writeUnauthorized(HttpServletResponse response, ResultCode code) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String body = objectMapper.writeValueAsString(Result.fail(code));
        response.getWriter().write(body);
    }
}
