package com.risesin.system.service.plan;

import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.plan.EnterpriseInfoDao;
import com.risesin.systemapi.plan.entity.EnterpriseInfo;
import com.risesin.systemapi.plan.interfaceresult.FinancingBarInfo;
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
import java.util.Optional;

/**
 * enterpriseInfo服务层
 *
 * @author Administrator
 */
@Service
public class EnterpriseInfoService {

    @Autowired
    private EnterpriseInfoDao enterpriseInfoDao;


    /**
     * 查询全部列表
     *
     * @return
     */

    public List<EnterpriseInfo> findAll() {
        return enterpriseInfoDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */

    public Page<EnterpriseInfo> findSearch(Map whereMap, int page, int size) {
        Specification<EnterpriseInfo> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return enterpriseInfoDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */

    public List<EnterpriseInfo> findSearch(Map whereMap) {
        Specification<EnterpriseInfo> specification = createSpecification(whereMap);
        return enterpriseInfoDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */

    public EnterpriseInfo findById(Integer id) {
//        Optional<EnterpriseInfo> entInfo = enterpriseInfoDao.findById(EnterpriseInfo.class, id);
        Optional<EnterpriseInfo> entInfo = enterpriseInfoDao.findById(id);
        return SqlHelper.optional(entInfo);
    }


    public void add(EnterpriseInfo enterpriseInfo) {

    }


    public void update(EnterpriseInfo enterpriseInfo) {
        enterpriseInfoDao.update(enterpriseInfo, enterpriseInfo.getId());
    }


    public void deleteById(Integer id) {
        enterpriseInfoDao.deleteById(id);
    }


    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<EnterpriseInfo> createSpecification(Map searchMap) {

        return new Specification<EnterpriseInfo>() {


            public Predicate toPredicate(Root<EnterpriseInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 融资主体名称
                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%" + (String) searchMap.get("name") + "%"));
                }
                // 组织机构码
                if (searchMap.get("orgCode") != null && !"".equals(searchMap.get("orgCode"))) {
                    predicateList.add(cb.like(root.get("orgCode").as(String.class), "%" + (String) searchMap.get("orgCode") + "%"));
                }
                // 所属区域
                if (searchMap.get("fkAreaId") != null && !"".equals(searchMap.get("fkAreaId"))) {
                    predicateList.add(cb.like(root.get("fkAreaId").as(String.class), "%" + (String) searchMap.get("fkAreaId") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }


    public List<FinancingBarInfo> find5FinancingPlanInfo(String enterpriseUserId) {

        List<FinancingBarInfo> financingPlanInfo = enterpriseInfoDao.find5FinancingPlanInfo(enterpriseUserId);
        return financingPlanInfo;
    }
}
