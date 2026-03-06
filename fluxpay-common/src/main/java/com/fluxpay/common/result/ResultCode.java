package com.fluxpay.common.result;

import lombok.Getter;

/**
 * 全局业务错误码枚举
 *
 * <pre>
 * 编码规则：
 *   200xx  — 成功
 *   400xx  — 客户端错误（参数、权限、资源）
 *   500xx  — 服务端错误
 *   600xx  — 业务错误（计费、配额、审核等）
 * </pre>
 */
@Getter
public enum ResultCode {

    // ── 成功 ──────────────────────────────────────────────────
    SUCCESS(20000, "操作成功"),

    // ── 客户端错误 ────────────────────────────────────────────
    BAD_REQUEST(40000, "请求参数错误"),
    UNAUTHORIZED(40001, "未登录或登录已过期"),
    FORBIDDEN(40003, "无权限访问"),
    NOT_FOUND(40004, "资源不存在"),
    METHOD_NOT_ALLOWED(40005, "请求方式不支持"),
    TOO_MANY_REQUESTS(40029, "请求过于频繁，请稍后重试"),

    // ── 服务端错误 ────────────────────────────────────────────
    INTERNAL_SERVER_ERROR(50000, "服务器内部错误"),
    SERVICE_UNAVAILABLE(50003, "服务暂时不可用"),

    // ── 认证相关 ──────────────────────────────────────────────
    LOGIN_FAILED(60001, "用户名或密码错误"),
    ACCOUNT_DISABLED(60002, "账号已被禁用"),
    ACCOUNT_PENDING(60003, "账号待审核，暂无法登录"),
    MFA_CODE_INVALID(60004, "MFA 验证码错误或已过期"),
    TOKEN_INVALID(60005, "Token 无效"),
    TOKEN_EXPIRED(60006, "Token 已过期"),
    CAPTCHA_INVALID(60007, "验证码错误或已过期"),
    INVITE_TOKEN_INVALID(60008, "邀请链接无效或已过期"),

    // ── 企业 / 用户相关 ───────────────────────────────────────
    ENTERPRISE_NOT_FOUND(61001, "企业不存在"),
    ENTERPRISE_EMAIL_EXISTS(61002, "该邮箱已注册"),
    VERIFY_PENDING(61003, "实名认证待审核中"),
    VERIFY_REJECTED(61004, "实名认证审核未通过"),
    MEMBER_LIMIT_EXCEEDED(61005, "子账号数量已达上限"),
    MEMBER_NOT_FOUND(61006, "成员不存在"),

    // ── API Key 相关 ──────────────────────────────────────────
    API_KEY_NOT_FOUND(62001, "API Key 不存在"),
    API_KEY_DISABLED(62002, "API Key 已禁用"),
    API_KEY_EXPIRED(62003, "API Key 已过期"),
    API_KEY_LIMIT_EXCEEDED(62004, "API Key 数量已达上限"),
    API_KEY_NO_PERMISSION(62005, "该 API Key 无权访问此接口"),

    // ── 计费 / 配额相关 ───────────────────────────────────────
    BALANCE_INSUFFICIENT(63001, "账户余额不足"),
    QUOTA_EXHAUSTED(63002, "套餐调用配额已耗尽"),
    NO_ACTIVE_SUBSCRIPTION(63003, "当前无有效套餐订阅"),

    // ── 套餐相关 ──────────────────────────────────────────────
    PLAN_NOT_FOUND(64001, "套餐不存在"),
    PLAN_OFFLINE(64002, "套餐已下架"),
    PLAN_DOWNGRADE_MEMBER_EXCEEDED(64003, "当前子账号数量超过目标套餐上限，请先删减子账号"),

    // ── 充值相关 ──────────────────────────────────────────────
    RECHARGE_NOT_FOUND(65001, "充值记录不存在"),
    RECHARGE_ALREADY_REVIEWED(65002, "该充值记录已审核，不可重复操作"),

    // ── API 管理相关 ──────────────────────────────────────────
    API_NOT_FOUND(66001, "API 不存在"),
    API_CODE_EXISTS(66002, "API 编码已存在"),
    API_OFFLINE(66003, "该 API 已下线");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
