package com.fluxpay.admin.security;

import com.fluxpay.admin.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * JWT 工具类（基于 JJWT 0.12.x API）
 */
@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final JwtConfig jwtConfig;

    /** Access Token 声明中的用户 ID 键 */
    private static final String CLAIM_USER_ID   = "userId";
    /** Access Token 声明中的用户名键 */
    private static final String CLAIM_USERNAME  = "username";
    /** Token 类型键 */
    private static final String CLAIM_TYPE      = "type";
    private static final String TYPE_ACCESS     = "access";
    private static final String TYPE_REFRESH    = "refresh";

    // ── 生成 Token ─────────────────────────────────────────────

    public String generateAccessToken(Long userId, String username) {
        return buildToken(userId, username, TYPE_ACCESS,
                jwtConfig.getAccessTokenExpire());
    }

    public String generateRefreshToken(Long userId, String username) {
        return buildToken(userId, username, TYPE_REFRESH,
                jwtConfig.getRefreshTokenExpire());
    }

    private String buildToken(Long userId, String username, String type, long expireSeconds) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expireSeconds * 1000);
        return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim(CLAIM_USER_ID, userId)
                .claim(CLAIM_USERNAME, username)
                .claim(CLAIM_TYPE, type)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(getKey())
                .compact();
    }

    // ── 解析 Token ─────────────────────────────────────────────

    /**
     * 解析 Token，返回 Claims；Token 无效或过期时抛出 JwtException
     */
    public Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * 校验 Token 是否有效（不抛异常，返回 boolean）
     */
    public boolean isValid(String token) {
        try {
            parseToken(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Long getUserId(String token) {
        return parseToken(token).get(CLAIM_USER_ID, Long.class);
    }

    public String getUsername(String token) {
        return parseToken(token).get(CLAIM_USERNAME, String.class);
    }

    /**
     * 获取 Token 剩余有效时间（毫秒），用于 Redis 黑名单 TTL
     */
    public long getRemainingMs(String token) {
        Date expiry = parseToken(token).getExpiration();
        return Math.max(0, expiry.getTime() - System.currentTimeMillis());
    }

    // ── 私有 ───────────────────────────────────────────────────

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(
                jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
    }
}
