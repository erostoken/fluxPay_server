package com.fluxpay.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fluxpay.core.entity.PlanApi;
import com.fluxpay.core.service.PlanApiService;
import com.fluxpay.core.mapper.PlanApiMapper;
import org.springframework.stereotype.Service;

/**
* @author apple
* @description 针对表【plan_api(套餐-API 权限关联表)】的数据库操作Service实现
* @createDate 2026-03-06 09:57:35
*/
@Service
public class PlanApiServiceImpl extends ServiceImpl<PlanApiMapper, PlanApi>
    implements PlanApiService{

}




