package com.fluxpay.core.orm.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 系统-菜单资源表
 * @TableName sys_menu
 */
@TableName(value ="sys_menu")
@Data
public class SysMenu {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父级ID (0为顶级)
     */
    private Long parentId;

    /**
     * 菜单/资源名称
     */
    private String menuName;

    /**
     * 前端路由路径
     */
    private String path;

    /**
     * 前端组件路径
     */
    private String component;

    /**
     * 权限标识 (如: user:list, user:delete)
     */
    private String permission;

    /**
     * 类型：1-目录 2-菜单 3-按钮 4-接口
     */
    private Integer type;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序号
     */
    private Integer sortOrder;

    /**
     * 状态：0-禁用 1-启用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 逻辑删除
     */
    private Integer isDeleted;
}