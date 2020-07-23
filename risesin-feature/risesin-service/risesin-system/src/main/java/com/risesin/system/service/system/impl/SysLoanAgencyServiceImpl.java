package com.risesin.system.service.system.impl;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.system.SysLoanAgencyDao;
import com.risesin.system.service.system.SysLoanAgencyService;
import com.risesin.systemapi.system.entity.SysLoanAgency;
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
 * @author honey
 * 注意：同志们不需要继承BaseService了，dao还是继承BaseDao<E,ID>，亲更新当前代码
 * extends RisesinBaseServiceImpl<D, E, ID>
 * @Date 2019/12/11 20:49
 */
@Service
public class SysLoanAgencyServiceImpl extends RisesinBaseServiceImpl<SysLoanAgencyDao, SysLoanAgency, Integer> implements SysLoanAgencyService {

    @Override
    public Page<SysLoanAgency> findSearch(Map whereMap, Query query) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Specification<SysLoanAgency> specification = createSpecification(whereMap);
        PageRequest pageRequest = QueryUtil.getPage(query, sort);
        return baseDao.findAll(specification, pageRequest);
    }

    @Override
    public void update(SysLoanAgency sysLoanAgency) {
        baseDao.update(sysLoanAgency, sysLoanAgency.getId());
    }

    @Override
    public SysLoanAgency findById(Integer id) {
        return SqlHelper.optional(baseDao.findById(id));
    }

    private Specification<SysLoanAgency> createSpecification(Map searchMap) {

        return new Specification<SysLoanAgency>() {

            @Override
            public Predicate toPredicate(Root<SysLoanAgency> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 公司代码
                if (searchMap.get("orgCode") != null && !"".equals(searchMap.get("orgCode"))) {
                    predicateList.add(cb.like(root.get("orgCode").as(String.class), "%" + (String) searchMap.get("orgCode") + "%"));
                }
                // 公司名称
                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%" + (String) searchMap.get("name") + "%"));
                }
                // 区域
                if (searchMap.get("area") != null && !"".equals(searchMap.get("area"))) {
                    predicateList.add(cb.like(root.get("area").as(String.class), "%" + (String) searchMap.get("area") + "%"));
                }
                // 公司地址
                if (searchMap.get("address") != null && !"".equals(searchMap.get("address"))) {
                    predicateList.add(cb.like(root.get("address").as(String.class), "%" + (String) searchMap.get("address") + "%"));
                }
                // 负责人
                if (searchMap.get("leader") != null && !"".equals(searchMap.get("leader"))) {
                    predicateList.add(cb.like(root.get("leader").as(String.class), "%" + (String) searchMap.get("leader") + "%"));
                }
                // 联系电话
                if (searchMap.get("phone") != null && !"".equals(searchMap.get("phone"))) {
                    predicateList.add(cb.like(root.get("phone").as(String.class), "%" + (String) searchMap.get("phone") + "%"));
                }
                // 描述
                if (searchMap.get("description") != null && !"".equals(searchMap.get("description"))) {
                    predicateList.add(cb.like(root.get("description").as(String.class), "%" + (String) searchMap.get("description") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }
}
