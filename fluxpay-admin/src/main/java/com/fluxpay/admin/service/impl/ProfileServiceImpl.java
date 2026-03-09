package com.fluxpay.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fluxpay.admin.domain.vo.resp.menu.MenuTreeResp;
import com.fluxpay.admin.domain.vo.resp.profile.ProfileResp;
import com.fluxpay.admin.security.SecurityUtils;
import com.fluxpay.admin.service.ProfileService;
import com.fluxpay.common.exception.BusinessException;
import com.fluxpay.common.result.ResultCode;
import com.fluxpay.core.orm.sys.entity.SysMenu;
import com.fluxpay.core.orm.sys.entity.SysRole;
import com.fluxpay.core.orm.sys.entity.SysUser;
import com.fluxpay.core.orm.sys.entity.SysUserRole;
import com.fluxpay.core.orm.sys.service.SysMenuService;
import com.fluxpay.core.orm.sys.service.SysRoleService;
import com.fluxpay.core.orm.sys.service.SysUserRoleService;
import com.fluxpay.core.orm.sys.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final SysUserService     sysUserService;
    private final SysRoleService     sysRoleService;
    private final SysUserRoleService sysUserRoleService;
    private final SysMenuService     sysMenuService;

    @Override
    public ProfileResp getProfile() {
        Long userId = SecurityUtils.getCurrentUserId();

        SysUser user = sysUserService.getById(userId);
        if (user == null || user.getIsDeleted() == 1) {
            throw new BusinessException(ResultCode.UNAUTHORIZED);
        }

        // 查询角色列表
        List<Long> roleIds = getRoleIds(userId);
        List<SysRole> roles = roleIds.isEmpty()
                ? List.of()
                : sysRoleService.listByIds(roleIds);

        ProfileResp vo = new ProfileResp();
        vo.setId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setPhone(user.getPhone());
        vo.setStatus(user.getStatus());
        vo.setLastLoginTime(user.getLastLoginTime());
        vo.setRoles(roles.stream().map(r -> {
            ProfileResp.RoleVO rv = new ProfileResp.RoleVO();
            rv.setId(r.getId());
            rv.setRoleName(r.getRoleName());
            rv.setRoleCode(r.getRoleCode());
            return rv;
        }).collect(Collectors.toList()));
        vo.setMenus(getMenus());
        return vo;
    }

    @Override
    public List<MenuTreeResp> getMenus() {
        // 查询所有启用且未删除的菜单（目录+菜单类型，过滤按钮/接口）
        List<SysMenu> allMenus = sysMenuService.list(
                new LambdaQueryWrapper<SysMenu>()
                        .eq(SysMenu::getIsDeleted, 0)
                        .eq(SysMenu::getStatus, 1)
                        .in(SysMenu::getType, 1, 2)      // 1-目录 2-菜单
                        .orderByAsc(SysMenu::getSortOrder)
                        .orderByAsc(SysMenu::getId));

        return buildTree(allMenus, 0L);
    }

    // ── 私有方法 ──────────────────────────────────────────────────────────

    private List<Long> getRoleIds(Long userId) {
        return sysUserRoleService
                .list(new LambdaQueryWrapper<SysUserRole>()
                        .eq(SysUserRole::getUserId, userId))
                .stream()
                .map(SysUserRole::getRoleId)
                .collect(Collectors.toList());
    }

    private List<MenuTreeResp> buildTree(List<SysMenu> all, Long parentId) {
        Map<Long, List<SysMenu>> grouped = all.stream()
                .collect(Collectors.groupingBy(SysMenu::getParentId));
        return buildChildren(grouped, parentId);
    }

    private List<MenuTreeResp> buildChildren(Map<Long, List<SysMenu>> grouped, Long parentId) {
        List<SysMenu> children = grouped.getOrDefault(parentId, new ArrayList<>());
        return children.stream().map(menu -> {
            MenuTreeResp vo = toMenuVO(menu);
            vo.setChildren(buildChildren(grouped, menu.getId()));
            return vo;
        }).collect(Collectors.toList());
    }

    private MenuTreeResp toMenuVO(SysMenu menu) {
        MenuTreeResp vo = new MenuTreeResp();
        vo.setId(menu.getId());
        vo.setParentId(menu.getParentId());
        vo.setMenuName(menu.getMenuName());
        vo.setType(menu.getType());
        vo.setPath(menu.getPath());
        vo.setComponent(menu.getComponent());
        vo.setIcon(menu.getIcon());
        vo.setSortOrder(menu.getSortOrder());
        vo.setStatus(menu.getStatus());
        return vo;
    }
}
