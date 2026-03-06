package com.fluxpay.common.enums;

import lombok.Getter;

/**
 * 套餐订阅状态
 */
@Getter
public enum SubscriptionStatusEnum {

    ACTIVE(1, "有效"),
    EXPIRED(2, "已过期"),
    CANCELLED(3, "已取消");

    private final int code;
    private final String desc;

    SubscriptionStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
