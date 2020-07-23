package com.risesin.agent.service;

import com.risesin.agent.dao.ComPermissionDao;
import com.risesin.agent.entity.ComPermission;
import com.risesin.core.launch.constant.SqlConstant;
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
 * comPermission服务层
 *
 * @author Administrator
 */
@Service
public class ComPermissionService {

    @Autowired
    private ComPermissionDao comPermissionDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ComPermission> findAll() {
        return comPermissionDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ComPermission> findSearch(Map whereMap, int page, int size) {
        Specification<ComPermission> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return comPermissionDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ComPermission> findSearch(Map whereMap) {
        Specification<ComPermission> specification = createSpecification(whereMap);
        return comPermissionDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ComPermission findById(Integer pkId) {
        return comPermissionDao.findById(ComPermission.class, pkId);
    }

    /**
     * 增加
     *
     * @param comPermission
     */
    public void add(ComPermission comPermission) {
        // comPermission.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        comPermissionDao.save(comPermission);
    }

    /**
     * 修改
     *
     * @param comPermission
     */
    @Transactional
    public void update(ComPermission comPermission) {
        comPermissionDao.update(comPermission, comPermission.getId());
    }

    /**
     * 删除
     *
     * @param pkId
     */
    @Transactional
    public void deleteById(Integer pkId) {
        comPermissionDao.updateStatus(ComPermission.class, pkId, SqlConstant.LOGOUT_OR_DELETE);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ComPermission> createSpecification(Map searchMap) {

        return new Specification<ComPermission>() {

            @Override
            public Predicate toPredicate(Root<ComPermission> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 权限名称
                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%" + (String) searchMap.get("name") + "%"));
                }
                // 权限别名
                if (searchMap.get("alias") != null && !"".equals(searchMap.get("alias"))) {
                    predicateList.add(cb.like(root.get("alias").as(String.class), "%" + (String) searchMap.get("alias") + "%"));
                }
                // 描述
                if (searchMap.get("description") != null && !"".equals(searchMap.get("description"))) {
                    predicateList.add(cb.like(root.get("description").as(String.class), "%" + (String) searchMap.get("description") + "%"));
                }
// id
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (Integer) searchMap.get("id") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    public Boolean removeByIds(List<Integer> ids) {
        return comPermissionDao.removeBatch(ComPermission.class, ids, SqlConstant.UPDATE);
    }

    public void updateStatus(Integer id, Byte status) {
        comPermissionDao.updateStatus(ComPermission.class, id, status);
    }
}
