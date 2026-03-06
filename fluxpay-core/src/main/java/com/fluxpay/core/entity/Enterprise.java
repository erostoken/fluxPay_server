package com.fluxpay.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 企业表（多租户主体，enterprise.id = tenant_id）
 * @TableName enterprise
 */
@TableName(value ="enterprise")
@Data
public class Enterprise {
    /**
     * 主键（同时作为 tenant_id）
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 主账号登录邮箱
     */
    private String email;

    /**
     * 登录密码（BCrypt）
     */
    private String password;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 账号状态：0-待审核 1-正常 2-已禁用
     */
    private Integer status;

    /**
     * 账户余额（元，精度0.0001）
     */
    private BigDecimal balance;

    /**
     * 乐观锁版本号（余额更新防并发）
     */
    private Long version;

    /**
     * 法人姓名
     */
    private String legalName;

    /**
     * 营业执照注册号
     */
    private String businessLicense;

    /**
     * 营业执照图片 URL
     */
    private String licenseImageUrl;

    /**
     * 实名认证状态：0-未提交 1-待审核 2-已通过 3-已拒绝
     */
    private Integer verifyStatus;

    /**
     * 审核备注（拒绝原因等）
     */
    private String verifyRemark;

    /**
     * 审核通过时间
     */
    private Date verifyTime;

    /**
     * 审核管理员 ID
     */
    private Long verifyAdminId;

    /**
     * Admin 覆盖的子账号上限，NULL 则以套餐为准
     */
    private Integer maxMemberOverride;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 创建人 ID（0=系统）
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