package com.fluxpay.common.enums;

import lombok.Getter;

/**
 * 套餐状态
 */
@Getter
public enum PlanStatusEnum {

    OFFLINE(0, "已下架"),
    ONLINE(1, "已上架");

    private final int code;
    private final String desc;

    PlanStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
