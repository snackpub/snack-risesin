package com.risesin.system.dao.template;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.dict.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * template数据访问接口
 *
 * @author Administrator
 */
public interface TemplateDao extends BaseDao<Template, Integer> {

}
