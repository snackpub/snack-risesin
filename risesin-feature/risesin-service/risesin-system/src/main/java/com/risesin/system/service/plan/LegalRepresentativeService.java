package com.risesin.system.service.plan;

import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.plan.LegalRepresentativeDao;
import com.risesin.systemapi.plan.entity.LegalRepresentative;
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
 * legalRepresentative服务层
 *
 * @author Administrator
 */
@Service
public class LegalRepresentativeService {

    @Autowired
    private LegalRepresentativeDao legalRepresentativeDao;


    /**
     * 查询全部列表
     *
     * @return
     */

    public List<LegalRepresentative> findAll() {
        return legalRepresentativeDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */

    public Page<LegalRepresentative> findSearch(Map whereMap, int page, int size) {
        Specification<LegalRepresentative> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return legalRepresentativeDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */

    public List<LegalRepresentative> findSearch(Map whereMap) {
        Specification<LegalRepresentative> specification = createSpecification(whereMap);
        return legalRepresentativeDao.findAll(specification);
    }


    public LegalRepresentative findById(Integer id) {
        return SqlHelper.optional(legalRepresentativeDao.findById(id));
    }

    public void add(LegalRepresentative legalRepresentative) {
        legalRepresentativeDao.save(legalRepresentative);
    }


    public void update(LegalRepresentative lr) {
        legalRepresentativeDao.update(lr, lr.getId());
    }


    public void deleteById(Integer id) {
        legalRepresentativeDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<LegalRepresentative> createSpecification(Map searchMap) {

        return new Specification<LegalRepresentative>() {

            @Override
            public Predicate toPredicate(Root<LegalRepresentative> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 法人代表姓名
                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%" + (String) searchMap.get("name") + "%"));
                }
                // 国籍
                if (searchMap.get("nationality") != null && !"".equals(searchMap.get("nationality"))) {
                    predicateList.add(cb.like(root.get("nationality").as(String.class), "%" + (String) searchMap.get("nationality") + "%"));
                }
                // 法人代表身份证号
                if (searchMap.get("idNumber") != null && !"".equals(searchMap.get("idNumber"))) {
                    predicateList.add(cb.like(root.get("idNumber").as(String.class), "%" + (String) searchMap.get("idNumber") + "%"));
                }
                // 配偶姓名
                if (searchMap.get("spouseName") != null && !"".equals(searchMap.get("spouseName"))) {
                    predicateList.add(cb.like(root.get("spouseName").as(String.class), "%" + (String) searchMap.get("spouseName") + "%"));
                }
                // 配偶身份证号
                if (searchMap.get("spouseIdNumber") != null && !"".equals(searchMap.get("spouseIdNumber"))) {
                    predicateList.add(cb.like(root.get("spouseIdNumber").as(String.class), "%" + (String) searchMap.get("spouseIdNumber") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }


}
