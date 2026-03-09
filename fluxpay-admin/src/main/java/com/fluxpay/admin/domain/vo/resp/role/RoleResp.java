package com.fluxpay.admin.domain.vo.resp.role;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 角色信息响应
 */
@Data
public class RoleResp {

    private Long id;

    /** 角色名称 */
    private String roleName;

    /** 角色标识 */
    private String roleCode;

    /** 角色描述 */
    private String description;

    /** 状态：0-禁用 1-启用 */
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createdTime;

    /** 拥有的菜单 ID 列表（仅详情接口返回） */
    private List<Long> menuIds;
}
