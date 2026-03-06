package com.fluxpay.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 充值记录表（对公转账需 Admin 审核，租户隔离）
 * @TableName recharge_record
 */
@TableName(value ="recharge_record")
@Data
public class RechargeRecord {
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
     * 充值金额（元）
     */
    private BigDecimal amount;

    /**
     * 充值类型：1-对公转账 2-系统调整（Admin 操作）
     */
    private Integer type;

    /**
     * 状态：0-待审核 1-已到账 2-已拒绝
     */
    private Integer status;

    /**
     * 转账金额（元，企业填写）
     */
    private BigDecimal bankAmount;

    /**
     * 汇款账户名
     */
    private String bankAccountName;

    /**
     * 转账凭证截图 URL
     */
    private String bankVoucherUrl;

    /**
     * 审核管理员 ID
     */
    private Long reviewAdminId;

    /**
     * 审核时间
     */
    private Date reviewTime;

    /**
     * 审核备注（拒绝原因）
     */
    private String reviewRemark;

    /**
     * 充值前余额
     */
    private BigDecimal balanceBefore;

    /**
     * 充值后余额
     */
    private BigDecimal balanceAfter;

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