package com.risesin.agent.service;

import com.risesin.agent.dao.ComRoleDao;
import com.risesin.agent.entity.ComRole;
import com.risesin.agent.entity.vo.ComRoleVO;
import com.risesin.agent.wrapper.ComRoleWrapper;
import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.core.tool.node.ForestNodeMerger;
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
 * comRole服务层
 *
 * @author Administrator
 */
@Service
public class ComRoleService {

    @Autowired
    private ComRoleDao comRoleDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ComRole> findAll() {
        return comRoleDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ComRole> findSearch(Map whereMap, int page, int size) {
        Specification<ComRole> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return comRoleDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ComRole> findSearch(Map whereMap) {
        Specification<ComRole> specification = createSpecification(whereMap);
        return comRoleDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkRoleId
     * @return
     */
    public ComRole findById(Integer pkRoleId) {
        return comRoleDao.findById(ComRole.class, pkRoleId);
    }

    /**
     * 增加
     *
     * @param comRole
     */
    public void add(ComRole comRole) {
        // comRole.setPkRoleId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        comRoleDao.save(comRole);
    }

    /**
     * 修改
     *
     * @param comRole
     */
    @Transactional
    public void update(ComRole comRole) {
        comRoleDao.update(comRole, comRole.getId());
    }

    /**
     * 删除
     *
     * @param pkRoleId
     */
    @Transactional
    public Boolean deleteById(Integer pkRoleId) {
        return comRoleDao.updateStatus(ComRole.class, pkRoleId, SqlConstant.LOGOUT_OR_DELETE);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ComRole> createSpecification(Map searchMap) {

        return new Specification<ComRole>() {

            @Override
            public Predicate toPredicate(Root<ComRole> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 角色名称
                if (searchMap.get("roleName") != null && !"".equals(searchMap.get("roleName"))) {
                    predicateList.add(cb.like(root.get("roleName").as(String.class), "%" + (String) searchMap.get("roleName") + "%"));
                }
                // 角色标识
                if (searchMap.get("sign") != null && !"".equals(searchMap.get("sign"))) {
                    predicateList.add(cb.like(root.get("sign").as(String.class), "%" + (String) searchMap.get("sign") + "%"));
                }
                // 排序
                if (searchMap.get("sort") != null && !"".equals(searchMap.get("sort"))) {
                    predicateList.add(cb.like(root.get("sort").as(String.class), "%" + (String) searchMap.get("sort") + "%"));
                }
                // id
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (Integer) searchMap.get("id") + "%"));
                }
                // 父主键
                if (searchMap.get("parentId") != null && !"".equals(searchMap.get("parentId"))) {
                    predicateList.add(cb.like(root.get("parentId").as(String.class), "%" + (Integer) searchMap.get("parentId") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    /**
     * 树形结构
     *
     * @return
     */
    public List<ComRoleVO> tree() {
        List<ComRole> comRoleList = comRoleDao.findAllForHasStatus(ComRole.class, true);
        return ForestNodeMerger.merge(ComRoleWrapper.build().listNodeVO(comRoleList));
    }

    public Boolean removeByIds(List<Integer> ids) {
        return comRoleDao.removeBatch(ComRole.class, ids, SqlConstant.UPDATE);
    }

    public void updateStatus(Integer id, Byte status) {
        comRoleDao.updateStatus(ComRole.class, id, status);
    }
}
