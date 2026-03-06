package com.fluxpay.core.mapper;

import com.fluxpay.core.entity.OperationLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author apple
* @description 针对表【operation_log(操作日志表（tenant_id 为 NULL 时为管理员操作）)】的数据库操作Mapper
* @createDate 2026-03-06 09:57:35
* @Entity com.fluxpay.core.entity.OperationLog
*/
public interface OperationLogMapper extends BaseMapper<OperationLog> {

}




