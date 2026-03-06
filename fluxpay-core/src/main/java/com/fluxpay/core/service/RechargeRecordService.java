package com.fluxpay.core.service;

import com.fluxpay.core.entity.RechargeRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author apple
* @description 针对表【recharge_record(充值记录表（对公转账需 Admin 审核，租户隔离）)】的数据库操作Service
* @createDate 2026-03-06 09:57:35
*/
public interface RechargeRecordService extends IService<RechargeRecord> {

}
