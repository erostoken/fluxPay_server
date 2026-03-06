package com.fluxpay.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fluxpay.core.entity.CallLog;
import com.fluxpay.core.service.CallLogService;
import com.fluxpay.core.mapper.CallLogMapper;
import org.springframework.stereotype.Service;

/**
* @author apple
* @description 针对表【call_log(调用记录表（网关原始流水，高写入，保留3个月）)】的数据库操作Service实现
* @createDate 2026-03-06 09:57:35
*/
@Service
public class CallLogServiceImpl extends ServiceImpl<CallLogMapper, CallLog>
    implements CallLogService{

}




