package com.fluxpay.admin.service;

import com.fluxpay.admin.dto.menu.MenuSaveReqDTO;
import com.fluxpay.admin.dto.menu.MenuTreeVO;

import java.util.List;

/**
 * 系统菜单管理 Service
 */
public interface AdminMenuService {

    /**
     * 查询完整菜单树（含禁用节点）
     */
    List<MenuTreeVO> tree();

    /**
     * 根据 ID 查询单个菜单
     */
    MenuTreeVO getById(Long id);

    /**
     * 新建菜单
     *
     * @return 新菜单 ID
     */
    Long save(MenuSaveReqDTO req);

    /**
     * 修改菜单（全字段覆盖）
     */
    void update(Long id, MenuSaveReqDTO req);

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
