package com.fluxpay.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fluxpay.core.entity.ApiRoute;
import com.fluxpay.core.service.ApiRouteService;
import com.fluxpay.core.mapper.ApiRouteMapper;
import org.springframework.stereotype.Service;

/**
* @author apple
* @description 针对表【api_route(API 路由配置表（网关转发规则）)】的数据库操作Service实现
* @createDate 2026-03-06 09:57:35
*/
@Service
public class ApiRouteServiceImpl extends ServiceImpl<ApiRouteMapper, ApiRoute>
    implements ApiRouteService{

}




