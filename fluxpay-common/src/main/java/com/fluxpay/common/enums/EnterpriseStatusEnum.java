package com.fluxpay.common.enums;

import lombok.Getter;

/**
 * 企业账号状态
 */
@Getter
public enum EnterpriseStatusEnum {

    PENDING(0, "待审核"),
    NORMAL(1, "正常"),
    DISABLED(2, "已禁用");

    private final int code;
    private final String desc;

    EnterpriseStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
