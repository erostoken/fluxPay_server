package com.fluxpay.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fluxpay.admin.dto.role.RolePageReqDTO;
import com.fluxpay.admin.dto.role.RoleRespVO;
import com.fluxpay.admin.dto.role.RoleSaveReqDTO;
import com.fluxpay.admin.service.AdminRoleService;
import com.fluxpay.common.dto.PageVO;
import com.fluxpay.common.exception.BusinessException;
import com.fluxpay.common.result.ResultCode;
import com.fluxpay.common.utils.PageUtils;
import com.fluxpay.core.orm.sys.entity.SysRole;
import com.fluxpay.core.orm.sys.entity.SysRoleMenu;
import com.fluxpay.core.orm.sys.entity.SysUserRole;
import com.fluxpay.core.orm.sys.service.SysRoleMenuService;
import com.fluxpay.core.orm.sys.service.SysRoleService;
import com.fluxpay.core.orm.sys.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminRoleServiceImpl implements AdminRoleService {

    private final SysRoleService     sysRoleService;
    private final SysRoleMenuService sysRoleMenuService;
    private final SysUserRoleService sysUserRoleService;

    @Override
    public PageVO<RoleRespVO> page(RolePageReqDTO req) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getIsDeleted, 0)
                .like(StringUtils.hasText(req.getRoleName()), SysRole::getRoleName, req.getRoleName())
                .like(StringUtils.hasText(req.getRoleCode()), SysRole::getRoleCode, req.getRoleCode())
                .eq(req.getStatus() != null, SysRole::getStatus, req.getStatus())
                .orderByDesc(SysRole::getCreatedTime);

        IPage<SysRole> pageResult = sysRoleService.page(
                new Page<>(req.getPage(), req.getSize()), wrapper);

        return PageUtils.toVO(pageResult, this::toRespVO);
    }

    @Override
    public List<RoleRespVO> list() {
        List<SysRole> roles = sysRoleService.list(
                new LambdaQueryWrapper<SysRole>()
                        .eq(SysRole::getIsDeleted, 0)
                        .eq(SysRole::getStatus, 1)
                        .orderByDesc(SysRole::getCreatedTime));
        return roles.stream().map(this::toRespVO).collect(Collectors.toList());
    }

    @Override
    public RoleRespVO getById(Long id) {
        SysRole role = getExistRole(id);
        RoleRespVO vo = toRespVO(role);
        // 查询已绑定的菜单 ID 列表
        List<Long> menuIds = sysRoleMenuService
                .list(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, id))
                .stream()
                .map(SysRoleMenu::getMenuId)
                .collect(Collectors.toList());
        vo.setMenuIds(menuIds);
        return vo;
    }

    @Override
    public Long save(RoleSaveReqDTO req) {
        // roleCode 唯一性校验
        long count = sysRoleService.count(
                new LambdaQueryWrapper<SysRole>()
                        .eq(SysRole::getRoleCode, req.getRoleCode())
                        .eq(SysRole::getIsDeleted, 0));
        if (count > 0) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "角色标识已存在");
        }

        SysRole role = new SysRole();
        fillFromReq(role, req);
        role.setCreatedTime(new Date());
        role.setIsDeleted(0);
        sysRoleService.save(role);
        log.info("新建角色: roleCode={}, id={}", role.getRoleCode(), role.getId());
        return role.getId();
    }

    @Override
    public void update(Long id, RoleSaveReqDTO req) {
        SysRole exist = getExistRole(id);
        // roleCode 若变更，校验唯一性
        if (!exist.getRoleCode().equals(req.getRoleCode())) {
            long count = sysRoleService.count(
                    new LambdaQueryWrapper<SysRole>()
                            .eq(SysRole::getRoleCode, req.getRoleCode())
                            .eq(SysRole::getIsDeleted, 0)
                            .ne(SysRole::getId, id));
            if (count > 0) {
                throw new BusinessException(ResultCode.BAD_REQUEST, "角色标识已存在");
            }
        }
        SysRole update = new SysRole();
        update.setId(id);
        fillFromReq(update, req);
        update.setUpdatedTime(new Date());
        sysRoleService.updateById(update);
    }

    @Override
    public void changeStatus(Long id, Integer status) {
        getExistRole(id);
        SysRole update = new SysRole();
        update.setId(id);
        update.setStatus(status);
        update.setUpdatedTime(new Date());
        sysRoleService.updateById(update);
    }

    @Override
    public void delete(Long id) {
        getExistRole(id);
        // 检查是否有用户绑定该角色
        long userCount = sysUserRoleService.count(
                new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getRoleId, id));
        if (userCount > 0) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "该角色已分配用户，无法删除");
        }
        SysRole update = new SysRole();
        update.setId(id);
        update.setIsDeleted(1);
        update.setUpdatedTime(new Date());
        sysRoleService.updateById(update);
        log.info("逻辑删除角色: id={}", id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignMenus(Long roleId, List<Long> menuIds) {
        getExistRole(roleId);
        // 删除原有菜单权限
        sysRoleMenuService.remove(
                new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId));
        // 批量插入新菜单权限
        if (menuIds != null && !menuIds.isEmpty()) {
            List<SysRoleMenu> list = menuIds.stream().map(menuId -> {
                SysRoleMenu rm = new SysRoleMenu();
                rm.setRoleId(roleId);
                rm.setMenuId(menuId);
                rm.setCreatedTime(new Date());
                return rm;
            }).collect(Collectors.toList());
            sysRoleMenuService.saveBatch(list);
        }
        log.info("分配菜单权限: roleId={}, menuCount={}", roleId, menuIds == null ? 0 : menuIds.size());
    }

    // ── private ─────────────────────────────────────────────────────────────

    private SysRole getExistRole(Long id) {
        SysRole role = sysRoleService.getById(id);
        if (role == null || role.getIsDeleted() == 1) {
            throw new BusinessException(ResultCode.NOT_FOUND);
        }
        return role;
    }

    private RoleRespVO toRespVO(SysRole role) {
        RoleRespVO vo = new RoleRespVO();
        vo.setId(role.getId());
        vo.setRoleName(role.getRoleName());
        vo.setRoleCode(role.getRoleCode());
        vo.setDescription(role.getDescription());
        vo.setStatus(role.getStatus());
        vo.setCreatedTime(role.getCreatedTime());
        return vo;
    }

    private void fillFromReq(SysRole role, RoleSaveReqDTO req) {
        role.setRoleName(req.getRoleName());
        role.setRoleCode(req.getRoleCode());
        role.setDescription(req.getDescription());
        role.setStatus(req.getStatus());
    }
}
