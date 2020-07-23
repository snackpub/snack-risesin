package com.risesin.system.service.plan;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.plan.EnterpriseCreditInfoDao;
import com.risesin.systemapi.plan.entity.EnterpriseCreditInfo;
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
 * enterpriseCreditInfo服务层
 *
 * @author Administrator
 */
@Service
public class EnterpriseCreditInfoService extends RisesinBaseServiceImpl<EnterpriseCreditInfoDao, EnterpriseCreditInfo, Integer> {

    @Autowired
    private EnterpriseCreditInfoDao enterpriseCreditInfoDao;


    public List<EnterpriseCreditInfo> findAll() {
        return enterpriseCreditInfoDao.findAll();
    }


    public Page<EnterpriseCreditInfo> findSearch(Map whereMap, int page, int size) {
        Specification<EnterpriseCreditInfo> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return enterpriseCreditInfoDao.findAll(specification, pageRequest);
    }


    public List<EnterpriseCreditInfo> findSearch(Map whereMap) {
        Specification<EnterpriseCreditInfo> specification = createSpecification(whereMap);
        return enterpriseCreditInfoDao.findAll(specification);
    }


    @Override
    public EnterpriseCreditInfo findById(Integer id) {
        return SqlHelper.optional(enterpriseCreditInfoDao.findById(id));
    }


    @Override
    public void add(EnterpriseCreditInfo enterpriseCreditInfo) {
        enterpriseCreditInfoDao.save(enterpriseCreditInfo);
    }

    public void update(EnterpriseCreditInfo enterpriseCreditInfo) {
        enterpriseCreditInfoDao.update(enterpriseCreditInfo, enterpriseCreditInfo.getId());
    }


    public void deleteById(Integer pkId) {
        enterpriseCreditInfoDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<EnterpriseCreditInfo> createSpecification(Map searchMap) {

        return new Specification<EnterpriseCreditInfo>() {

            @Override
            public Predicate toPredicate(Root<EnterpriseCreditInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }


}
