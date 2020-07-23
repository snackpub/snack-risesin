package com.risesin.system.service.order.impl;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.order.SysOrderCheckResultDao;
import com.risesin.system.service.order.ISysOrderCheckResultService;
import com.risesin.systemapi.order.entity.SysOrderCheckResult;
import lombok.AllArgsConstructor;
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
 * sysOrderCheckResult服务层
 *
 * @author Administrator
 */
@Service
@AllArgsConstructor
public class SysOrderCheckResultServiceImpl extends RisesinBaseServiceImpl<SysOrderCheckResultDao, SysOrderCheckResult, String> implements ISysOrderCheckResultService {


    @Override
    public Page<SysOrderCheckResult> findSearch(Map whereMap, Query query) {
        Specification<SysOrderCheckResult> specification = createSpecification(whereMap);
        PageRequest pageRequest = QueryUtil.getPage(query);
        return baseDao.findAll(specification, pageRequest);
    }


    @Override
    public SysOrderCheckResult findById(String id) {
        return SqlHelper.optional(baseDao.findById(id));
    }


    /**
     * 增加
     *
     * @param sysOrderCheckResult 对象
     */
    @Override
    public void add(SysOrderCheckResult sysOrderCheckResult) {
        baseDao.save(sysOrderCheckResult);
    }

    /**
     * 修改
     *
     * @param sysOrderCheckResult
     */
    public void update(SysOrderCheckResult sysOrderCheckResult) {
        baseDao.update(sysOrderCheckResult, sysOrderCheckResult.getId());
    }


    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<SysOrderCheckResult> createSpecification(Map searchMap) {

        return new Specification<SysOrderCheckResult>() {

            @Override
            public Predicate toPredicate(Root<SysOrderCheckResult> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
