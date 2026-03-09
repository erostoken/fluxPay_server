package com.fluxpay.admin.service;

import com.fluxpay.admin.domain.vo.req.role.RolePageReq;
import com.fluxpay.admin.domain.vo.resp.role.RoleResp;
import com.fluxpay.admin.domain.vo.req.role.RoleSaveReq;
import com.fluxpay.common.dto.PageVO;

import java.util.List;

/**
 * 角色管理 Service
 */
public interface AdminRoleService {

    /**
     * 分页查询角色列表
     */
    PageVO<RoleResp> page(RolePageReq req);

    /**
     * 查询全部启用角色（供下拉框使用）
     */
    List<RoleResp> list();

    /**
     * 根据 ID 查询角色详情（含菜单 ID 列表）
     */
    RoleResp getById(Long id);

    /**
     * 新建角色
     *
     * @return 新角色 ID
     */
    Long save(RoleSaveReq req);

    /**
     * 修改角色基本信息
     */
    void update(Long id, RoleSaveReq req);

    /**
     * 启用 / 禁用角色
     *
     * @param status 0-禁用 1-启用
     */
    void changeStatus(Long id, Integer status);

    /**
     * 逻辑删除角色（已分配用户时拒绝）
     */
    void delete(Long id);

    /**
     * 为角色分配菜单权限（全量替换）
     */
    void assignMenus(Long roleId, List<Long> menuIds);
}
