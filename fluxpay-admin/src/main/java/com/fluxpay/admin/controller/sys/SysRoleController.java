package com.fluxpay.admin.controller.sys;

import com.fluxpay.admin.domain.vo.req.role.RolePageReq;
import com.fluxpay.admin.domain.vo.resp.role.RoleResp;
import com.fluxpay.admin.domain.vo.req.role.RoleSaveReq;
import com.fluxpay.admin.service.AdminRoleService;
import com.fluxpay.common.dto.PageVO;
import com.fluxpay.common.result.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理接口
 *
 * <pre>
 *   GET  /sys/roles/page              分页查询角色列表
 *   GET  /sys/roles/list              查询全部启用角色（供下拉框使用）
 *   GET  /sys/roles/{id}              查询角色详情（含菜单 ID 列表）
 *   POST /sys/roles/save              新建角色
 *   POST /sys/roles/{id}/update       修改角色信息
 *   POST /sys/roles/{id}/status       启用/禁用角色
 *   POST /sys/roles/{id}/delete       删除角色
 *   POST /sys/roles/{id}/menus        分配菜单权限（全量替换）
 * </pre>
 */
@RestController
@RequestMapping("/sys/roles")
@RequiredArgsConstructor
public class SysRoleController {

    private final AdminRoleService adminRoleService;

    /** 分页查询角色列表 */
    @GetMapping("/page")
    public Result<PageVO<RoleResp>> page(RolePageReq req) {
        return Result.ok(adminRoleService.page(req));
    }

    /** 查询全部启用角色（供下拉框使用） */
    @GetMapping("/list")
    public Result<List<RoleResp>> list() {
        return Result.ok(adminRoleService.list());
    }

    /** 查询角色详情（含菜单 ID 列表） */
    @GetMapping("/{id}")
    public Result<RoleResp> getById(@PathVariable Long id) {
        return Result.ok(adminRoleService.getById(id));
    }

    /** 新建角色 */
    @PostMapping("/save")
    public Result<Long> save(@Valid @RequestBody RoleSaveReq req) {
        return Result.ok(adminRoleService.save(req));
    }

    /** 修改角色信息 */
    @PostMapping("/{id}/update")
    public Result<Void> update(@PathVariable Long id,
                               @Valid @RequestBody RoleSaveReq req) {
        adminRoleService.update(id, req);
        return Result.ok();
    }

    /** 启用 / 禁用角色  POST /roles/{id}/status?value=0|1 */
    @PostMapping("/{id}/status")
    public Result<Void> changeStatus(@PathVariable Long id,
                                     @RequestParam Integer value) {
        adminRoleService.changeStatus(id, value);
        return Result.ok();
    }

    /** 逻辑删除角色 */
    @PostMapping("/{id}/delete")
    public Result<Void> delete(@PathVariable Long id) {
        adminRoleService.delete(id);
        return Result.ok();
    }

    /** 分配菜单权限（全量替换） */
    @PostMapping("/{id}/menus")
    public Result<Void> assignMenus(@PathVariable Long id,
                                    @RequestBody List<Long> menuIds) {
        adminRoleService.assignMenus(id, menuIds);
        return Result.ok();
    }
}
