package com.fluxpay.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fluxpay.core.entity.NotificationLog;
import com.fluxpay.core.service.NotificationLogService;
import com.fluxpay.core.mapper.NotificationLogMapper;
import org.springframework.stereotype.Service;

/**
* @author apple
* @description 针对表【notification_log(通知记录表（站内消息 + 邮件日志，租户隔离）)】的数据库操作Service实现
* @createDate 2026-03-06 09:57:35
*/
@Service
public class NotificationLogServiceImpl extends ServiceImpl<NotificationLogMapper, NotificationLog>
    implements NotificationLogService{

}




