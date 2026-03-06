package com.fluxpay.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fluxpay.core.entity.Enterprise;
import com.fluxpay.core.service.EnterpriseService;
import com.fluxpay.core.mapper.EnterpriseMapper;
import org.springframework.stereotype.Service;

/**
* @author apple
* @description 针对表【enterprise(企业表（多租户主体，enterprise.id = tenant_id）)】的数据库操作Service实现
* @createDate 2026-03-06 09:57:35
*/
@Service
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseMapper, Enterprise>
    implements EnterpriseService{

}




