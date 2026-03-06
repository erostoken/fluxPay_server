/**
 * 管理后台安全配置。
 *
 * <p>包含：
 * <ul>
 *   <li>{@code SecurityConfig} — Spring Security 过滤链、白名单路径、BCrypt 密码编码器</li>
 *   <li>{@code JwtConfig} — JWT 签名密钥、过期时间等参数绑定（{@code @ConfigurationProperties}）</li>
 *   <li>{@code WebMvcConfig} — 跨域（CORS）配置与全局拦截器注册</li>
 * </ul>
 */
package com.fluxpay.admin.config;
