package com.risesin.agent.service;

import com.risesin.agent.dao.ComPermissionRoleDao;
import com.risesin.agent.entity.ComPermissionRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * comPermissionRole服务层
 *
 * @author Administrator
 */
@Service
public class ComPermissionRoleService {

    @Autowired
    private ComPermissionRoleDao comPermissionRoleDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ComPermissionRole> findAll() {
        return comPermissionRoleDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ComPermissionRole> findSearch(Map whereMap, int page, int size) {
        Specification<ComPermissionRole> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return comPermissionRoleDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ComPermissionRole> findSearch(Map whereMap) {
        Specification<ComPermissionRole> specification = createSpecification(whereMap);
        return comPermissionRoleDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ComPermissionRole findById(Integer pkId) {
        return comPermissionRoleDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param comPermissionRole
     */
    public void add(ComPermissionRole comPermissionRole) {
        // comPermissionRole.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        comPermissionRoleDao.save(comPermissionRole);
    }

    /**
     * 修改
     *
     * @param comPermissionRole
     */
    @Transactional
    public void update(ComPermissionRole comPermissionRole) {
        comPermissionRoleDao.update(comPermissionRole, comPermissionRole.getId());
    }

    /**
     * 删除
     *
     * @param pkId
     */
    @Transactional
    public void deleteById(Integer pkId) {
        comPermissionRoleDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ComPermissionRole> createSpecification(Map searchMap) {

        return new Specification<ComPermissionRole>() {

            @Override
            public Predicate toPredicate(Root<ComPermissionRole> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
