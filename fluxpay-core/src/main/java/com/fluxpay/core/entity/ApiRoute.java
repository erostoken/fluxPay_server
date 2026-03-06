package com.fluxpay.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * API 路由配置表（网关转发规则）
 * @TableName api_route
 */
@TableName(value ="api_route")
@Data
public class ApiRoute {
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
     * 路由类型：1-自有 API 2-第三方代理
     */
    private Integer routeType;

    /**
     * 上游服务地址
     */
    private String upstreamUrl;

    /**
     * 超时时间（毫秒）
     */
    private Integer timeoutMs;

    /**
     * 失败重试次数
     */
    private Integer retryCount;

    /**
     * 是否裁剪路由前缀：0-否 1-是
     */
    private Integer stripPrefix;

    /**
     * 附加请求头（JSON Object，如鉴权头）
     */
    private Object extraHeaders;

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