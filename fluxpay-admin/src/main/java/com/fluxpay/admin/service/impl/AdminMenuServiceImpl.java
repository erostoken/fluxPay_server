package com.fluxpay.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fluxpay.admin.dto.menu.MenuSaveReqDTO;
import com.fluxpay.admin.dto.menu.MenuTreeVO;
import com.fluxpay.admin.service.AdminMenuService;
import com.fluxpay.common.exception.BusinessException;
import com.fluxpay.common.result.ResultCode;
import com.fluxpay.core.orm.sys.entity.SysMenu;
import com.fluxpay.core.orm.sys.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminMenuServiceImpl implements AdminMenuService {

    private final SysMenuService sysMenuService;

    @Override
    public List<MenuTreeVO> tree() {
        List<SysMenu> all = sysMenuService.list(
                new LambdaQueryWrapper<SysMenu>()
                        .eq(SysMenu::getIsDeleted, 0)
                        .orderByAsc(SysMenu::getSortOrder)
                        .orderByAsc(SysMenu::getId));
        return buildTree(all, 0L);
    }

    @Override
    public MenuTreeVO getById(Long id) {
        SysMenu menu = getExistMenu(id);
        return toVO(menu);
    }

    @Override
    public Long save(MenuSaveReqDTO req) {
        SysMenu menu = new SysMenu();
        fillFromReq(menu, req);
        menu.setCreatedTime(new Date());
        menu.setIsDeleted(0);
        sysMenuService.save(menu);
        log.info("新建菜单: name={}, id={}", menu.getMenuName(), menu.getId());
        return menu.getId();
    }

    @Override
    public void update(Long id, MenuSaveReqDTO req) {
        getExistMenu(id);
        SysMenu menu = new SysMenu();
        menu.setId(id);
        fillFromReq(menu, req);
        sysMenuService.updateById(menu);
    }

    @Override
    public void delete(Long id) {
        getExistMenu(id);
        // 检查是否存在子菜单
        long childCount = sysMenuService.count(
                new LambdaQueryWrapper<SysMenu>()
                        .eq(SysMenu::getParentId, id)
                        .eq(SysMenu::getIsDeleted, 0));
        if (childCount > 0) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "请先删除子菜单");
        }
        SysMenu update = new SysMenu();
        update.setId(id);
        update.setIsDeleted(1);
        sysMenuService.updateById(update);
        log.info("删除菜单: id={}", id);
    }

    @Override
    public void changeStatus(Long id, Integer status) {
        getExistMenu(id);
        SysMenu update = new SysMenu();
        update.setId(id);
        update.setStatus(status);
        sysMenuService.updateById(update);
    }

    // ── private ─────────────────────────────────────────────────────────────

    /**
     * 递归构建菜单树
     */
    private List<MenuTreeVO> buildTree(List<SysMenu> all, Long parentId) {
        Map<Long, List<SysMenu>> grouped = all.stream()
                .collect(Collectors.groupingBy(SysMenu::getParentId));

        return buildChildren(grouped, parentId);
    }

    private List<MenuTreeVO> buildChildren(Map<Long, List<SysMenu>> grouped, Long parentId) {
        List<SysMenu> children = grouped.getOrDefault(parentId, new ArrayList<>());
        return children.stream().map(menu -> {
            MenuTreeVO vo = toVO(menu);
            vo.setChildren(buildChildren(grouped, menu.getId()));
            return vo;
        }).collect(Collectors.toList());
    }

    private SysMenu getExistMenu(Long id) {
        SysMenu menu = sysMenuService.getById(id);
        if (menu == null || menu.getIsDeleted() == 1) {
            throw new BusinessException(ResultCode.NOT_FOUND);
        }
        return menu;
    }

    private MenuTreeVO toVO(SysMenu menu) {
        MenuTreeVO vo = new MenuTreeVO();
        vo.setId(menu.getId());
        vo.setParentId(menu.getParentId());
        vo.setMenuName(menu.getMenuName());
        vo.setType(menu.getType());
        vo.setPath(menu.getPath());
        vo.setComponent(menu.getComponent());
        vo.setPermission(menu.getPermission());
        vo.setIcon(menu.getIcon());
        vo.setSortOrder(menu.getSortOrder());
        vo.setStatus(menu.getStatus());
        vo.setCreatedTime(menu.getCreatedTime());
        return vo;
    }

    private void fillFromReq(SysMenu menu, MenuSaveReqDTO req) {
        menu.setParentId(req.getParentId());
        menu.setMenuName(req.getMenuName());
        menu.setType(req.getType());
        menu.setPath(req.getPath());
        menu.setComponent(req.getComponent());
        menu.setPermission(req.getPermission());
        menu.setIcon(req.getIcon());
        menu.setSortOrder(req.getSortOrder());
        menu.setStatus(req.getStatus());
    }
}
