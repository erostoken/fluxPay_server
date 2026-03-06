/**
 * 网关全局过滤器。
 *
 * <p>基于 Spring Cloud Gateway 的 {@code GlobalFilter} 实现，按优先级依次执行：
 * <ol>
 *   <li>{@code AuthFilter} — 验证请求头中的 API Key 合法性与启用状态</li>
 *   <li>{@code RateLimitFilter} — 基于 Redis 令牌桶算法对 API Key 进行限流</li>
 *   <li>{@code MeteringFilter} — 请求成功后按次扣减配额并写入计量记录</li>
 * </ol>
 */
package com.fluxpay.gateway.filter;
