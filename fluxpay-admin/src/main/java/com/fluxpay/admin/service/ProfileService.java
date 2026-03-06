package com.fluxpay.admin.service;

import com.fluxpay.admin.dto.menu.MenuTreeVO;
import com.fluxpay.admin.dto.profile.ProfileVO;

import java.util.List;

/**
 * 当前登录用户 Service
 */
public interface ProfileService {

    /**
     * 获取当前登录用户完整信息（含角色列表）
     */
    ProfileVO getProfile();

    /**
     * 获取当前用户可访问的菜单树
     *
     * <p>仅返回类型为目录（1）和菜单（2）的节点，按钮/接口节点不返回给前端。
     */
    List<MenuTreeVO> getMenus();
}
