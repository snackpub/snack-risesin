package com.risesin.system.service.system;

import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.system.dao.system.SysMenuRoleDao;
import com.risesin.systemapi.system.entity.SysMenuRole;
import org.springframework.beans.factory.annotation.Autowired;
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
 * sysPermissionRole服务层
 *
 * @author Administrator
 */
@Service
public class SysMenuRoleService {

    @Autowired
    private SysMenuRoleDao sysMenuRoleDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<SysMenuRole> findAll() {
        return sysMenuRoleDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<SysMenuRole> findSearch(Map whereMap, int page, int size) {
        Specification<SysMenuRole> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return sysMenuRoleDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<SysMenuRole> findSearch(Map whereMap) {
        Specification<SysMenuRole> specification = createSpecification(whereMap);
        return sysMenuRoleDao.findAll(specification);
    }


    public SysMenuRole findById(Integer id) {
        return sysMenuRoleDao.findById(SysMenuRole.class, id);
    }


    public void add(SysMenuRole sysMenuRole) {
        sysMenuRoleDao.save(sysMenuRole);
    }


    public void update(SysMenuRole sysMenuRole) {
        sysMenuRoleDao.save(sysMenuRole);
    }


    public void deleteById(Integer pkId) {
        sysMenuRoleDao.deleteById(pkId);
    }


    public void removeByRoleId(List<Integer> roleIds) {
        sysMenuRoleDao.removeByRoleId(roleIds);
    }

    public boolean batchRemove(List<Integer> ids) {
        return sysMenuRoleDao.removeBatch(SysMenuRole.class, ids, SqlConstant.DELETE);
    }

    private Specification<SysMenuRole> createSpecification(Map searchMap) {

        return new Specification<SysMenuRole>() {

            @Override
            public Predicate toPredicate(Root<SysMenuRole> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
