package com.fluxpay.admin.dto.role;

import com.fluxpay.common.dto.PageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色分页查询请求
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RolePageReqDTO extends PageDTO {

    /** 角色名称（模糊匹配） */
    private String roleName;

    /** 角色标识（模糊匹配） */
    private String roleCode;

    /** 状态：0-禁用 1-启用 */
    private Integer status;
}
