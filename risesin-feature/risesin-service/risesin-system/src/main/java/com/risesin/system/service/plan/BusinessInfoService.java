package com.risesin.system.service.plan;

import com.risesin.system.dao.plan.BusinessInfoDao;
import com.risesin.systemapi.plan.entity.BusinessInfo;
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
 * businessInfo服务层
 *
 * @author Administrator
 */
@Service
public class BusinessInfoService {

    @Autowired
    private BusinessInfoDao businessInfoDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<BusinessInfo> findAll() {
        return businessInfoDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<BusinessInfo> findSearch(Map whereMap, int page, int size) {
        Specification<BusinessInfo> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return businessInfoDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<BusinessInfo> findSearch(Map whereMap) {
        Specification<BusinessInfo> specification = createSpecification(whereMap);
        return businessInfoDao.findAll(specification);
    }

    public BusinessInfo findById(Integer id) {
        return businessInfoDao.findById(BusinessInfo.class, id);
    }

    public void add(BusinessInfo businessInfo) {
        businessInfoDao.save(businessInfo);
    }


    public void update(BusinessInfo info) {
        businessInfoDao.update(info, info.getId());
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(Integer pkId) {
        businessInfoDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<BusinessInfo> createSpecification(Map searchMap) {

        return new Specification<BusinessInfo>() {

            @Override
            public Predicate toPredicate(Root<BusinessInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 本年度纳税评级。级别：A;B;C;D;M
                if (searchMap.get("thisYearTaxRate") != null && !"".equals(searchMap.get("thisYearTaxRate"))) {
                    predicateList.add(cb.like(root.get("thisYearTaxRate").as(String.class), "%" + (String) searchMap.get("thisYearTaxRate") + "%"));
                }
                // 上年度纳税评级。级别：A;B;C;D;M
                if (searchMap.get("lastYearTaxRate") != null && !"".equals(searchMap.get("lastYearTaxRate"))) {
                    predicateList.add(cb.like(root.get("lastYearTaxRate").as(String.class), "%" + (String) searchMap.get("lastYearTaxRate") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }


}
