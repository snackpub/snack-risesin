package com.risesin.agent.service;

import com.risesin.agent.dao.ComLogUsualDao;
import com.risesin.agent.entity.ComLogUsual;
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
 * comLogUsual服务层
 *
 * @author Administrator
 */
@Service
public class ComLogUsualService {

    @Autowired
    private ComLogUsualDao comLogUsualDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ComLogUsual> findAll() {
        return comLogUsualDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ComLogUsual> findSearch(Map whereMap, int page, int size) {
        Specification<ComLogUsual> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return comLogUsualDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ComLogUsual> findSearch(Map whereMap) {
        Specification<ComLogUsual> specification = createSpecification(whereMap);
        return comLogUsualDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public ComLogUsual findById(Integer id) {
        return comLogUsualDao.findById(id).get();
    }

    /**
     * 增加
     *
     * @param comLogUsual
     */
    public void add(ComLogUsual comLogUsual) {
        // comLogUsual.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        comLogUsualDao.save(comLogUsual);
    }

    /**
     * 修改
     *
     * @param comLogUsual
     */
    @Transactional
    public void update(ComLogUsual comLogUsual) {
        comLogUsualDao.update(comLogUsual, comLogUsual.getId());
    }

    /**
     * 删除
     *
     * @param id
     */
    @Transactional
    public void deleteById(Integer id) {
        comLogUsualDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ComLogUsual> createSpecification(Map searchMap) {

        return new Specification<ComLogUsual>() {

            @Override
            public Predicate toPredicate(Root<ComLogUsual> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
                // 系统环境
                if (searchMap.get("env") != null && !"".equals(searchMap.get("env"))) {
                    predicateList.add(cb.like(root.get("env").as(String.class), "%" + (String) searchMap.get("env") + "%"));
                }
                // 日志级别
                if (searchMap.get("logLevel") != null && !"".equals(searchMap.get("logLevel"))) {
                    predicateList.add(cb.like(root.get("logLevel").as(String.class), "%" + (String) searchMap.get("logLevel") + "%"));
                }
                // 日志业务id
                if (searchMap.get("logId") != null && !"".equals(searchMap.get("logId"))) {
                    predicateList.add(cb.like(root.get("logId").as(String.class), "%" + (String) searchMap.get("logId") + "%"));
                }
                // 日志数据
                if (searchMap.get("logData") != null && !"".equals(searchMap.get("logData"))) {
                    predicateList.add(cb.like(root.get("logData").as(String.class), "%" + (String) searchMap.get("logData") + "%"));
                }
                // 操作方式
                if (searchMap.get("method") != null && !"".equals(searchMap.get("method"))) {
                    predicateList.add(cb.like(root.get("method").as(String.class), "%" + (String) searchMap.get("method") + "%"));
                }
                // 请求URI
                if (searchMap.get("requestUri") != null && !"".equals(searchMap.get("requestUri"))) {
                    predicateList.add(cb.like(root.get("requestUri").as(String.class), "%" + (String) searchMap.get("requestUri") + "%"));
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
                // 用户代理
                if (searchMap.get("userAgent") != null && !"".equals(searchMap.get("userAgent"))) {
                    predicateList.add(cb.like(root.get("userAgent").as(String.class), "%" + (String) searchMap.get("userAgent") + "%"));
                }
                // 操作提交的数据
                if (searchMap.get("params") != null && !"".equals(searchMap.get("params"))) {
                    predicateList.add(cb.like(root.get("params").as(String.class), "%" + (String) searchMap.get("params") + "%"));
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
