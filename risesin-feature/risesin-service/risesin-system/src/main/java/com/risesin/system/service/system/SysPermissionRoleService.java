package com.risesin.system.service.system;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.system.SysPermissionRoleDao;
import com.risesin.systemapi.system.entity.SysPermissionRole;
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
public class SysPermissionRoleService {

    @Autowired
    private SysPermissionRoleDao sysPermissionRoleDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<SysPermissionRole> findAll() {
        return sysPermissionRoleDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<SysPermissionRole> findSearch(Map whereMap, int page, int size) {
        Specification<SysPermissionRole> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return sysPermissionRoleDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<SysPermissionRole> findSearch(Map whereMap) {
        Specification<SysPermissionRole> specification = createSpecification(whereMap);
        return sysPermissionRoleDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public SysPermissionRole findById(Integer pkId) {
        return sysPermissionRoleDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param sysPermissionRole
     */
    public void add(SysPermissionRole sysPermissionRole) {
        // sysPermissionRole.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        sysPermissionRoleDao.save(sysPermissionRole);
    }

    /**
     * 修改
     *
     * @param sysPermissionRole
     */
    public void update(SysPermissionRole sysPermissionRole) {
        sysPermissionRoleDao.save(sysPermissionRole);
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(Integer pkId) {
        sysPermissionRoleDao.deleteById(pkId);
    }

    public boolean removeByRoleId(List<Integer> roleIds) {
        return SqlHelper.retBool(sysPermissionRoleDao.removeByRoleId(roleIds));
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<SysPermissionRole> createSpecification(Map searchMap) {

        return new Specification<SysPermissionRole>() {

            @Override
            public Predicate toPredicate(Root<SysPermissionRole> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
