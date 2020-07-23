package com.risesin.agent.service;

import com.risesin.agent.dao.ComOrderCheckResultDao;
import com.risesin.agent.entity.ComOrderCheckResult;
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
 * comOrderCheckResult服务层
 *
 * @author Administrator
 */
@Service
public class ComOrderCheckResultService {

    @Autowired
    private ComOrderCheckResultDao comOrderCheckResultDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ComOrderCheckResult> findAll() {
        return comOrderCheckResultDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ComOrderCheckResult> findSearch(Map whereMap, int page, int size) {
        Specification<ComOrderCheckResult> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return comOrderCheckResultDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ComOrderCheckResult> findSearch(Map whereMap) {
        Specification<ComOrderCheckResult> specification = createSpecification(whereMap);
        return comOrderCheckResultDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ComOrderCheckResult findById(Integer pkId) {
        return comOrderCheckResultDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param comOrderCheckResult
     */
    public void add(ComOrderCheckResult comOrderCheckResult) {
        // comOrderCheckResult.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        comOrderCheckResultDao.save(comOrderCheckResult);
    }

    /**
     * 修改
     *
     * @param comOrderCheckResult
     */
    @Transactional
    public void update(ComOrderCheckResult comOrderCheckResult) {
        comOrderCheckResultDao.update(comOrderCheckResult, comOrderCheckResult.getId());
    }

    /**
     * 删除
     *
     * @param pkId
     */
    @Transactional
    public void deleteById(Integer pkId) {
        comOrderCheckResultDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ComOrderCheckResult> createSpecification(Map searchMap) {

        return new Specification<ComOrderCheckResult>() {

            @Override
            public Predicate toPredicate(Root<ComOrderCheckResult> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 审核意见
                if (searchMap.get("reason") != null && !"".equals(searchMap.get("reason"))) {
                    predicateList.add(cb.like(root.get("reason").as(String.class), "%" + (String) searchMap.get("reason") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
