package com.risesin.agent.service;

import com.risesin.agent.dao.ComUserRoleDao;
import com.risesin.agent.entity.ComUserRole;
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
 * comUserRole服务层
 *
 * @author Administrator
 */
@Service
public class ComUserRoleService {

    @Autowired
    private ComUserRoleDao comUserRoleDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ComUserRole> findAll() {
        return comUserRoleDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ComUserRole> findSearch(Map whereMap, int page, int size) {
        Specification<ComUserRole> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return comUserRoleDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ComUserRole> findSearch(Map whereMap) {
        Specification<ComUserRole> specification = createSpecification(whereMap);
        return comUserRoleDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ComUserRole findById(Integer pkId) {
        return comUserRoleDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param comUserRole
     */
    public void add(ComUserRole comUserRole) {
        // comUserRole.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        comUserRoleDao.save(comUserRole);
    }

    /**
     * 修改
     *
     * @param comUserRole
     */
    @Transactional
    public void update(ComUserRole comUserRole) {
        comUserRoleDao.update(comUserRole, comUserRole.getId());
    }

    /**
     * 删除
     *
     * @param pkId
     */
    @Transactional
    public void deleteById(Integer pkId) {
        comUserRoleDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ComUserRole> createSpecification(Map searchMap) {

        return new Specification<ComUserRole>() {

            @Override
            public Predicate toPredicate(Root<ComUserRole> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
