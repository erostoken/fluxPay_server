package com.fluxpay.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 通知记录表（站内消息 + 邮件日志，租户隔离）
 * @TableName notification_log
 */
@TableName(value ="notification_log")
@Data
public class NotificationLog {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 接收方企业 ID（tenant_id）
     */
    private Long tenantId;

    /**
     * 通知类型：RECHARGE_SUCCESS / RECHARGE_REJECT / SUBSCRIBE_SUCCESS / API_OFFLINE / QUOTA_WARNING
     */
    private String type;

    /**
     * 通知渠道：EMAIL / IN_APP
     */
    private String channel;

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 是否已读：0-未读 1-已读
     */
    private Integer isRead;

    /**
     * 已读时间
     */
    private Date readTime;

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