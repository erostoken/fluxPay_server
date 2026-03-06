package com.fluxpay.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fluxpay.core.entity.BillingRecord;
import com.fluxpay.core.service.BillingRecordService;
import com.fluxpay.core.mapper.BillingRecordMapper;
import org.springframework.stereotype.Service;

/**
* @author apple
* @description 针对表【billing_record(消费流水表（计费明细，与 call_log 双写对账）)】的数据库操作Service实现
* @createDate 2026-03-06 09:57:35
*/
@Service
public class BillingRecordServiceImpl extends ServiceImpl<BillingRecordMapper, BillingRecord>
    implements BillingRecordService{

}




