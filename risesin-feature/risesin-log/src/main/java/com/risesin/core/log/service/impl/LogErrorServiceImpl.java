package com.risesin.core.log.service.impl;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.log.dao.SysLogErrorDao;
import com.risesin.core.log.model.LogError;
import com.risesin.core.log.service.ILogErrorService;
import com.risesin.core.tool.utils.SqlHelper;
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
 * 服务类实现类
 *
 * @author honey
 * @since 2019-12-1
 */
@Service
public class LogErrorServiceImpl extends RisesinBaseServiceImpl<SysLogErrorDao, LogError, Integer> implements ILogErrorService {


    @Override
    public Page<LogError> findSearch(Map whereMap, Query query) {
        Specification<LogError> specification = createSpecification(whereMap);
        PageRequest pageRequest = QueryUtil.getPage(query);
        return baseDao.findAll(specification, pageRequest);
    }

    public List<LogError> findSearch(Map whereMap) {
        Specification<LogError> specification = createSpecification(whereMap);
        return baseDao.findAll(specification);
    }


    @Override
    public LogError findById(Integer id) {
        return SqlHelper.optional(baseDao.findById(id));
    }


    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<LogError> createSpecification(Map searchMap) {

        return new Specification<LogError>() {

            @Override
            public Predicate toPredicate(Root<LogError> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 服务ID
                if (searchMap.get("serviceId") != null && !"".equals(searchMap.get("serviceId"))) {
                    predicateList.add(cb.like(root.get("serviceId").as(String.class), "%" + (String) searchMap.get("serviceId") + "%"));
                }
                // 服务器名
                if (searchMap.get("serverHost") != null && !"".equals(searchMap.get("serverHost"))) {
                    predicateList.add(cb.like(root.get("serverHost").as(String.class), "%" + (String) searchMap.get("serverHost") + "%"));
                }
                // 服务器IP地址
                if (searchMap.get("serverIp") != null && !"".equals(searchMap.get("serverIp"))) {
                    predicateList.add(cb.like(root.get("serverIp").as(String.class), "%" + (String) searchMap.get("serverIp") + "%"));
                }
                // 服务器环境
                if (searchMap.get("env") != null && !"".equals(searchMap.get("env"))) {
                    predicateList.add(cb.like(root.get("env").as(String.class), "%" + (String) searchMap.get("env") + "%"));
                }
                // 日志类型
                if (searchMap.get("type") != null && !"".equals(searchMap.get("type"))) {
                    predicateList.add(cb.like(root.get("type").as(String.class), "%" + (String) searchMap.get("type") + "%"));
                }
                // 日志标题
                if (searchMap.get("title") != null && !"".equals(searchMap.get("title"))) {
                    predicateList.add(cb.like(root.get("title").as(String.class), "%" + (String) searchMap.get("title") + "%"));
                }
                // 操作方式
                if (searchMap.get("method") != null && !"".equals(searchMap.get("method"))) {
                    predicateList.add(cb.like(root.get("method").as(String.class), "%" + (String) searchMap.get("method") + "%"));
                }
                // 请求URI
                if (searchMap.get("requestUri") != null && !"".equals(searchMap.get("requestUri"))) {
                    predicateList.add(cb.like(root.get("requestUri").as(String.class), "%" + (String) searchMap.get("requestUri") + "%"));
                }
                // 用户代理
                if (searchMap.get("userAgent") != null && !"".equals(searchMap.get("userAgent"))) {
                    predicateList.add(cb.like(root.get("userAgent").as(String.class), "%" + (String) searchMap.get("userAgent") + "%"));
                }
                // 操作IP地址
                if (searchMap.get("remoteIp") != null && !"".equals(searchMap.get("remoteIp"))) {
                    predicateList.add(cb.like(root.get("remoteIp").as(String.class), "%" + (String) searchMap.get("remoteIp") + "%"));
                }
                // 方法类
                if (searchMap.get("methodClass") != null && !"".equals(searchMap.get("methodClass"))) {
                    predicateList.add(cb.like(root.get("methodClass").as(String.class), "%" + (String) searchMap.get("methodClass") + "%"));
                }
                // 方法名
                if (searchMap.get("methodName") != null && !"".equals(searchMap.get("methodName"))) {
                    predicateList.add(cb.like(root.get("methodName").as(String.class), "%" + (String) searchMap.get("methodName") + "%"));
                }
                // 操作提交的数据
                if (searchMap.get("params") != null && !"".equals(searchMap.get("params"))) {
                    predicateList.add(cb.like(root.get("params").as(String.class), "%" + (String) searchMap.get("params") + "%"));
                }
                // 执行时间
                if (searchMap.get("time") != null && !"".equals(searchMap.get("time"))) {
                    predicateList.add(cb.like(root.get("time").as(String.class), "%" + (String) searchMap.get("time") + "%"));
                }
                // 创建者
                if (searchMap.get("createBy") != null && !"".equals(searchMap.get("createBy"))) {
                    predicateList.add(cb.like(root.get("createBy").as(String.class), "%" + (String) searchMap.get("createBy") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }
}
