package com.fluxpay.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fluxpay.core.entity.OperationLog;
import com.fluxpay.core.service.OperationLogService;
import com.fluxpay.core.mapper.OperationLogMapper;
import org.springframework.stereotype.Service;

/**
* @author apple
* @description 针对表【operation_log(操作日志表（tenant_id 为 NULL 时为管理员操作）)】的数据库操作Service实现
* @createDate 2026-03-06 09:57:35
*/
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog>
    implements OperationLogService{

}




