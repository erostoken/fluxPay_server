package com.fluxpay.admin.domain.vo.resp.auth;

import lombok.Builder;
import lombok.Data;

/**
 * 登录响应
 */
@Data
@Builder
public class LoginResp {

    /** Access Token（有效期短，用于接口鉴权） */
    private String accessToken;

    /** Refresh Token（有效期长，用于换取新 Access Token） */
    private String refreshToken;

    /** Access Token 过期时间（秒） */
    private long expiresIn;
}
