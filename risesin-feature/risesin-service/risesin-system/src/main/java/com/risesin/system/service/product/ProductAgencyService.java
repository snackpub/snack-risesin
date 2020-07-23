package com.risesin.system.service.product;

import com.risesin.system.dao.product.ProductAgencyDao;
import com.risesin.systemapi.product.entity.ProductAgency;
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
 * productAgency服务层
 *
 * @author Administrator
 */
@Service
public class ProductAgencyService {

    @Autowired
    private ProductAgencyDao productAgencyDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ProductAgency> findAll() {
        return productAgencyDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ProductAgency> findSearch(Map whereMap, int page, int size) {
        Specification<ProductAgency> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return productAgencyDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ProductAgency> findSearch(Map whereMap) {
        Specification<ProductAgency> specification = createSpecification(whereMap);
        return productAgencyDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ProductAgency findById(Integer pkId) {
        return productAgencyDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param productAgency
     */
    public void add(ProductAgency productAgency) {
        // productAgency.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        productAgencyDao.save(productAgency);
    }

    /**
     * 修改
     *
     * @param productAgency
     */
    public void update(ProductAgency productAgency) {
        productAgencyDao.save(productAgency);
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(Integer pkId) {
        productAgencyDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ProductAgency> createSpecification(Map searchMap) {

        return new Specification<ProductAgency>() {

            @Override
            public Predicate toPredicate(Root<ProductAgency> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
