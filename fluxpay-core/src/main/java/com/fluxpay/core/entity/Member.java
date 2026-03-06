package com.fluxpay.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 企业成员表（子账号，租户隔离）
 * @TableName member
 */
@TableName(value ="member")
@Data
public class Member {
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
     * 成员姓名 / 昵称
     */
    private String name;

    /**
     * 登录邮箱
     */
    private String email;

    /**
     * 登录密码（BCrypt）
     */
    private String password;

    /**
     * 角色：ADMIN（子管理员） / MEMBER（普通成员）
     */
    private String role;

    /**
     * 状态：0-禁用 1-启用
     */
    private Integer status;

    /**
     * 邀请 Token（未激活时使用）
     */
    private String inviteToken;

    /**
     * 邀请 Token 过期时间（48小时后过期）
     */
    private Date inviteExpiresAt;

    /**
     * 最近登录时间
     */
    private Date lastLoginTime;

    /**
     * 最近登录 IP
     */
    private String lastLoginIp;

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