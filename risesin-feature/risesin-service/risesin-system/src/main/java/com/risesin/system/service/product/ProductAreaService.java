package com.risesin.system.service.product;

import com.risesin.system.dao.product.ProductAreaDao;
import com.risesin.systemapi.product.entity.ProductArea;
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
 * productArea服务层
 *
 * @author Administrator
 */
@Service
public class ProductAreaService {

    @Autowired
    private ProductAreaDao productAreaDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ProductArea> findAll() {
        return productAreaDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ProductArea> findSearch(Map whereMap, int page, int size) {
        Specification<ProductArea> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return productAreaDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ProductArea> findSearch(Map whereMap) {
        Specification<ProductArea> specification = createSpecification(whereMap);
        return productAreaDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ProductArea findById(Integer pkId) {
        return productAreaDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param productArea
     */
    public void add(ProductArea productArea) {
        // productArea.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        productAreaDao.save(productArea);
    }

    /**
     * 修改
     *
     * @param productArea
     */
    public void update(ProductArea productArea) {
        productAreaDao.save(productArea);
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(Integer pkId) {
        productAreaDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ProductArea> createSpecification(Map searchMap) {

        return new Specification<ProductArea>() {

            @Override
            public Predicate toPredicate(Root<ProductArea> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
