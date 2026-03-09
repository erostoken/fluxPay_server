package com.fluxpay.admin.controller.auth;

import com.fluxpay.admin.domain.vo.req.auth.LoginReq;
import com.fluxpay.admin.domain.vo.resp.auth.LoginResp;
import com.fluxpay.admin.service.AuthService;
import com.fluxpay.common.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 认证接口（登录 / 登出 / 刷新 Token）
 *
 *
 * <pre>
 *   POST    /auth/login               登录
 *   POST    /auth/logout              登出
 *   POST    /auth/refresh             刷新 Access Token
 * </pre>
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 登录
     * POST /auth/login
     */
    @PostMapping("/login")
    public Result<LoginResp> login(@Valid @RequestBody LoginReq req) {
        return Result.ok(authService.login(req));
    }

    /**
     * 登出（将当前 Token 加入黑名单）
     * POST /auth/logout
     */
    @PostMapping("/logout")
    public Result<Void> logout(HttpServletRequest request) {
        String token = extractToken(request);
        authService.logout(token);
        return Result.ok();
    }

    /**
     * 刷新 Access Token
     * POST /auth/refresh
     */
    @PostMapping("/refresh")
    public Result<String> refresh(@RequestParam String refreshToken) {
        return Result.ok(authService.refreshToken(refreshToken));
    }

    // ── 工具 ───────────────────────────────────────────────────

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
