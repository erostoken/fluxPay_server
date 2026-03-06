package com.fluxpay.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fluxpay.core.entity.RechargeRecord;
import com.fluxpay.core.service.RechargeRecordService;
import com.fluxpay.core.mapper.RechargeRecordMapper;
import org.springframework.stereotype.Service;

/**
* @author apple
* @description 针对表【recharge_record(充值记录表（对公转账需 Admin 审核，租户隔离）)】的数据库操作Service实现
* @createDate 2026-03-06 09:57:35
*/
@Service
public class RechargeRecordServiceImpl extends ServiceImpl<RechargeRecordMapper, RechargeRecord>
    implements RechargeRecordService{

}




