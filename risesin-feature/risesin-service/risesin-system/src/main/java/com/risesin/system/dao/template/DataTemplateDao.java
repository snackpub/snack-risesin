package com.risesin.system.dao.template;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.dict.entity.DataTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * dataTemplate数据访问接口
 *
 * @author Administrator
 */
@Repository
public interface DataTemplateDao extends BaseDao<DataTemplate, Integer> {

}
