package com.risesin.system.service.plan;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.plan.ExcludeFinancingTypeDao;
import com.risesin.systemapi.plan.entity.ExcludeFinancingType;
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
 * 排除融资预案类型服务层
 *
 * @author Administrator
 */
@Service
public class ExcludeFinancingTypeService {

    @Autowired
    private ExcludeFinancingTypeDao planFinancingTypeDao;


    public List<ExcludeFinancingType> findAll() {
        return planFinancingTypeDao.findAll();
    }


    public Page<ExcludeFinancingType> findSearch(Map whereMap, int page, int size) {
        Specification<ExcludeFinancingType> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return planFinancingTypeDao.findAll(specification, pageRequest);
    }


    public List<ExcludeFinancingType> findSearch(Map whereMap) {
        Specification<ExcludeFinancingType> specification = createSpecification(whereMap);
        return planFinancingTypeDao.findAll(specification);
    }


    public ExcludeFinancingType findById(Integer id) {
        return SqlHelper.optional(planFinancingTypeDao.findById(id));
    }


    public void add(ExcludeFinancingType planFinancingType) {
        planFinancingTypeDao.save(planFinancingType);
    }


    public void update(ExcludeFinancingType planFinancingType) {
        planFinancingTypeDao.update(planFinancingType, planFinancingType.getId());
    }

    public void deleteById(Integer id) {
        planFinancingTypeDao.deleteById(id);
    }

    public Boolean removeBatch(List<Integer> ids) {
        return planFinancingTypeDao.removeBatch(ExcludeFinancingType.class, ids, SqlConstant.UPDATE);
    }

    public Boolean saveBatch(List<ExcludeFinancingType> excludeFinancingTypes) {
        return planFinancingTypeDao.saveBatch(excludeFinancingTypes);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ExcludeFinancingType> createSpecification(Map searchMap) {

        return new Specification<ExcludeFinancingType>() {

            @Override
            public Predicate toPredicate(Root<ExcludeFinancingType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
