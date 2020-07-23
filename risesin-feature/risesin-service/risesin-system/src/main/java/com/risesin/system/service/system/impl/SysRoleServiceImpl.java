package com.risesin.system.service.system.impl;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.tool.node.ForestNodeMerger;
import com.risesin.core.tool.utils.Func;
import com.risesin.system.dao.system.SysRoleDao;
import com.risesin.system.service.system.ISysRoleService;
import com.risesin.system.service.system.SysMenuRoleService;
import com.risesin.system.service.system.SysPermissionRoleService;
import com.risesin.systemapi.system.entity.SysMenuRole;
import com.risesin.systemapi.system.entity.SysPermissionRole;
import com.risesin.systemapi.system.entity.SysRole;
import com.risesin.systemapi.system.vo.RoleVO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * sysRole服务层
 *
 * @author Administrator
 */
@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends RisesinBaseServiceImpl<SysRoleDao, SysRole, Integer> implements ISysRoleService {

    private SysMenuRoleService menuRoleService;
    private SysPermissionRoleService permissionRoleService;

    @Override
    public Page<SysRole> findSearch(Map whereMap, Query query) {
        Specification<SysRole> specification = createSpecification(whereMap);
        PageRequest pageRequest = QueryUtil.getPage(query);
        return baseDao.findAll(specification, pageRequest);
    }


    @Override
    public SysRole findById(Integer id) {
        return baseDao.detail(id);
    }

    @Override
    public List<RoleVO> tree() {
        return ForestNodeMerger.merge(baseDao.tree());
    }


    @Override
    public boolean grant(List<Integer> roleIds, List<Integer> menuIds) {
        // 删除角色配置的菜单集合
        menuRoleService.removeByRoleId(roleIds);
        // 组装配置
        List<SysMenuRole> roleMenus = new ArrayList<>();
        roleIds.forEach(roleId -> menuIds.forEach(menuId -> {
            SysMenuRole roleMenu = new SysMenuRole();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenus.add(roleMenu);
        }));
        // 新增配置
        baseDao.saveBatch(roleMenus);
        return true;
    }

    @Override
    public boolean authority(List<Integer> roleIds, List<Integer> permissionIds) {
        permissionRoleService.removeByRoleId(roleIds);
        List<SysPermissionRole> rolePermissions = new ArrayList<>();
        roleIds.forEach(roleId -> permissionIds.forEach(permissionId -> {
            SysPermissionRole rolePermission = new SysPermissionRole();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissions.add(rolePermission);
        }));
        baseDao.saveBatch(rolePermissions);
        return true;
    }

    /**
     * 动态条件构建
     *
     * @param searchMap 条件
     * @return Specification<SysRole>
     */
    private Specification<SysRole> createSpecification(Map searchMap) {

        return new Specification<SysRole>() {

            @Override
            public Predicate toPredicate(Root<SysRole> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 角色名称
                if (searchMap.get("roleName") != null && !"".equals(searchMap.get("roleName"))) {
                    predicateList.add(cb.like(root.get("roleName").as(String.class), "%" + (String) searchMap.get("roleName") + "%"));
                }
                // 角色别名
                if (searchMap.get("roleAlias") != null && !"".equals(searchMap.get("roleAlias"))) {
                    predicateList.add(cb.like(root.get("roleAlias").as(String.class), "%" + (String) searchMap.get("roleAlias") + "%"));
                }
                // 创建时间区间查询
                if (searchMap.get("startDate") != null && !"".equals(searchMap.get("startDate"))) {
                    predicateList.add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class), (String) searchMap.get("startDate")));
                }
                if (searchMap.get("endDate") != null && !"".equals(searchMap.get("endDate"))) {
                    predicateList.add(cb.lessThanOrEqualTo((root.get("createTime").as(String.class)), (String) searchMap.get("endDate")));
                }
                // 状态
                if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
                    predicateList.add(cb.like(root.get("status").as(String.class), "%" + (String) searchMap.get("state") + "%"));
                }
                // 创建用户
                if (searchMap.get("createBy") != null && !"".equals(searchMap.get("createBy"))) {
                    predicateList.add(cb.like(root.get("createUser").as(String.class), Func.toInt(searchMap.get("createBy")) + ""));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
