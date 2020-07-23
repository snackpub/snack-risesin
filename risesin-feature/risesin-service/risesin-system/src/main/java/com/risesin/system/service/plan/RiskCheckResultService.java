package com.risesin.system.service.plan;

import com.risesin.system.dao.plan.RiskCheckResultDao;
import com.risesin.systemapi.plan.entity.RiskCheckResult;
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
 * riskCheckResult服务层
 *
 * @author Administrator
 */
@Service
public class RiskCheckResultService {

    @Autowired
    private RiskCheckResultDao riskCheckResultDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<RiskCheckResult> findAll() {
        return riskCheckResultDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<RiskCheckResult> findSearch(Map whereMap, int page, int size) {
        Specification<RiskCheckResult> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return riskCheckResultDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<RiskCheckResult> findSearch(Map whereMap) {
        Specification<RiskCheckResult> specification = createSpecification(whereMap);
        return riskCheckResultDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public RiskCheckResult findById(Integer pkId) {
        return riskCheckResultDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param riskCheckResult
     */
    public void add(RiskCheckResult riskCheckResult) {
        // riskCheckResult.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        riskCheckResultDao.save(riskCheckResult);
    }

    /**
     * 修改
     *
     * @param riskCheckResult
     */
    public void update(RiskCheckResult riskCheckResult) {
        riskCheckResultDao.save(riskCheckResult);
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(Integer pkId) {
        riskCheckResultDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<RiskCheckResult> createSpecification(Map searchMap) {

        return new Specification<RiskCheckResult>() {

            @Override
            public Predicate toPredicate(Root<RiskCheckResult> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
