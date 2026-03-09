package com.fluxpay.admin.controller.sys;

import com.fluxpay.admin.domain.vo.req.user.UserPageReq;
import com.fluxpay.admin.domain.vo.resp.user.UserResp;
import com.fluxpay.admin.domain.vo.req.user.UserSaveReq;
import com.fluxpay.admin.domain.vo.req.user.UserUpdateReq;
import com.fluxpay.admin.service.AdminUserService;
import com.fluxpay.common.dto.PageVO;
import com.fluxpay.common.result.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理接口
 *
 * <pre>
 *   GET  /sys/users                    分页查询
 *   GET  /sys/users/{id}               查询详情
 *   POST /sys/users/save                新建用户
 *   POST /sys/users/{id}/update        更新用户
 *   POST /sys/users/{id}/status        启用/禁用
 *   POST /sys/users/{id}/delete        删除用户
 *   POST /sys/users/{id}/roles         分配角色
 * </pre>
 */
@RestController
@RequestMapping("/sys/users")
@RequiredArgsConstructor
public class SysUserController {

    private final AdminUserService adminUserService;

    /** 分页查询用户列表 */
    @GetMapping("/page")
    public Result<PageVO<UserResp>> page(UserPageReq req) {
        return Result.ok(adminUserService.page(req));
    }

    /** 查询用户详情（含角色） */
    @GetMapping("/{id}")
    public Result<UserResp> getById(@PathVariable Long id) {
        return Result.ok(adminUserService.getById(id));
    }

    /** 新建用户 */
    @PostMapping("/save")
    public Result<Long> save(@Valid @RequestBody UserSaveReq req) {
        return Result.ok(adminUserService.save(req));
    }

    /** 更新用户信息 */
    @PostMapping("/{id}/update")
    public Result<Void> update(@PathVariable Long id,
                               @Valid @RequestBody UserUpdateReq req) {
        adminUserService.update(id, req);
        return Result.ok();
    }

    /** 启用 / 禁用用户  POST /users/{id}/status  body: {"value": 0|1} */
    @PostMapping("/{id}/status")
    public Result<Void> changeStatus(@PathVariable Long id,
                                     @RequestParam Integer value) {
        adminUserService.changeStatus(id, value);
        return Result.ok();
    }

    /** 逻辑删除用户 */
    @PostMapping("/{id}/delete")
    public Result<Void> delete(@PathVariable Long id) {
        adminUserService.delete(id);
        return Result.ok();
    }

    /** 分配角色（全量替换） */
    @PostMapping("/{id}/roles")
    public Result<Void> assignRoles(@PathVariable Long id,
                                    @RequestBody List<Long> roleIds) {
        adminUserService.assignRoles(id, roleIds);
        return Result.ok();
    }
}
