package com.fluxpay.admin.service;

import com.fluxpay.admin.dto.auth.LoginReqDTO;
import com.fluxpay.admin.dto.auth.LoginRespVO;

/**
 * 认证 Service
 */
public interface AuthService {

    /**
     * 登录，返回 Token 及用户信息
     */
    LoginRespVO login(LoginReqDTO req);

    /**
     * 登出：将当前 Access Token 加入 Redis 黑名单
     *
     * @param token 请求头中的 Access Token（不含 Bearer 前缀）
     */
    void logout(String token);

    /**
     * 使用 Refresh Token 换取新的 Access Token
     *
     * @param refreshToken Refresh Token
     * @return 新的 Access Token
     */
    String refreshToken(String refreshToken);
}
