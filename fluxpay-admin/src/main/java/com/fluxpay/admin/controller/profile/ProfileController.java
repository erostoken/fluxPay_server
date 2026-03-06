package com.fluxpay.admin.controller.profile;

import com.fluxpay.admin.dto.menu.MenuTreeVO;
import com.fluxpay.admin.dto.profile.ProfileVO;
import com.fluxpay.admin.service.ProfileService;
import com.fluxpay.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 当前登录用户信息接口
 *
 * <pre>
 *   GET /profile         当前用户信息（含角色 + 菜单树）
 *   GET /profile/menus   仅菜单树（前端路由动态加载）
 * </pre>
 */
@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    /**
     * 获取当前登录用户完整信息
     *
     * <p>包含：用户基本信息、角色列表、可访问菜单树。
     * 前端登录成功后通常通过此接口初始化用户状态。
     */
    @GetMapping
    public Result<ProfileVO> profile() {
        return Result.ok(profileService.getProfile());
    }

    /**
     * 获取当前用户菜单树（仅目录和菜单类型）
     *
     * <p>适用于前端动态路由生成场景，不包含按钮/接口权限节点。
     */
    @GetMapping("/menus")
    public Result<List<MenuTreeVO>> menus() {
        return Result.ok(profileService.getMenus());
    }
}
