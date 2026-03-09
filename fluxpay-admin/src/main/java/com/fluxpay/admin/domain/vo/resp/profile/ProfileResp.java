package com.fluxpay.admin.domain.vo.resp.profile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fluxpay.admin.domain.vo.resp.menu.MenuTreeResp;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 当前登录用户信息响应
 */
@Data
public class ProfileResp {

    private Long    id;
    private String  username;
    private String  phone;
    /** 状态：0-禁用 1-启用 */
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date lastLoginTime;

    /** 拥有的角色列表 */
    private List<RoleVO> roles;

    /** 前端可访问的菜单树（仅目录和菜单类型，不包含按钮/接口） */
    private List<MenuTreeResp> menus;

    @Data
    public static class RoleVO {
        private Long   id;
        private String roleName;
        private String roleCode;
    }
}
