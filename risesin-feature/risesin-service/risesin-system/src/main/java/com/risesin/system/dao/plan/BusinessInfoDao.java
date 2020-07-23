package com.risesin.system.dao.plan;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.plan.entity.BusinessInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * businessInfo数据访问接口
 *
 * @author Administrator
 */
public interface BusinessInfoDao extends JpaRepository<BusinessInfo, Integer>, JpaSpecificationExecutor<BusinessInfo>, BaseDao<BusinessInfo, Integer> {

}
