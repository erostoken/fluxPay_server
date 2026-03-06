package com.fluxpay.core.mapper;

import com.fluxpay.core.entity.CallLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author apple
* @description 针对表【call_log(调用记录表（网关原始流水，高写入，保留3个月）)】的数据库操作Mapper
* @createDate 2026-03-06 09:57:35
* @Entity com.fluxpay.core.entity.CallLog
*/
public interface CallLogMapper extends BaseMapper<CallLog> {

}




