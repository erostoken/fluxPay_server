package com.fluxpay.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fluxpay.core.entity.Member;
import com.fluxpay.core.service.MemberService;
import com.fluxpay.core.mapper.MemberMapper;
import org.springframework.stereotype.Service;

/**
* @author apple
* @description 针对表【member(企业成员表（子账号，租户隔离）)】的数据库操作Service实现
* @createDate 2026-03-06 09:57:35
*/
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
    implements MemberService{

}




