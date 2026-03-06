package com.fluxpay.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 套餐-API 权限关联表
 * @TableName plan_api
 */
@TableName(value ="plan_api")
@Data
public class PlanApi {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 套餐 ID
     */
    private Long planId;

    /**
     * API ID（api_definition.id）
     */
    private Long apiId;

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