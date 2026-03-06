package com.fluxpay.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fluxpay.core.entity.ApiDefinition;
import com.fluxpay.core.service.ApiDefinitionService;
import com.fluxpay.core.mapper.ApiDefinitionMapper;
import org.springframework.stereotype.Service;

/**
* @author apple
* @description 针对表【api_definition(API 定义表（含计费单价）)】的数据库操作Service实现
* @createDate 2026-03-06 09:57:35
*/
@Service
public class ApiDefinitionServiceImpl extends ServiceImpl<ApiDefinitionMapper, ApiDefinition>
    implements ApiDefinitionService{

}




