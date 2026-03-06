package com.fluxpay.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 套餐表
 * @TableName plan
 */
@TableName(value ="plan")
@Data
public class Plan {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 套餐名称
     */
    private String name;

    /**
     * 套餐描述
     */
    private String description;

    /**
     * 套餐月费（元）
     */
    private BigDecimal price;

    /**
     * 每计费周期内可调用次数
     */
    private Integer quota;

    /**
     * 最大子账号数（默认5）
     */
    private Integer maxMemberCount;

    /**
     * 最大 API Key 数
     */
    private Integer maxApiKeyCount;

    /**
     * 状态：0-下架 1-上架
     */
    private Integer status;

    /**
     * 排序值
     */
    private Integer sort;

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