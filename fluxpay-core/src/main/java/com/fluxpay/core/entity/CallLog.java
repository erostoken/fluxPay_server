package com.fluxpay.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 调用记录表（网关原始流水，高写入，保留3个月）
 * @TableName call_log
 */
@TableName(value ="call_log")
@Data
public class CallLog {
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
     * API Key ID
     */
    private Long apiKeyId;

    /**
     * 调用者子账号 ID，NULL 表示主账号
     */
    private Long memberId;

    /**
     * API ID
     */
    private Long apiId;

    /**
     * API 编码快照
     */
    private String apiCode;

    /**
     * 网关全局唯一请求 ID（幂等对账键）
     */
    private String gatewayRequestId;

    /**
     * 调用方 IP
     */
    private String requestIp;

    /**
     * HTTP 响应状态码
     */
    private Integer httpStatus;

    /**
     * 响应耗时（毫秒）
     */
    private Integer latencyMs;

    /**
     * 调用时间
     */
    private Date calledAt;

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