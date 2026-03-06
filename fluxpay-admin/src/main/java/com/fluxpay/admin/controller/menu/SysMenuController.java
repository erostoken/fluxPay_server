package com.fluxpay.admin.controller.menu;

import com.fluxpay.admin.dto.menu.MenuSaveReqDTO;
import com.fluxpay.admin.dto.menu.MenuTreeVO;
import com.fluxpay.admin.service.AdminMenuService;
import com.fluxpay.common.result.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统菜单管理接口
 *
 * <pre>
 *   GET  /menus              查询完整菜单树
 *   GET  /menus/{id}         查询单个菜单
 *   POST /menus/save         新建菜单
 *   POST /menus/{id}/update  修改菜单
 *   POST /menus/{id}/delete  删除菜单
 *   POST /menus/{id}/status  启用/禁用菜单
 * </pre>
 */
@RestController
@RequestMapping("/sys/menus")
@RequiredArgsConstructor
public class SysMenuController {

    private final AdminMenuService adminMenuService;

    /** 查询完整菜单树 */
    @GetMapping
    public Result<List<MenuTreeVO>> tree() {
        return Result.ok(adminMenuService.tree());
    }

    /** 查询单个菜单详情 */
    @GetMapping("/{id}")
    public Result<MenuTreeVO> getById(@PathVariable Long id) {
        return Result.ok(adminMenuService.getById(id));
    }

    /** 新建菜单 */
    @PostMapping("/save")
    public Result<Long> save(@Valid @RequestBody MenuSaveReqDTO req) {
        return Result.ok(adminMenuService.save(req));
    }

    /** 修改菜单 */
    @PostMapping("/{id}/update")
    public Result<Void> update(@PathVariable Long id,
                               @Valid @RequestBody MenuSaveReqDTO req) {
        adminMenuService.update(id, req);
        return Result.ok();
    }

    /** 删除菜单（有子菜单时拒绝） */
    @PostMapping("/{id}/delete")
    public Result<Void> delete(@PathVariable Long id) {
        adminMenuService.delete(id);
        return Result.ok();
    }

    /** 启用 / 禁用菜单  POST /menus/{id}/status?value=0|1 */
    @PostMapping("/{id}/status")
    public Result<Void> changeStatus(@PathVariable Long id,
                                     @RequestParam Integer value) {
        adminMenuService.changeStatus(id, value);
        return Result.ok();
    }
}
