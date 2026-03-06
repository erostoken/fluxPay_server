/**
 * 核心业务 Service 接口。
 *
 * <p>定义跨模块共用的业务逻辑契约，具体实现位于 {@code impl} 子包。
 * 包含：EnterpriseService、ApiKeyService、PlanService、BillingService、SubscriptionService 等。
 * BillingService 封装按次计费的核心扣费逻辑，为网关计量过滤器的直接调用入口。
 */
package com.fluxpay.core.service;
