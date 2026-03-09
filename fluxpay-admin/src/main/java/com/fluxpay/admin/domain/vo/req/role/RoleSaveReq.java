package com.fluxpay.admin.domain.vo.req.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 新建 / 修改角色请求
 */
@Data
public class RoleSaveReq {

    /** 角色名称 */
    @NotBlank(message = "角色名称不能为空")
    @Size(max = 32, message = "角色名称最长 32 位")
    private String roleName;

    /** 角色标识，如：admin、finance（唯一） */
    @NotBlank(message = "角色标识不能为空")
    @Size(max = 64, message = "角色标识最长 64 位")
    private String roleCode;

    /** 角色描述 */
    @Size(max = 200, message = "描述最长 200 位")
    private String description;

    /** 状态：0-禁用 1-启用，默认启用 */
    private Integer status = 1;
}
