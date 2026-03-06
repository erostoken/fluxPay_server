package com.fluxpay.admin.service;

import com.fluxpay.admin.dto.user.UserPageReqDTO;
import com.fluxpay.admin.dto.user.UserRespVO;
import com.fluxpay.admin.dto.user.UserSaveReqDTO;
import com.fluxpay.admin.dto.user.UserUpdateReqDTO;
import com.fluxpay.common.dto.PageVO;

import java.util.List;

/**
 * 管理员用户管理 Service
 */
public interface AdminUserService {

    /**
     * 分页查询用户列表
     */
    PageVO<UserRespVO> page(UserPageReqDTO req);

    /**
     * 根据 ID 查询用户详情（含角色列表）
     */
    UserRespVO getById(Long id);

    /**
     * 新建管理员用户
     *
     * @return 新用户 ID
     */
    Long save(UserSaveReqDTO req);

    /**
     * 更新用户信息（手机号 / 密码 / 状态）
     */
    void update(Long id, UserUpdateReqDTO req);

    /**
     * 启用 / 禁用用户
     *
     * @param status 0-禁用 1-启用
     */
    void changeStatus(Long id, Integer status);

    /**
     * 逻辑删除用户
     */
    void delete(Long id);

    /**
     * 为用户分配角色（全量替换）
     */
    void assignRoles(Long userId, List<Long> roleIds);
}
