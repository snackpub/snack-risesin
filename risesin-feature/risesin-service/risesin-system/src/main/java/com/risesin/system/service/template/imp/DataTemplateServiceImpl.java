package com.risesin.system.service.template.imp;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.template.DataTemplateDao;
import com.risesin.system.service.template.DataTemplateService;
import com.risesin.systemapi.dict.entity.DataTemplate;
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
 * @author honey
 * @date 2019/12/13 16:01
 */
@Service
public class DataTemplateServiceImpl extends RisesinBaseServiceImpl<DataTemplateDao, DataTemplate, Integer> implements DataTemplateService {
    /**
     * 条件查询+分页
     *
     * @param whereMap params
     * @param query    分页Query
     * @return page
     */
    @Override
    public Page<DataTemplate> findSearch(Map whereMap, Query query) {
        Specification<DataTemplate> specification = createSpecification(whereMap);
        PageRequest pageRequest = QueryUtil.getPage(query);
        return baseDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap params
     * @return list
     */
    public List<DataTemplate> findSearch(Map whereMap) {
        Specification<DataTemplate> specification = createSpecification(whereMap);
        return baseDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id Id
     * @return DataTemplate
     */
    @Override
    public DataTemplate findById(Integer id) {
        return SqlHelper.optional(baseDao.findById(id));
    }


    /**
     * 修改
     *
     * @param dataTemplate Entity
     */
    @Override
    public void update(DataTemplate dataTemplate) {
        baseDao.update(dataTemplate, dataTemplate.getId());
    }


    private Specification<DataTemplate> createSpecification(Map searchMap) {

        return new Specification<DataTemplate>() {

            @Override
            public Predicate toPredicate(Root<DataTemplate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 表字段
                if (searchMap.get("field") != null && !"".equals(searchMap.get("field"))) {
                    predicateList.add(cb.like(root.get("field").as(String.class), "%" + (String) searchMap.get("field") + "%"));
                }
                // 字段类型
                if (searchMap.get("fieldType") != null && !"".equals(searchMap.get("fieldType"))) {
                    predicateList.add(cb.like(root.get("fieldType").as(String.class), "%" + (String) searchMap.get("fieldType") + "%"));
                }
                // 字段后缀
                if (searchMap.get("fieldSuffix") != null && !"".equals(searchMap.get("fieldSuffix"))) {
                    predicateList.add(cb.like(root.get("fieldSuffix").as(String.class), "%" + (String) searchMap.get("fieldSuffix") + "%"));
                }
                // 描述
                if (searchMap.get("description") != null && !"".equals(searchMap.get("description"))) {
                    predicateList.add(cb.like(root.get("description").as(String.class), "%" + (String) searchMap.get("description") + "%"));
                }
                // 字段单位
                if (searchMap.get("fieldUnit") != null && !"".equals(searchMap.get("fieldUnit"))) {
                    predicateList.add(cb.like(root.get("fieldUnit").as(String.class), "%" + (String) searchMap.get("fieldUnit") + "%"));
                }
                // 选项
                if (searchMap.get("options") != null && !"".equals(searchMap.get("options"))) {
                    predicateList.add(cb.like(root.get("options").as(String.class), "%" + (String) searchMap.get("options") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }
}
