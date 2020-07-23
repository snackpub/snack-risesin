package com.risesin.system.dao.plan;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.plan.entity.EnterpriseCreditInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * enterpriseCreditInfo数据访问接口
 *
 * @author Administrator
 */
public interface EnterpriseCreditInfoDao extends JpaRepository<EnterpriseCreditInfo, Integer>, JpaSpecificationExecutor<EnterpriseCreditInfo>, BaseDao<EnterpriseCreditInfo, Integer> {

}
