package com.fluxpay.common.enums;

import lombok.Getter;

/**
 * 计费类型
 *
 * <ul>
 *   <li>QUOTA — 消耗套餐配额</li>
 *   <li>BALANCE — 扣除余额（配额耗尽后）</li>
 *   <li>COMPENSATE_DEDUCT — 对账补扣（漏扣补偿）</li>
 *   <li>COMPENSATE_REFUND — 对账退款（多扣补偿）</li>
 * </ul>
 */
@Getter
public enum BillingTypeEnum {

    QUOTA("QUOTA", "套餐配额"),
    BALANCE("BALANCE", "余额扣费"),
    COMPENSATE_DEDUCT("COMPENSATE_DEDUCT", "补扣"),
    COMPENSATE_REFUND("COMPENSATE_REFUND", "退款");

    private final String code;
    private final String desc;

    BillingTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
