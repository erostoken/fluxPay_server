package com.fluxpay.admin.domain.vo.req.menu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 新建 / 修改菜单请求
 */
@Data
public class MenuSaveReq {

    /** 父级 ID，顶级菜单传 0 */
    @NotNull(message = "父级 ID 不能为空")
    private Long parentId;

    /** 菜单名称 */
    @NotBlank(message = "菜单名称不能为空")
    private String menuName;

    /**
     * 类型：1-目录 2-菜单 3-按钮 4-接口
     */
    @NotNull(message = "类型不能为空")
    private Integer type;

    /** 前端路由路径（目录/菜单类型填写） */
    private String path;

    /** 前端组件路径（菜单类型填写） */
    private String component;

    /** 权限标识，如：user:list（按钮/接口类型填写） */
    private String permission;

    /** 图标 */
    private String icon;

    /** 排序号，数字越小越靠前 */
    private Integer sortOrder;

    /** 状态：0-禁用 1-启用，默认启用 */
    private Integer status = 1;
}
