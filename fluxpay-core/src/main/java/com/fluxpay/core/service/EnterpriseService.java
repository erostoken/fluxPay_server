package com.fluxpay.core.service;

import com.fluxpay.core.entity.Enterprise;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author apple
* @description 针对表【enterprise(企业表（多租户主体，enterprise.id = tenant_id）)】的数据库操作Service
* @createDate 2026-03-06 09:57:35
*/
public interface EnterpriseService extends IService<Enterprise> {

}
