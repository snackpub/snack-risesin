package com.risesin.agent.service;

import com.risesin.agent.dao.ComDeptDao;
import com.risesin.agent.entity.ComDept;
import com.risesin.agent.entity.vo.ComDeptVO;
import com.risesin.agent.wrapper.ComDeptWrapper;
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
 * comDept服务层
 *
 * @author Administrator
 */
@Service
public class ComDeptService {

    @Autowired
    private ComDeptDao comDeptDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ComDept> findAll() {
        return comDeptDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ComDept> findSearch(Map whereMap, int page, int size) {
        Specification<ComDept> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return comDeptDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ComDept> findSearch(Map whereMap) {
        Specification<ComDept> specification = createSpecification(whereMap);
        return comDeptDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public ComDept findById(Integer id) {

        return comDeptDao.findById(ComDept.class, id);
    }

    /**
     * 增加
     *
     * @param comDept
     */
    public void add(ComDept comDept) {
        // comDept.setid( idWorker.nextId()+"" ); 雪花分布式ID生成器
        comDeptDao.save(comDept);
    }

    /**
     * 修改
     *
     * @param comDept
     */
    @Transactional
    public void update(ComDept comDept) {
        comDeptDao.update(comDept, comDept.getId());
    }

    /**
     * 删除
     *
     * @param id
     */
    @Transactional
    public void deleteById(Integer id) {
        comDeptDao.updateStatus(ComDept.class, id, SqlConstant.LOGOUT_OR_DELETE);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ComDept> createSpecification(Map searchMap) {

        return new Specification<ComDept>() {

            @Override
            public Predicate toPredicate(Root<ComDept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 部门名称
                if (searchMap.get("deptName") != null && !"".equals(searchMap.get("deptName"))) {
                    predicateList.add(cb.like(root.get("deptName").as(String.class), "%" + (String) searchMap.get("deptName") + "%"));
                }
                // 父主键
                if (searchMap.get("parentId") != null && !"".equals(searchMap.get("parentId"))) {
                    predicateList.add(cb.like(root.get("parentId").as(String.class), "%" + (Integer) searchMap.get("parentId") + "%"));
                }
                // id
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (Integer) searchMap.get("id") + "%"));
                }
                // 描述
                if (searchMap.get("description") != null && !"".equals(searchMap.get("description"))) {
                    predicateList.add(cb.like(root.get("description").as(String.class), "%" + (String) searchMap.get("description") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    public List<ComDeptVO> tree() {
        List<ComDept> comDeptList = comDeptDao.findAllForHasStatus(ComDept.class, true);
        return ForestNodeMerger.merge(ComDeptWrapper.build().listNodeVO(comDeptList));
    }

    public boolean updateStatus(Integer id, Byte status) {
        return comDeptDao.updateStatus(ComDept.class, id, status);
    }

    public Boolean removeByIds(List<Integer> ids) {
        return comDeptDao.removeBatch(ComDept.class, ids, SqlConstant.UPDATE);
    }
}
