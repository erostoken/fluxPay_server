package com.fluxpay.common.enums;

import lombok.Getter;

/**
 * 企业成员角色
 */
@Getter
public enum MemberRoleEnum {

    ADMIN("ADMIN", "子管理员"),
    MEMBER("MEMBER", "普通成员");

    private final String code;
    private final String desc;

    MemberRoleEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
