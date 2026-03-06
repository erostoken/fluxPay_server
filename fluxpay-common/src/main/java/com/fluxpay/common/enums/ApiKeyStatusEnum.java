package com.fluxpay.common.enums;

import lombok.Getter;

/**
 * API Key 状态
 */
@Getter
public enum ApiKeyStatusEnum {

    DISABLED(0, "已禁用"),
    ENABLED(1, "启用");

    private final int code;
    private final String desc;

    ApiKeyStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
