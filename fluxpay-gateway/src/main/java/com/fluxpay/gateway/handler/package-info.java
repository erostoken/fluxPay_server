/**
 * 网关层统一异常处理。
 *
 * <p>{@code GatewayExceptionHandler} 实现 {@code ErrorWebExceptionHandler}，
 * 将网关过滤器抛出的异常（鉴权失败、限流拦截、上游不可达等）统一转换为
 * 符合 {@code Result} 规范的 JSON 响应，避免框架默认的 Whitelabel 错误页。
 */
package com.fluxpay.gateway.handler;
