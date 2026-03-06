package com.fluxpay.core.orm.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fluxpay.core.orm.sys.entity.SysUser;
import com.fluxpay.core.orm.sys.service.SysUserService;
import com.fluxpay.core.orm.sys.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author apple
* @description 针对表【sys_user(管理员表)】的数据库操作Service实现
* @createDate 2026-03-06 11:01:05
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

}




