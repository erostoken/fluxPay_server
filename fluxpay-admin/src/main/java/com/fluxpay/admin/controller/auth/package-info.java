/**
 * 认证相关控制器。
 *
 * <p>处理管理员登录、MFA 验证、Token 刷新与登出接口。
 * 登录成功后返回 Access Token（短期）与 Refresh Token（长期），
 * 登出时将 Token 加入 Redis 黑名单。
 */
package com.fluxpay.admin.controller.auth;
