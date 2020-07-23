package com.risesin.system.service.dict;

import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.system.dao.dict.FinancingTypeDao;
import com.risesin.systemapi.dict.entity.FinancingType;
import com.risesin.systemapi.dict.vo.FinancingTypeVO;
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
import java.util.stream.Collectors;

/**
 * 融资类型服务层
 *
 * @author Administrator
 */
@Service
public class FinancingTypeService {

    @Autowired
    private FinancingTypeDao financingTypeDao;

    public List<FinancingType> findAll() {
        return financingTypeDao.findAll();
    }


    public Page<FinancingType> findSearch(Map whereMap, int page, int size) {
        Specification<FinancingType> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return financingTypeDao.findAll(specification, pageRequest);
    }


    public List<FinancingType> findSearch(Map whereMap) {
        Specification<FinancingType> specification = createSpecification(whereMap);
        return financingTypeDao.findAll(specification);
    }

    public FinancingType findById(Integer id) {
        return financingTypeDao.findById(FinancingType.class, id);
    }

    public void add(FinancingType financingType) {
        financingTypeDao.save(financingType);
    }

    public void update(FinancingType financingType) {
        financingTypeDao.update(financingType, financingType.getId());
    }

    public void deleteById(Integer id) {
//        financingTypeDao.softDeleteById(FinancingType.class, id);
    }

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return bool
     */
    public Boolean removeBatch(List<Integer> ids) {
        return financingTypeDao.removeBatch(FinancingType.class, ids, SqlConstant.UPDATE);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<FinancingType> createSpecification(Map searchMap) {

        return new Specification<FinancingType>() {

            @Override
            public Predicate toPredicate(Root<FinancingType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                //状态
                if (searchMap.get("status") != null) {
                    predicateList.add(cb.equal(root.get("status").as(String.class), searchMap.get("status")));
                }

                // 类型标识
                if (searchMap.get("parentCode") != null && !"".equals(searchMap.get("parentCode"))) {
                    predicateList.add(cb.equal(root.get("parentCode").as(String.class), (String) searchMap.get("parentCode")));
                }

                if (searchMap.get("code") != null && !"".equals(searchMap.get("code"))) {
                    predicateList.add(cb.equal(root.get("code").as(String.class), (String) searchMap.get("code")));
                }

                // 融资名称
                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%" + (String) searchMap.get("name") + "%"));
                }
                // 描述
                if (searchMap.get("description") != null && !"".equals(searchMap.get("description"))) {
                    predicateList.add(cb.like(root.get("description").as(String.class), "%" + (String) searchMap.get("description") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }


    /**
     * @param parentCode
     * @return
     */
    public List<FinancingType> listByPid(String parentCode) {
        return financingTypeDao.findByParentCode(parentCode);
    }

    /**
     * 更改状态
     *
     * @param id
     * @param status
     * @return
     */
    public Boolean updateStatus(int id, Byte status) {
        return financingTypeDao.updateStatus(FinancingType.class, id, status);
    }

    public List<Map<String, Object>> tree(Byte status) {
        //List<FinancingType> list = financingTypeDao.findAllForHasStatus(FinancingType.class,true);
        List<FinancingType> list;
        if (status == null) {
            list = financingTypeDao.findAll();
        } else {
            list = financingTypeDao.findAllByStatus(status);
        }
        List<FinancingTypeVO> collect = list.stream().map(financingType -> BeanUtil.copy(financingType, FinancingTypeVO.class)).collect(Collectors.toList());
        return new TreeUtils().merge(collect);
    }

    /**
     * 根据code查询
     *
     * @param code
     * @return
     */
    public FinancingType findByCode(String code) {
        return financingTypeDao.findByCode(code);
    }
}
