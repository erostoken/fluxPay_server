package com.fluxpay.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 第三方 API 凭证表（加密存储）
 * @TableName third_party_credential
 */
@TableName(value ="third_party_credential")
@Data
public class ThirdPartyCredential {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 关联 api_definition.id
     */
    private Long apiId;

    /**
     * 凭证名称（如：OpenAI GPT-4 Key）
     */
    private String name;

    /**
     * 凭证类型：API_KEY / BEARER_TOKEN / BASIC_AUTH
     */
    private String credentialType;

    /**
     * 凭证内容（AES-256 加密存储）
     */
    private String credentialValue;

    /**
     * 状态：0-禁用 1-启用
     */
    private Integer status;

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