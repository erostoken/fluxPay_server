package com.fluxpay.core.orm.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fluxpay.core.orm.sys.entity.SysRoleMenu;
import com.fluxpay.core.orm.sys.mapper.SysRoleMenuMapper;
import com.fluxpay.core.orm.sys.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * 针对表【sys_role_menu(系统-角色菜单关联表)】的数据库操作 Service 实现
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu>
        implements SysRoleMenuService {
}
