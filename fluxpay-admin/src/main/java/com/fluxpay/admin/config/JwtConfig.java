package com.fluxpay.admin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT 参数配置，绑定 application.yaml 中 fluxpay.jwt.*
 */
@Data
@Component
@ConfigurationProperties(prefix = "fluxpay.jwt")
public class JwtConfig {

    /** HMAC-SHA256 签名密钥（Base64 编码，至少 32 字节）*/
    private String secret;

    /** Access Token 有效期（秒），默认 2 小时 */
    private long accessTokenExpire = 7200;

    /** Refresh Token 有效期（秒），默认 7 天 */
    private long refreshTokenExpire = 604800;
}
