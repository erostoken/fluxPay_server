package com.fluxpay.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fluxpay.core.entity.ApiKey;
import com.fluxpay.core.service.ApiKeyService;
import com.fluxpay.core.mapper.ApiKeyMapper;
import org.springframework.stereotype.Service;

/**
* @author apple
* @description 针对表【api_key(API Key 表（租户隔离）)】的数据库操作Service实现
* @createDate 2026-03-06 09:57:35
*/
@Service
public class ApiKeyServiceImpl extends ServiceImpl<ApiKeyMapper, ApiKey>
    implements ApiKeyService{

}




