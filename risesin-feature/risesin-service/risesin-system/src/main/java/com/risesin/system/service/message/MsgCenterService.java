package com.risesin.system.service.message;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.system.dao.message.MsgCenterDao;
import com.risesin.systemapi.message.entity.MsgCenter;
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
 * 勿删！！！
 *
 * @author honey
 * @date 2019/12/17 9:14
 */
@Service
public class MsgCenterService extends RisesinBaseServiceImpl<MsgCenterDao, MsgCenter, Integer> {

    public Page<MsgCenter> findSearch(Map whereMap, Query query) {
        Specification<MsgCenter> specification = createSpecification(whereMap);
        PageRequest pageRequest = QueryUtil.getPage(query);
        return baseDao.findAll(specification, pageRequest);
    }

    @Override
    public void add(MsgCenter msgCenter) {
        baseDao.save(msgCenter);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<MsgCenter> createSpecification(Map searchMap) {

        return new Specification<MsgCenter>() {

            @Override
            public Predicate toPredicate(Root<MsgCenter> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 标题
                if (searchMap.get("title") != null && !"".equals(searchMap.get("title"))) {
                    predicateList.add(cb.like(root.get("title").as(String.class), "%" + (String) searchMap.get("title") + "%"));
                }
                // 内容
                if (searchMap.get("content") != null && !"".equals(searchMap.get("content"))) {
                    predicateList.add(cb.like(root.get("content").as(String.class), "%" + (String) searchMap.get("content") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
