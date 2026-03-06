package com.fluxpay.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 消费流水表（计费明细，与 call_log 双写对账）
 * @TableName billing_record
 */
@TableName(value ="billing_record")
@Data
public class BillingRecord {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 所属企业 ID（tenant_id）
     */
    private Long tenantId;

    /**
     * 关联 call_log.id（对账键）
     */
    private Long callLogId;

    /**
     * API Key ID
     */
    private Long apiKeyId;

    /**
     * 调用者子账号 ID
     */
    private Long memberId;

    /**
     * API ID
     */
    private Long apiId;

    /**
     * API 名称快照
     */
    private String apiName;

    /**
     * 计费单价快照（元/次）
     */
    private BigDecimal unitPrice;

    /**
     * 实际扣费金额（元）
     */
    private BigDecimal amount;

    /**
     * 计费类型：QUOTA（套餐配额） / BALANCE（余额） / COMPENSATE_DEDUCT（补扣） / COMPENSATE_REFUND（退款）
     */
    private String billingType;

    /**
     * 扣费前余额（余额模式时记录，配额模式为 NULL）
     */
    private BigDecimal balanceBefore;

    /**
     * 扣费后余额
     */
    private BigDecimal balanceAfter;

    /**
     * 对应调用时间（冗余，用于时间范围查询）
     */
    private Date calledAt;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 创建人 ID
     */
    private Long createdBy;

    /**
     * 修改时间
     */
    private Date updatedTime;

    /**
     * 修改人 ID
     */
    private Long updatedBy;

    /**
     * 逻辑删除：0-未删除 1-已删除
     */
    private Integer isDeleted;
}