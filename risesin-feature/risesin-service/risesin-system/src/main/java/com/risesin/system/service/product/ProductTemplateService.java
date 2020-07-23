package com.risesin.system.service.product;

import com.risesin.system.dao.product.ProductTemplateDao;
import com.risesin.systemapi.product.entity.ProductTemplate;
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
 * productTemplate服务层
 *
 * @author Administrator
 */
@Service
public class ProductTemplateService {

    @Autowired
    private ProductTemplateDao productTemplateDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ProductTemplate> findAll() {
        return productTemplateDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ProductTemplate> findSearch(Map whereMap, int page, int size) {
        Specification<ProductTemplate> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return productTemplateDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ProductTemplate> findSearch(Map whereMap) {
        Specification<ProductTemplate> specification = createSpecification(whereMap);
        return productTemplateDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ProductTemplate findById(Integer pkId) {
        return productTemplateDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param productTemplate
     */
    public void add(ProductTemplate productTemplate) {
        // productTemplate.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        productTemplateDao.save(productTemplate);
    }

    /**
     * 修改
     *
     * @param productTemplate
     */
    public void update(ProductTemplate productTemplate) {
        productTemplateDao.save(productTemplate);
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(Integer pkId) {
        productTemplateDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ProductTemplate> createSpecification(Map searchMap) {

        return new Specification<ProductTemplate>() {

            @Override
            public Predicate toPredicate(Root<ProductTemplate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
