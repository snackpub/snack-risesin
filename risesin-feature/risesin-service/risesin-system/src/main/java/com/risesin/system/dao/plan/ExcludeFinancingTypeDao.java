package com.risesin.system.dao.plan;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.plan.entity.ExcludeFinancingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 排除融资预案类型数据访问接口
 *
 * @author Administrator
 */
public interface ExcludeFinancingTypeDao extends JpaRepository<ExcludeFinancingType, Integer>, JpaSpecificationExecutor<ExcludeFinancingType>, BaseDao<ExcludeFinancingType, Integer> {

}
