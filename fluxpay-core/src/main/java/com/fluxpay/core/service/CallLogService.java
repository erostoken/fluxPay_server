package com.fluxpay.core.service;

import com.fluxpay.core.entity.CallLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author apple
* @description 针对表【call_log(调用记录表（网关原始流水，高写入，保留3个月）)】的数据库操作Service
* @createDate 2026-03-06 09:57:35
*/
public interface CallLogService extends IService<CallLog> {

}
