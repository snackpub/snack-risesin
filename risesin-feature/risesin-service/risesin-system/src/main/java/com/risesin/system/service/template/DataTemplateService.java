package com.risesin.system.service.template;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.template.DataTemplateDao;
import com.risesin.systemapi.dict.entity.DataTemplate;
import com.risesin.systemapi.system.entity.SysLoanAgency;
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
 * 资料模板服务层
 *
 * @author honey
 * @date 2019/12/13
 */
public interface DataTemplateService extends RisesinBaseService<DataTemplate, Integer> {

    /**
     * 自定义分页
     *
     * @param whereMap 参数条件
     * @param query    分页
     * @return Page<SysLoanAgency>
     */
    Page<DataTemplate> findSearch(Map whereMap, Query query);


    void update(DataTemplate dataTemplate);


}
