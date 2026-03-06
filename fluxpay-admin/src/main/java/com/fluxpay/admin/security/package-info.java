/**
 * 管理后台安全层。
 *
 * <p>包含：
 * <ul>
 *   <li>{@code JwtAuthFilter} — 从请求头提取并校验 JWT，将认证信息写入 {@code SecurityContext}</li>
 *   <li>{@code AdminUserDetailsService} — 实现 {@code UserDetailsService}，从数据库加载管理员信息</li>
 *   <li>{@code MfaService} — 支持 TOTP 软令牌或邮件验证码的多因素认证逻辑</li>
 * </ul>
 */
package com.fluxpay.admin.security;
