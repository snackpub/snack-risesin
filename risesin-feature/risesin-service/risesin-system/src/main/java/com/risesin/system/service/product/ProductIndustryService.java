package com.risesin.system.service.product;

import com.risesin.system.dao.product.ProductIndustryDao;
import com.risesin.systemapi.product.entity.ProductIndustry;
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
 * productIndustry服务层
 *
 * @author Administrator
 */
@Service
public class ProductIndustryService {

    @Autowired
    private ProductIndustryDao productIndustryDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ProductIndustry> findAll() {
        return productIndustryDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ProductIndustry> findSearch(Map whereMap, int page, int size) {
        Specification<ProductIndustry> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return productIndustryDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ProductIndustry> findSearch(Map whereMap) {
        Specification<ProductIndustry> specification = createSpecification(whereMap);
        return productIndustryDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ProductIndustry findById(Integer pkId) {
        return productIndustryDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param productIndustry
     */
    public void add(ProductIndustry productIndustry) {
        // productIndustry.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        productIndustryDao.save(productIndustry);
    }

    /**
     * 修改
     *
     * @param productIndustry
     */
    public void update(ProductIndustry productIndustry) {
        productIndustryDao.save(productIndustry);
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(Integer pkId) {
        productIndustryDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ProductIndustry> createSpecification(Map searchMap) {

        return new Specification<ProductIndustry>() {

            @Override
            public Predicate toPredicate(Root<ProductIndustry> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
