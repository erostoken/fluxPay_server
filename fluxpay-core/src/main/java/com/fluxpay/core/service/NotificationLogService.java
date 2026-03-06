package com.fluxpay.core.service;

import com.fluxpay.core.entity.NotificationLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author apple
* @description 针对表【notification_log(通知记录表（站内消息 + 邮件日志，租户隔离）)】的数据库操作Service
* @createDate 2026-03-06 09:57:35
*/
public interface NotificationLogService extends IService<NotificationLog> {

}
