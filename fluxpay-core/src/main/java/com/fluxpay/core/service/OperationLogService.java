package com.fluxpay.core.service;

import com.fluxpay.core.entity.OperationLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author apple
* @description 针对表【operation_log(操作日志表（tenant_id 为 NULL 时为管理员操作）)】的数据库操作Service
* @createDate 2026-03-06 09:57:35
*/
public interface OperationLogService extends IService<OperationLog> {

}
