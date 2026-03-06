package com.fluxpay.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fluxpay.core.entity.Plan;
import com.fluxpay.core.service.PlanService;
import com.fluxpay.core.mapper.PlanMapper;
import org.springframework.stereotype.Service;

/**
* @author apple
* @description 针对表【plan(套餐表)】的数据库操作Service实现
* @createDate 2026-03-06 09:57:35
*/
@Service
public class PlanServiceImpl extends ServiceImpl<PlanMapper, Plan>
    implements PlanService{

}




