package com.fluxpay.core.mapper;

import com.fluxpay.core.entity.Enterprise;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author apple
* @description 针对表【enterprise(企业表（多租户主体，enterprise.id = tenant_id）)】的数据库操作Mapper
* @createDate 2026-03-06 09:57:35
* @Entity com.fluxpay.core.entity.Enterprise
*/
public interface EnterpriseMapper extends BaseMapper<Enterprise> {

}




