package com.risesin.system.dao.plan;

import com.risesin.systemapi.plan.entity.InfoCheckResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * infoCheckResult数据访问接口
 *
 * @author Administrator
 */
public interface InfoCheckResultDao extends JpaRepository<InfoCheckResult, Integer>, JpaSpecificationExecutor<InfoCheckResult> {

}
