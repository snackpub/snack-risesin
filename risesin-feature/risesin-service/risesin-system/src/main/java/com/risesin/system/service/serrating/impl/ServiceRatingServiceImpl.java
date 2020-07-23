package com.risesin.system.service.serrating.impl;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.serrating.ServiceRatingDao;
import com.risesin.system.service.serrating.IServiceRatingService;
import com.risesin.systemapi.serrating.entity.ServiceRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
 * ServiceRating的服务接口的实现类
 *
 * @author
 */
@Service
public class ServiceRatingServiceImpl extends RisesinBaseServiceImpl<ServiceRatingDao, ServiceRating, Integer> implements IServiceRatingService {

    @Override
    public Page<ServiceRating> findSearch(Map whereMap, Query query) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Specification<ServiceRating> specification = createSpecification(whereMap);
        PageRequest pageRequest = QueryUtil.getPage(query, sort);
        return baseDao.findAll(specification, pageRequest);
    }

    @Override
    public void update(ServiceRating serviceRating) {
        baseDao.update(serviceRating, serviceRating.getId());
    }

    @Override
    public ServiceRating findById(Integer id) {
        return SqlHelper.optional(baseDao.findById(id));
    }

    private Specification<ServiceRating> createSpecification(Map searchMap) {

        return new Specification<ServiceRating>() {

            @Override
            public Predicate toPredicate(Root<ServiceRating> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                /**主键ID：自动增长*/
                /**创建时间*/
                if (searchMap.get("createTime") != null && !"".equals(searchMap.get("createTime"))) {
                    predicateList.add(cb.like(root.get("createTime").as(String.class), "%" + (String) searchMap.get("createTime") + "%"));
                }
                /**修改时间*/
                if (searchMap.get("updateTime") != null && !"".equals(searchMap.get("updateTime"))) {
                    predicateList.add(cb.like(root.get("updateTime").as(String.class), "%" + (String) searchMap.get("updateTime") + "%"));
                }
                /**评价人*/
                /**服务评分(1-5分)*/
                if (searchMap.get("score") != null && !"".equals(searchMap.get("score"))) {
                    predicateList.add(cb.like(root.get("score").as(String.class), "%" + (String) searchMap.get("score") + "%"));
                }
                /**服务提供者*/
                /**评价类型*/
                /**融资预案id*/
                if (searchMap.get("fkFinaPlanCode") != null && !"".equals(searchMap.get("fkFinaPlanCode"))) {
                    predicateList.add(cb.like(root.get("fkFinaPlanCode").as(String.class), "%" + (String) searchMap.get("fkFinaPlanCode") + "%"));
                }
                /**评价描述*/
                if (searchMap.get("description") != null && !"".equals(searchMap.get("description"))) {
                    predicateList.add(cb.like(root.get("description").as(String.class), "%" + (String) searchMap.get("description") + "%"));
                }
                /**状态*/
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };
    }
}