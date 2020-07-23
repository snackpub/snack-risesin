package com.risesin.system.dao.plan;

import com.risesin.systemapi.plan.entity.RiskCheckResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * riskCheckResult数据访问接口
 *
 * @author Administrator
 */
public interface RiskCheckResultDao extends JpaRepository<RiskCheckResult, Integer>, JpaSpecificationExecutor<RiskCheckResult> {

}
