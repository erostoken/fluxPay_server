package com.fluxpay.admin.security;

import com.fluxpay.common.exception.BusinessException;
import com.fluxpay.common.result.ResultCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Security 上下文工具类
 *
 * <p>从 {@link SecurityContextHolder} 中提取当前登录用户信息，
 * 由 {@link JwtAuthFilter} 在认证成功时写入。
 */
public class SecurityUtils {

    private SecurityUtils() {}

    /**
     * 获取当前登录用户 ID
     *
     * @throws BusinessException 若未登录或上下文为空
     */
    public static Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || !(auth.getDetails() instanceof Long)) {
            throw new BusinessException(ResultCode.UNAUTHORIZED);
        }
        return (Long) auth.getDetails();
    }

    /**
     * 获取当前登录用户名
     */
    public static String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new BusinessException(ResultCode.UNAUTHORIZED);
        }
        return auth.getName();
    }
}
