/**
 * 核心模块基础设施配置。
 *
 * <p>包含：
 * <ul>
 *   <li>{@code MybatisPlusConfig} — 注册分页插件 {@code PaginationInnerInterceptor}</li>
 *   <li>{@code RedisConfig} — 配置 {@code RedisTemplate} 的序列化方式（key 用 String，value 用 JSON）</li>
 * </ul>
 */
package com.fluxpay.core.config;
