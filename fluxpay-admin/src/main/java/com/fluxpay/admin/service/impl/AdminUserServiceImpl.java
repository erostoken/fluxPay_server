package com.fluxpay.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fluxpay.admin.domain.vo.req.user.UserPageReq;
import com.fluxpay.admin.domain.vo.resp.user.UserResp;
import com.fluxpay.admin.domain.vo.req.user.UserSaveReq;
import com.fluxpay.admin.domain.vo.req.user.UserUpdateReq;
import com.fluxpay.admin.service.AdminUserService;
import com.fluxpay.common.dto.PageVO;
import com.fluxpay.common.exception.BusinessException;
import com.fluxpay.common.result.ResultCode;
import com.fluxpay.common.utils.PageUtils;
import com.fluxpay.core.orm.sys.entity.SysUser;
import com.fluxpay.core.orm.sys.entity.SysUserRole;
import com.fluxpay.core.orm.sys.service.SysUserRoleService;
import com.fluxpay.core.orm.sys.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final SysUserService     sysUserService;
    private final SysUserRoleService sysUserRoleService;
    private final PasswordEncoder    passwordEncoder;

    @Override
    public PageVO<UserResp> page(UserPageReq req) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getIsDeleted, 0)
                .like(StringUtils.hasText(req.getUsername()), SysUser::getUsername, req.getUsername())
                .like(StringUtils.hasText(req.getPhone()),    SysUser::getPhone,    req.getPhone())
                .eq(req.getStatus() != null, SysUser::getStatus, req.getStatus())
                .orderByDesc(SysUser::getCreatedTime);

        IPage<SysUser> pageResult = sysUserService.page(
                new Page<>(req.getPage(), req.getSize()), wrapper);

        return PageUtils.toVO(pageResult, this::toRespVO);
    }

    @Override
    public UserResp getById(Long id) {
        SysUser user = sysUserService.getById(id);
        if (user == null || user.getIsDeleted() == 1) {
            throw new BusinessException(ResultCode.NOT_FOUND);
        }
        UserResp vo = toRespVO(user);
        // 查询角色列表
        List<Long> roleIds = sysUserRoleService
                .list(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, id))
                .stream()
                .map(SysUserRole::getRoleId)
                .collect(Collectors.toList());
        vo.setRoleIds(roleIds);
        return vo;
    }

    @Override
    public Long save(UserSaveReq req) {
        // 用户名唯一性校验
        long count = sysUserService.count(
                new LambdaQueryWrapper<SysUser>()
                        .eq(SysUser::getUsername, req.getUsername())
                        .eq(SysUser::getIsDeleted, 0));
        if (count > 0) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "用户名已存在");
        }

        SysUser user = new SysUser();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setPhone(req.getPhone());
        user.setStatus(req.getStatus());
        user.setCreatedTime(new Date());
        user.setIsDeleted(0);
        sysUserService.save(user);
        log.info("新建管理员用户: username={}, id={}", user.getUsername(), user.getId());
        return user.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long id, UserUpdateReq req) {
        SysUser user = getExistUser(id);
        SysUser update = new SysUser();
        update.setId(id);
        if (StringUtils.hasText(req.getPhone())) {
            update.setPhone(req.getPhone());
        }
        if (StringUtils.hasText(req.getPassword())) {
            update.setPassword(passwordEncoder.encode(req.getPassword()));
        }
        if (req.getStatus() != null) {
            update.setStatus(req.getStatus());
        }
        update.setUpdatedTime(new Date());
        sysUserService.updateById(update);
    }

    @Override
    public void changeStatus(Long id, Integer status) {
        getExistUser(id);
        SysUser update = new SysUser();
        update.setId(id);
        update.setStatus(status);
        update.setUpdatedTime(new Date());
        sysUserService.updateById(update);
    }

    @Override
    public void delete(Long id) {
        getExistUser(id);
        SysUser update = new SysUser();
        update.setId(id);
        update.setIsDeleted(1);
        update.setUpdatedTime(new Date());
        sysUserService.updateById(update);
        log.info("逻辑删除管理员用户: id={}", id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignRoles(Long userId, List<Long> roleIds) {
        getExistUser(userId);
        // 删除原有角色
        sysUserRoleService.remove(
                new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId));
        // 批量插入新角色
        if (roleIds != null && !roleIds.isEmpty()) {
            List<SysUserRole> list = roleIds.stream().map(roleId -> {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(userId);
                ur.setRoleId(roleId);
                ur.setCreatedTime(new Date());
                return ur;
            }).collect(Collectors.toList());
            sysUserRoleService.saveBatch(list);
        }
    }

    // ── private ────────────────────────────────────────────────────────────────

    private SysUser getExistUser(Long id) {
        SysUser user = sysUserService.getById(id);
        if (user == null || user.getIsDeleted() == 1) {
            throw new BusinessException(ResultCode.NOT_FOUND);
        }
        return user;
    }

    private UserResp toRespVO(SysUser user) {
        UserResp vo = new UserResp();
        vo.setId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setPhone(user.getPhone());
        vo.setStatus(user.getStatus());
        vo.setLastLoginTime(user.getLastLoginTime());
        vo.setLastLoginIp(user.getLastLoginIp());
        vo.setCreatedTime(user.getCreatedTime());
        return vo;
    }
}
