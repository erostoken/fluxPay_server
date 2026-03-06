package com.fluxpay.common.enums;

import lombok.Getter;

/**
 * 实名认证状态
 */
@Getter
public enum VerifyStatusEnum {

    NOT_SUBMITTED(0, "未提交"),
    PENDING(1, "待审核"),
    APPROVED(2, "已通过"),
    REJECTED(3, "已拒绝");

    private final int code;
    private final String desc;

    VerifyStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
