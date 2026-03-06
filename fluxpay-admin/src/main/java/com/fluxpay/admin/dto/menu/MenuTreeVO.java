package com.fluxpay.admin.dto.menu;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜单树节点 VO
 *
 * <p>根节点 children 包含所有子节点（递归结构），前端直接渲染菜单树。
 */
@Data
public class MenuTreeVO {

    private Long   id;
    private Long   parentId;
    private String menuName;
    /** 类型：1-目录 2-菜单 3-按钮 4-接口 */
    private Integer type;
    private String path;
    private String component;
    private String permission;
    private String icon;
    private Integer sortOrder;
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createdTime;

    /** 子节点列表 */
    private List<MenuTreeVO> children = new ArrayList<>();
}
