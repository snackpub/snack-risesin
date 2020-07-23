package com.risesin.system.service.plan;

import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.plan.EnterpriseAssetInfoDao;
import com.risesin.systemapi.plan.entity.EnterpriseAssetInfo;
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
 * enterpriseAssetInfo服务层
 *
 * @author Administrator
 */
@Service
public class EnterpriseAssetInfoService {

    @Autowired
    private EnterpriseAssetInfoDao enterpriseAssetInfoDao;


    public List<EnterpriseAssetInfo> findAll() {
        return enterpriseAssetInfoDao.findAll();
    }


    public Page<EnterpriseAssetInfo> findSearch(Map whereMap, int page, int size) {
        Specification<EnterpriseAssetInfo> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return enterpriseAssetInfoDao.findAll(specification, pageRequest);
    }


    public List<EnterpriseAssetInfo> findSearch(Map whereMap) {
        Specification<EnterpriseAssetInfo> specification = createSpecification(whereMap);
        return enterpriseAssetInfoDao.findAll(specification);
    }


    public EnterpriseAssetInfo findById(Integer id) {
        return SqlHelper.optional(enterpriseAssetInfoDao.findById(id));
    }


    public void add(EnterpriseAssetInfo enterpriseAssetInfo) {
        enterpriseAssetInfoDao.save(enterpriseAssetInfo);
    }


    public void update(EnterpriseAssetInfo enterpriseAssetInfo) {
        enterpriseAssetInfoDao.update(enterpriseAssetInfo, enterpriseAssetInfo.getId());
    }


    public void deleteById(Integer pkId) {
        enterpriseAssetInfoDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<EnterpriseAssetInfo> createSpecification(Map searchMap) {

        return new Specification<EnterpriseAssetInfo>() {

            @Override
            public Predicate toPredicate(Root<EnterpriseAssetInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 是否存在以下项目用地:工业用地;物流仓储用地;科教用地;
                if (searchMap.get("isProjectSite") != null && !"".equals(searchMap.get("isProjectSite"))) {
                    predicateList.add(cb.like(root.get("isProjectSite").as(String.class), "%" + (String) searchMap.get("isProjectSite") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }


}
