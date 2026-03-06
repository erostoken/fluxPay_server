package com.fluxpay.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 管理员表
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
public class SysUser {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 登录用户名
     */
    private String username;

    /**
     * 登录密码（BCrypt）
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 角色：SUPER_ADMIN / ADMIN / FINANCE
     */
    private String role;

    /**
     * 状态：0-禁用 1-启用
     */
    private Integer status;

    /**
     * MFA 是否开启：0-否 1-是
     */
    private Integer mfaEnabled;

    /**
     * TOTP 密钥（AES-256 加密存储）
     */
    private String mfaSecret;

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