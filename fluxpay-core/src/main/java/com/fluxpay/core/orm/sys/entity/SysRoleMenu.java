package com.fluxpay.core.orm.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 系统-角色菜单关联表
 * @TableName sys_role_menu
 */
@TableName(value = "sys_role_menu")
@Data
public class SysRoleMenu {

    /** 角色ID */
    private Long roleId;

    /** 菜单ID */
    private Long menuId;

    /** 绑定时间 */
    private Date createdTime;
}
