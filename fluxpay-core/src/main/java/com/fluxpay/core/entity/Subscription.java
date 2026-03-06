package com.fluxpay.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 套餐订阅表（含配额快照，租户隔离）
 * @TableName subscription
 */
@TableName(value ="subscription")
@Data
public class Subscription {
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
     * 套餐 ID
     */
    private Long planId;

    /**
     * 状态：1-有效 2-已过期 3-已取消
     */
    private Integer status;

    /**
     * 计费周期：1-月付（30天） 2-年付（365天）
     */
    private Integer periodType;

    /**
     * 订阅生效日期（配额重置起点）
     */
    private Date startDate;

    /**
     * 下次续费/配额重置日期
     */
    private Date nextRenewDate;

    /**
     * 本计费周期剩余调用次数
     */
    private Integer remainingQuota;

    /**
     * 套餐名称快照
     */
    private String planName;

    /**
     * 套餐价格快照（元）
     */
    private BigDecimal planPrice;

    /**
     * 套餐配额快照（次）
     */
    private Integer planQuota;

    /**
     * 套餐子账号上限快照
     */
    private Integer planMaxMember;

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