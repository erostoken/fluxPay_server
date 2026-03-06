package com.fluxpay.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * API Key 表（租户隔离）
 * @TableName api_key
 */
@TableName(value ="api_key")
@Data
public class ApiKey {
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
     * 归属子账号 ID，NULL 表示主账号创建
     */
    private Long memberId;

    /**
     * API Key 值（格式：fp_xxx，AES-256 加密存储，前缀明文）
     */
    private String keyValue;

    /**
     * Key 备注名称
     */
    private String name;

    /**
     * 状态：0-禁用 1-启用
     */
    private Integer status;

    /**
     * 过期时间，NULL 表示永久有效
     */
    private Date expiresAt;

    /**
     * 最近使用时间
     */
    private Date lastUsedTime;

    /**
     * 累计调用次数
     */
    private Long callCount;

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