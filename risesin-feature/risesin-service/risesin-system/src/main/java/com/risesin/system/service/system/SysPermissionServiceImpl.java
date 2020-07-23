package com.risesin.system.service.system;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;

import com.risesin.system.dao.system.SysPermissionDao;
import com.risesin.systemapi.system.entity.SysPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
 * SysPermission的服务接口的实现类
 *
 * @author honey
 * @date 2019/12/19
 */
@Service
public class SysPermissionServiceImpl extends RisesinBaseServiceImpl<SysPermissionDao, SysPermission, Integer> implements ISysPermissionService {

    @Override
    public Page<SysPermission> findSearch(Map whereMap, Query query) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Specification<SysPermission> specification = createSpecification(whereMap);
        PageRequest pageRequest = QueryUtil.getPage(query, sort);
        return baseDao.findAll(specification, pageRequest);
    }

    @Override
    public void update(SysPermission sysPermission) {
        baseDao.update(sysPermission, sysPermission.getId());
    }


    private Specification<SysPermission> createSpecification(Map searchMap) {

        return new Specification<SysPermission>() {

            @Override
            public Predicate toPredicate(Root<SysPermission> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                /**主键ID*/
                /**权限名称*/
                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%" + (String) searchMap.get("name") + "%"));
                }
                /**权限别名*/
                if (searchMap.get("alias") != null && !"".equals(searchMap.get("alias"))) {
                    predicateList.add(cb.like(root.get("alias").as(String.class), "%" + (String) searchMap.get("alias") + "%"));
                }
                /**父主键*/
                /**所属菜单ID*/
                /**排序*/
                /**修改时间*/
                /**创建时间*/
                /**创建用户*/
                /**描述*/
                if (searchMap.get("description") != null && !"".equals(searchMap.get("description"))) {
                    predicateList.add(cb.like(root.get("description").as(String.class), "%" + (String) searchMap.get("description") + "%"));
                }
                /**状态： -1 不启用 0 启用 1 加入黑名单 2注销（删除）*/
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };
    }
}