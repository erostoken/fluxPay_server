package com.fluxpay.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fluxpay.core.entity.Subscription;
import com.fluxpay.core.service.SubscriptionService;
import com.fluxpay.core.mapper.SubscriptionMapper;
import org.springframework.stereotype.Service;

/**
* @author apple
* @description 针对表【subscription(套餐订阅表（含配额快照，租户隔离）)】的数据库操作Service实现
* @createDate 2026-03-06 09:57:35
*/
@Service
public class SubscriptionServiceImpl extends ServiceImpl<SubscriptionMapper, Subscription>
    implements SubscriptionService{

}




