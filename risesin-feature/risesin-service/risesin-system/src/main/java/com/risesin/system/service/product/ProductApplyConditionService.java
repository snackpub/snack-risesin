package com.risesin.system.service.product;

import com.risesin.system.dao.product.ProductApplyConditionDao;
import com.risesin.systemapi.product.entity.ProductApplyCondition;
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
 * productApplyCondition服务层
 *
 * @author Administrator
 */
@Service
public class ProductApplyConditionService {

    @Autowired
    private ProductApplyConditionDao productApplyConditionDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ProductApplyCondition> findAll() {
        return productApplyConditionDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ProductApplyCondition> findSearch(Map whereMap, int page, int size) {
        Specification<ProductApplyCondition> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return productApplyConditionDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ProductApplyCondition> findSearch(Map whereMap) {
        Specification<ProductApplyCondition> specification = createSpecification(whereMap);
        return productApplyConditionDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ProductApplyCondition findById(String pkId) {
        return productApplyConditionDao.findById(ProductApplyCondition.class, pkId);
    }

    /**
     * 增加
     *
     * @param productApplyCondition
     */
    public void add(ProductApplyCondition productApplyCondition) {
        // productApplyCondition.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        productApplyConditionDao.save(productApplyCondition);
    }

    /**
     * 修改
     *
     * @param productApplyCondition
     */
    public void update(ProductApplyCondition productApplyCondition) {
        productApplyConditionDao.save(productApplyCondition);
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(String pkId) {
        productApplyConditionDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ProductApplyCondition> createSpecification(Map searchMap) {

        return new Specification<ProductApplyCondition>() {

            @Override
            public Predicate toPredicate(Root<ProductApplyCondition> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 排除行业(,分割)
                if (searchMap.get("excludeIndustry") != null && !"".equals(searchMap.get("excludeIndustry"))) {
                    predicateList.add(cb.like(root.get("excludeIndustry").as(String.class), "%" + (String) searchMap.get("excludeIndustry") + "%"));
                }
                // 可选地区(,分割)
                if (searchMap.get("area") != null && !"".equals(searchMap.get("area"))) {
                    predicateList.add(cb.like(root.get("area").as(String.class), "%" + (String) searchMap.get("area") + "%"));
                }
                // 开票
                if (searchMap.get("invoice") != null && !"".equals(searchMap.get("invoice"))) {
                    predicateList.add(cb.like(root.get("invoice").as(String.class), "%" + (String) searchMap.get("invoice") + "%"));
                }
                // 征信逾期
                if (searchMap.get("creditReportingTime") != null && !"".equals(searchMap.get("creditReportingTime"))) {
                    predicateList.add(cb.like(root.get("creditReportingTime").as(String.class), "%" + (String) searchMap.get("creditReportingTime") + "%"));
                }
                // 增值税实际纳税
                if (searchMap.get("vatMoney") != null && !"".equals(searchMap.get("vatMoney"))) {
                    predicateList.add(cb.like(root.get("vatMoney").as(String.class), "%" + (String) searchMap.get("vatMoney") + "%"));
                }
                // 1年纳税评级：A级 B级 C级 D级 M级 以上
                if (searchMap.get("nearlyOneYearTaxRating") != null && !"".equals(searchMap.get("nearlyOneYearTaxRating"))) {
                    predicateList.add(cb.like(root.get("nearlyOneYearTaxRating").as(String.class), "%" + (String) searchMap.get("nearlyOneYearTaxRating") + "%"));
                }
                // 网贷次数
                if (searchMap.get("personLoans") != null && !"".equals(searchMap.get("personLoans"))) {
                    predicateList.add(cb.like(root.get("personLoans").as(String.class), "%" + (String) searchMap.get("personLoans") + "%"));
                }
                // 其他
                if (searchMap.get("other") != null && !"".equals(searchMap.get("other"))) {
                    predicateList.add(cb.like(root.get("other").as(String.class), "%" + (String) searchMap.get("other") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
