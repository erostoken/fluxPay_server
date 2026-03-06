package com.fluxpay.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 操作日志表（tenant_id 为 NULL 时为管理员操作）
 * @TableName operation_log
 */
@TableName(value ="operation_log")
@Data
public class OperationLog {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 所属企业 ID，NULL 表示管理员操作
     */
    private Long tenantId;

    /**
     * 操作人类型：1-管理员 2-企业主账号 3-企业成员
     */
    private Integer operatorType;

    /**
     * 操作人 ID
     */
    private Long operatorId;

    /**
     * 操作人名称（快照）
     */
    private String operatorName;

    /**
     * 操作模块（如：API管理 / 套餐管理 / 企业管理）
     */
    private String module;

    /**
     * 操作描述（如：新增 API / 禁用企业账号）
     */
    private String action;

    /**
     * 操作对象类型（如：api_definition / enterprise）
     */
    private String targetType;

    /**
     * 操作对象 ID
     */
    private Long targetId;

    /**
     * HTTP 方法
     */
    private String requestMethod;

    /**
     * 请求路径
     */
    private String requestUrl;

    /**
     * 请求 IP
     */
    private String requestIp;

    /**
     * 请求参数（已脱敏，如密码字段替换为 ***）
     */
    private String requestParams;

    /**
     * 操作结果：1-成功 0-失败
     */
    private Integer result;

    /**
     * 失败原因
     */
    private String errorMsg;

    /**
     * 操作时间
     */
    private Date operatedAt;

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