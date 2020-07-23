package com.risesin.system.dao.flowlog;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.flowlog.entity.TransferRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * transferRecord数据访问接口
 *
 * @author Administrator
 */
public interface TransferRecordDao extends JpaRepository<TransferRecord, Integer>, JpaSpecificationExecutor<TransferRecord>, BaseDao<TransferRecord, Integer> {

    @Query("select distinct(planCode) from TransferRecord t where t.handleBy =?1")
    List<String> getActionPlanId(String sysUserId);

}
