package com.risesin.system.service.plan;

import com.risesin.core.tool.api.R;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.plan.FinancingDemandDao;
import com.risesin.systemapi.plan.entity.FinancingDemand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 融资需求服务层
 *
 * @author Administrator
 */
@Service
public class FinancingDemandService {

    @Autowired
    private FinancingDemandDao financingDemandDao;


    public List<FinancingDemand> findAll() {
        return financingDemandDao.findAll();
    }


    public Page<FinancingDemand> findSearch(Map whereMap, int page, int size) {
        Specification<FinancingDemand> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return financingDemandDao.findAll(specification, pageRequest);
    }


    public List<FinancingDemand> findSearch(Map whereMap) {
        Specification<FinancingDemand> specification = createSpecification(whereMap);
        return financingDemandDao.findAll(specification);
    }


    public FinancingDemand findById(Integer pkId) {
        return SqlHelper.optional(financingDemandDao.findById(pkId));
    }

    public R<BigDecimal> getAmount(Integer id) {
        return financingDemandDao.getAmount(id);
    }


    public void add(FinancingDemand financingDemand) {
        financingDemandDao.save(financingDemand);
    }


    public void update(FinancingDemand fd) {
        financingDemandDao.update(fd, fd.getId());
    }


    public void deleteById(Integer id) {
        financingDemandDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<FinancingDemand> createSpecification(Map searchMap) {

        return new Specification<FinancingDemand>() {

            @Override
            public Predicate toPredicate(Root<FinancingDemand> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
