package com.risesin.agent.service;

import com.risesin.agent.dao.ComfirmAmountDao;
import com.risesin.agent.entity.ComfirmAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * comfirmAmount服务层
 *
 * @author Administrator
 */
@Service
public class ComfirmAmountService {

    @Autowired
    private ComfirmAmountDao comfirmAmountDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ComfirmAmount> findAll() {
        return comfirmAmountDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ComfirmAmount> findSearch(Map whereMap, int page, int size) {
        Specification<ComfirmAmount> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return comfirmAmountDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ComfirmAmount> findSearch(Map whereMap) {
        Specification<ComfirmAmount> specification = createSpecification(whereMap);
        return comfirmAmountDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ComfirmAmount findById(Integer pkId) {
        return comfirmAmountDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param comfirmAmount
     */
    public void add(ComfirmAmount comfirmAmount) {
        // comfirmAmount.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        comfirmAmountDao.save(comfirmAmount);
    }

    /**
     * 修改
     *
     * @param comfirmAmount
     */
    @Transactional
    public void update(ComfirmAmount comfirmAmount) {
        comfirmAmountDao.update(comfirmAmount, comfirmAmount.getId());
    }

    /**
     * 删除
     *
     * @param pkId
     */
    @Transactional
    public void deleteById(Integer pkId) {
        comfirmAmountDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ComfirmAmount> createSpecification(Map searchMap) {

        return new Specification<ComfirmAmount>() {

            @Override
            public Predicate toPredicate(Root<ComfirmAmount> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 审核意见
                if (searchMap.get("comment") != null && !"".equals(searchMap.get("comment"))) {
                    predicateList.add(cb.like(root.get("comment").as(String.class), "%" + (String) searchMap.get("comment") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
