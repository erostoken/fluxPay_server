package com.fluxpay.common.enums;

import lombok.Getter;

/**
 * 充值记录状态
 */
@Getter
public enum RechargeStatusEnum {

    PENDING(0, "待审核"),
    APPROVED(1, "已到账"),
    REJECTED(2, "已拒绝");

    private final int code;
    private final String desc;

    RechargeStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
