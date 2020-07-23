package com.risesin.system.service.system;

import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.core.tool.node.ForestNodeMerger;
import com.risesin.system.dao.system.SysDeptDao;
import com.risesin.systemapi.system.entity.SysLoanAgency;
import com.risesin.system.dao.system.SysLoanAgencyDao;
import com.risesin.systemapi.system.entity.SysDept;
import com.risesin.systemapi.system.vo.DeptVO;
import lombok.AllArgsConstructor;
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
 * sysDept服务层
 *
 * @author Administrator
 */
@Service
@AllArgsConstructor
public class SysDeptService {

    private SysDeptDao sysDeptDao;

    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<SysDept> findSearch(Map whereMap, int page, int size) {
        Specification<SysDept> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<SysDept> deptPage = sysDeptDao.findAll(specification, pageRequest);
        return sysDeptDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<SysDept> findSearch(Map whereMap) {
        Specification<SysDept> specification = createSpecification(whereMap);
        return sysDeptDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public SysDept findById(Integer id) {
        return sysDeptDao.findById(SysDept.class, id);
    }


    public List<DeptVO> tree() {
        return ForestNodeMerger.merge(sysDeptDao.tree());
    }

    /**
     * 增加
     *
     * @param sysDept
     */
    public void add(SysDept sysDept) {
        sysDeptDao.save(sysDept);
    }

    /**
     * 修改
     *
     * @param sysDept
     */
    public void update(SysDept sysDept) {
        sysDeptDao.update(sysDept, sysDept.getId());
    }


    public void updateStatus(String id, Byte status) {
        sysDeptDao.updateStatus(SysDept.class, id, status);
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(Integer pkId) {
        sysDeptDao.deleteById(pkId);
    }

    public boolean removeByIds(List<Integer> ids) {
        return sysDeptDao.removeBatch(SysDept.class, ids, SqlConstant.UPDATE);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<SysDept> createSpecification(Map searchMap) {

        return new Specification<SysDept>() {

            @Override
            public Predicate toPredicate(Root<SysDept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 部门名称
                if (searchMap.get("deptName") != null && !"".equals(searchMap.get("deptName"))) {
                    predicateList.add(cb.like(root.get("deptName").as(String.class), "%" + (String) searchMap.get("deptName") + "%"));
                }
                // 状态
                if (searchMap.get("status") != null && !"".equals(searchMap.get("status"))) {
                    predicateList.add(cb.like(root.get("status").as(String.class), "%" + (String) searchMap.get("status") + "%"));
                }
                // 创建人
                if (searchMap.get("createBy") != null && !"".equals(searchMap.get("createBy"))) {
                    predicateList.add(cb.like(root.get("createUser").as(String.class), "%" + (String) searchMap.get("createBy") + "%"));
                }
                // 父主键
                if (searchMap.get("parentId") != null && !"".equals(searchMap.get("parentId"))) {
                    predicateList.add(cb.like(root.get("parentId").as(String.class), "%" + (Integer) searchMap.get("parentId") + "%"));
                }
                // id
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (Integer) searchMap.get("id") + "%"));
                }
                // 创建时间区间查询
                if (searchMap.get("startDate") != null && !"".equals(searchMap.get("startDate"))) {
                    predicateList.add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class), (String) searchMap.get("startDate")));
                }
                if (searchMap.get("endDate") != null && !"".equals(searchMap.get("endDate"))) {
                    predicateList.add(cb.lessThanOrEqualTo((root.get("createTime").as(String.class)), (String) searchMap.get("endDate")));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };
    }
}
