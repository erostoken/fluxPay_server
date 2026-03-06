package com.fluxpay.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * API 定义表（含计费单价）
 * @TableName api_definition
 */
@TableName(value ="api_definition")
@Data
public class ApiDefinition {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 所属分组 ID
     */
    private Long groupId;

    /**
     * API 名称
     */
    private String name;

    /**
     * API 唯一编码（网关路由匹配键）
     */
    private String code;

    /**
     * API 描述
     */
    private String description;

    /**
     * HTTP 方法：GET / POST / PUT / DELETE
     */
    private String method;

    /**
     * 路由路径（如 /v1/ocr/id-card）
     */
    private String path;

    /**
     * 按次计费单价（元/次，精度0.000001）
     */
    private BigDecimal unitPrice;

    /**
     * 状态：0-草稿 1-已上线 2-已下线
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