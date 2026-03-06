package com.fluxpay.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fluxpay.core.entity.ThirdPartyCredential;
import com.fluxpay.core.service.ThirdPartyCredentialService;
import com.fluxpay.core.mapper.ThirdPartyCredentialMapper;
import org.springframework.stereotype.Service;

/**
* @author apple
* @description 针对表【third_party_credential(第三方 API 凭证表（加密存储）)】的数据库操作Service实现
* @createDate 2026-03-06 09:57:35
*/
@Service
public class ThirdPartyCredentialServiceImpl extends ServiceImpl<ThirdPartyCredentialMapper, ThirdPartyCredential>
    implements ThirdPartyCredentialService{

}




