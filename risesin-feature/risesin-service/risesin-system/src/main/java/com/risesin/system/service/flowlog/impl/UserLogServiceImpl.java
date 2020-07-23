package com.risesin.system.service.flowlog.impl;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.tool.utils.SqlHelper;

import com.risesin.system.dao.flowlog.UserLogDao;
import com.risesin.system.service.flowlog.UserLogService;
import com.risesin.systemapi.flowlog.entity.UserLog;
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
 * UserLog的服务接口的实现类
 *
 * @author
 * @date 2019-12-22
 */
@Service
public class UserLogServiceImpl extends RisesinBaseServiceImpl<UserLogDao, UserLog, Integer> implements UserLogService {

    @Override
    public Page<UserLog> findSearch(Map whereMap, Query query) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Specification<UserLog> specification = createSpecification(whereMap);
        PageRequest pageRequest = QueryUtil.getPage(query, sort);
        return baseDao.findAll(specification, pageRequest);
    }

    @Override
    public void update(UserLog userLog) {
        baseDao.update(userLog, userLog.getId());
    }

    @Override
    public UserLog findById(Integer id) {
        return SqlHelper.optional(baseDao.findById(id));
    }

    private Specification<UserLog> createSpecification(Map searchMap) {

        return new Specification<UserLog>() {

            @Override
            public Predicate toPredicate(Root<UserLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                /**注解ID*/
                if (searchMap.get("pkId") != null && !"".equals(searchMap.get("pkId"))) {
                    predicateList.add(cb.like(root.get("pkId").as(String.class), "%" + (String) searchMap.get("pkId") + "%"));
                }
                /**操作内容*/
                if (searchMap.get("content") != null && !"".equals(searchMap.get("content"))) {
                    predicateList.add(cb.like(root.get("content").as(String.class), "%" + (String) searchMap.get("content") + "%"));
                }
                /**创建用户*/
                if (searchMap.get("createUser") != null && !"".equals(searchMap.get("createUser"))) {
                    predicateList.add(cb.like(root.get("createUser").as(String.class), "%" + (String) searchMap.get("createUser") + "%"));
                }
                /**操作用户*/
                if (searchMap.get("operationUser") != null && !"".equals(searchMap.get("operationUser"))) {
                    predicateList.equals(cb.equal(root.get("operationUser").as(String.class), (String) searchMap.get("operationUser")));
                }
                /**创建时间*/
                if (searchMap.get("createTime") != null && !"".equals(searchMap.get("createTime"))) {
                    predicateList.add(cb.like(root.get("createTime").as(String.class), "%" + (String) searchMap.get("createTime") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };
    }
}