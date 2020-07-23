package com.risesin.system.dao.plan;

import com.risesin.core.base.BaseDao;
import com.risesin.core.tool.api.R;
import com.risesin.systemapi.plan.entity.FinancingDemand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

/**
 * 融资需求数据访问接口
 *
 * @author Administrator
 */
public interface FinancingDemandDao extends JpaRepository<FinancingDemand, Integer>, JpaSpecificationExecutor<FinancingDemand>, BaseDao<FinancingDemand, Integer> {


    /**
     * 获取融资金额
     *
     * @param id ID
     * @return R
     */
    @Query("select fd.financingAmount from FinancingDemand fd where fd.id = :id")
    R<BigDecimal> getAmount(Integer id);

}
