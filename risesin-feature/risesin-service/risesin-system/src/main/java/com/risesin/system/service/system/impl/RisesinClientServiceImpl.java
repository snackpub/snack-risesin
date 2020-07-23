package com.risesin.system.service.system.impl;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.tool.utils.SqlHelper;

import com.risesin.system.dao.system.RisesinClientDao;
import com.risesin.system.service.system.RisesinClientService;
import com.risesin.systemapi.system.entity.RisesinClient;
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
 * RisesinClient的服务接口的实现类
 *
 * @author honey
 * @date 2019-12-22
 */
@Service
public class RisesinClientServiceImpl extends RisesinBaseServiceImpl<RisesinClientDao, RisesinClient, Integer> implements RisesinClientService {

    @Override
    public Page<RisesinClient> findSearch(Map whereMap, Query query) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Specification<RisesinClient> specification = createSpecification(whereMap);
        PageRequest pageRequest = QueryUtil.getPage(query, sort);
        return baseDao.findAll(specification, pageRequest);
    }

    @Override
    public void update(RisesinClient risesinClient) {
        baseDao.update(risesinClient, risesinClient.getId());
    }

    @Override
    public RisesinClient findById(Integer id) {
        return SqlHelper.optional(baseDao.findById(id));
    }

    private Specification<RisesinClient> createSpecification(Map searchMap) {

        return new Specification<RisesinClient>() {

            @Override
            public Predicate toPredicate(Root<RisesinClient> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                /**主键*/
                /**客户端id*/
                if (searchMap.get("clientId") != null && !"".equals(searchMap.get("clientId"))) {
                    predicateList.add(cb.like(root.get("clientId").as(String.class), "%" + (String) searchMap.get("clientId") + "%"));
                }
                /**客户端密钥*/
                if (searchMap.get("clientSecret") != null && !"".equals(searchMap.get("clientSecret"))) {
                    predicateList.add(cb.like(root.get("clientSecret").as(String.class), "%" + (String) searchMap.get("clientSecret") + "%"));
                }
                /**资源集合*/
                if (searchMap.get("resourceIds") != null && !"".equals(searchMap.get("resourceIds"))) {
                    predicateList.add(cb.like(root.get("resourceIds").as(String.class), "%" + (String) searchMap.get("resourceIds") + "%"));
                }
                /**授权范围*/
                if (searchMap.get("scope") != null && !"".equals(searchMap.get("scope"))) {
                    predicateList.add(cb.like(root.get("scope").as(String.class), "%" + (String) searchMap.get("scope") + "%"));
                }
                /**授权类型*/
                if (searchMap.get("authorizedGrantTypes") != null && !"".equals(searchMap.get("authorizedGrantTypes"))) {
                    predicateList.add(cb.like(root.get("authorizedGrantTypes").as(String.class), "%" + (String) searchMap.get("authorizedGrantTypes") + "%"));
                }
                /**回调地址*/
                if (searchMap.get("webServerRedirectUri") != null && !"".equals(searchMap.get("webServerRedirectUri"))) {
                    predicateList.add(cb.like(root.get("webServerRedirectUri").as(String.class), "%" + (String) searchMap.get("webServerRedirectUri") + "%"));
                }
                /**权限*/
                if (searchMap.get("authorities") != null && !"".equals(searchMap.get("authorities"))) {
                    predicateList.add(cb.like(root.get("authorities").as(String.class), "%" + (String) searchMap.get("authorities") + "%"));
                }
                /**令牌过期秒数*/
                /**刷新令牌过期秒数*/
                /**附加说明*/
                if (searchMap.get("additionalInformation") != null && !"".equals(searchMap.get("additionalInformation"))) {
                    predicateList.add(cb.like(root.get("additionalInformation").as(String.class), "%" + (String) searchMap.get("additionalInformation") + "%"));
                }
                /**自动授权*/
                if (searchMap.get("autoapprove") != null && !"".equals(searchMap.get("autoapprove"))) {
                    predicateList.add(cb.like(root.get("autoapprove").as(String.class), "%" + (String) searchMap.get("autoapprove") + "%"));
                }
                /**创建人*/
                /**创建时间*/
                /**修改人*/
                /**修改时间*/
                /**状态*/
                /**是否已删除*/
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };
    }
}