package com.risesin.system.dao.plan;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.plan.entity.EnterpriseAssetInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * enterpriseAssetInfo数据访问接口
 *
 * @author Administrator
 */
public interface EnterpriseAssetInfoDao extends JpaRepository<EnterpriseAssetInfo, Integer>, JpaSpecificationExecutor<EnterpriseAssetInfo>, BaseDao<EnterpriseAssetInfo, Integer> {

}
