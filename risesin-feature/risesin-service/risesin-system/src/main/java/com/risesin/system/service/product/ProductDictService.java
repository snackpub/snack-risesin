package com.risesin.system.service.product;

import com.risesin.system.dao.product.ProductDictDao;
import com.risesin.systemapi.product.entity.ProductDict;
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
 * productDict服务层
 *
 * @author Administrator
 */
@Service
public class ProductDictService {

    @Autowired
    private ProductDictDao productDictDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ProductDict> findAll() {
        return productDictDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ProductDict> findSearch(Map whereMap, int page, int size) {
        Specification<ProductDict> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return productDictDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ProductDict> findSearch(Map whereMap) {
        Specification<ProductDict> specification = createSpecification(whereMap);
        return productDictDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ProductDict findById(Integer pkId) {
        return productDictDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param productDict
     */
    public void add(ProductDict productDict) {
        // productDict.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        productDictDao.save(productDict);
    }

    /**
     * 修改
     *
     * @param productDict
     */
    public void update(ProductDict productDict) {
        productDictDao.save(productDict);
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(Integer pkId) {
        productDictDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ProductDict> createSpecification(Map searchMap) {

        return new Specification<ProductDict>() {

            @Override
            public Predicate toPredicate(Root<ProductDict> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
