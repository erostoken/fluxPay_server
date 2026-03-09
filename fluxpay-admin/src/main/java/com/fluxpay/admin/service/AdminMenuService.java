package com.fluxpay.admin.service;

import com.fluxpay.admin.domain.vo.req.menu.MenuSaveReq;
import com.fluxpay.admin.domain.vo.resp.menu.MenuTreeResp;

import java.util.List;

/**
 * 系统菜单管理 Service
 */
public interface AdminMenuService {

    /**
     * 查询完整菜单树（含禁用节点）
     */
    List<MenuTreeResp> tree();

    /**
     * 根据 ID 查询单个菜单
     */
    MenuTreeResp getById(Long id);

    /**
     * 新建菜单
     *
     * @return 新菜单 ID
     */
    Long save(MenuSaveReq req);

    /**
     * 修改菜单（全字段覆盖）
     */
    void update(Long id, MenuSaveReq req);

    /**
     * 删除菜单（若存在子菜单，则拒绝删除）
     */
    void delete(Long id);

    /**
     * 启用 / 禁用菜单
     *
     * @param status 0-禁用 1-启用
     */
    void changeStatus(Long id, Integer status);
}
