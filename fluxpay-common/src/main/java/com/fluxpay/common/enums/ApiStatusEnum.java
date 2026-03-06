package com.fluxpay.common.enums;

import lombok.Getter;

/**
 * API 上线状态
 */
@Getter
public enum ApiStatusEnum {

    DRAFT(0, "草稿"),
    ONLINE(1, "已上线"),
    OFFLINE(2, "已下线");

    private final int code;
    private final String desc;

    ApiStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
