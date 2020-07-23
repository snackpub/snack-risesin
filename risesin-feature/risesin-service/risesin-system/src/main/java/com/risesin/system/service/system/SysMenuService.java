package com.risesin.system.service.system;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.core.tool.node.ForestNodeMerger;
import com.risesin.core.tool.utils.Func;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.system.SysMenuDao;
import com.risesin.system.wrapper.system.MenuWrapper;
import com.risesin.systemapi.system.entity.SysMenu;
import com.risesin.systemapi.system.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Collectors;

/**
 * sysMenu服务层
 *
 * @author Administrator
 */
@Service
public class SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;


    /**
     * 查询全部列表
     *
     * @return
     */

    public List<SysMenu> findAll() {
        return sysMenuDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */

    public Page<SysMenu> findSearch(Map whereMap, int page, int size) {
        Specification<SysMenu> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return sysMenuDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<SysMenu> findSearch(Map whereMap) {
        Specification<SysMenu> specification = createSpecification(whereMap);
        return sysMenuDao.findAll(specification);
    }


    public SysMenu findById(Integer id) {
        return sysMenuDao.detail(id);
    }

    /**
     * 增加or修改
     *
     * @param sysMenu
     */

    public void add(SysMenu sysMenu) {
        if (null != sysMenu && sysMenu.getId() != null) {
            sysMenuDao.update(sysMenu, sysMenu.getId());
        }
        assert sysMenu != null;
        sysMenuDao.save(sysMenu);
    }


    public void update(SysMenu sysMenu) {
        //TODO
    }

    public void updateStatus(String id, Byte status) {
        sysMenuDao.updateStatus(SysMenu.class, id, status);
    }


    public void deleteById(Integer pkId) {
        sysMenuDao.deleteById(pkId);
    }

    public boolean removeByIds(List<Integer> idList) {
        return SqlHelper.retBool(sysMenuDao.deleteBatchIds(idList));
    }


    public List<MenuVO> routes(String roleId) {
        List<SysMenu> allMenus = sysMenuDao.allMenu();
        List<SysMenu> roleMenus = sysMenuDao.roleMenu(Func.toIntList(roleId));
        List<SysMenu> routes = new LinkedList<>(roleMenus);
        roleMenus.forEach(roleMenu -> recursion(allMenus, routes, roleMenu));
        routes.sort(Comparator.comparing(SysMenu::getSort));
        MenuWrapper menuWrapper = new MenuWrapper();
        List<SysMenu> collect = routes.stream().filter(x -> Func.equals(x.getCategory().intValue(), 1)).collect(Collectors.toList());
        return menuWrapper.listNodeVO(collect);
    }


    private void recursion(List<SysMenu> allMenus, List<SysMenu> routes, SysMenu roleMenu) {
        Optional<SysMenu> menu = allMenus.stream().filter(x -> Func.equals(x.getId(), roleMenu.getParentId())).findFirst();
        if (menu.isPresent() && !routes.contains(menu.get())) {
            routes.add(menu.get());
            recursion(allMenus, routes, menu.get());
        }
    }

    public List<MenuVO> tree() {
        return ForestNodeMerger.merge(sysMenuDao.tree());
    }

    public List<MenuVO> grantTree() {
        return ForestNodeMerger.merge(sysMenuDao.grantTree());
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<SysMenu> createSpecification(Map searchMap) {

        return new Specification<SysMenu>() {

            @Override
            public Predicate toPredicate(Root<SysMenu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 菜单别名
                if (searchMap.get("alias") != null && !"".equals(searchMap.get("alias"))) {
                    predicateList.add(cb.like(root.get("alias").as(String.class), "%" + (String) searchMap.get("alias") + "%"));
                }
                // 菜单名称
                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%" + (String) searchMap.get("name") + "%"));
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
                // id
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "" + Func.toInt(searchMap.get("id"))));
                }
                // 父主键
                if (searchMap.get("parentId") != null && !"".equals(searchMap.get("parentId"))) {
                    predicateList.add(cb.like(root.get("parentId").as(String.class), "" + Func.toInt(searchMap.get("parentId"))));
                }
                // 创建用户
                if (searchMap.get("createBy") != null && !"".equals(searchMap.get("createBy"))) {
                    predicateList.add(cb.like(root.get("createUser").as(String.class), "%" + (String) searchMap.get("createBy") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
