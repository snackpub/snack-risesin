package com.risesin.system.dao.plan;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.plan.entity.FinancingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

/**
 * financingPlan数据访问接口
 *
 * @author Administrator
 */
public interface FinancingPlanDao extends JpaRepository<FinancingPlan, Integer>, JpaSpecificationExecutor<FinancingPlan>, BaseDao<FinancingPlan, Integer> {


    /**
     * 根据企业用户ID查询融资预案
     *
     * @param financingCode
     * @return FinancingPlan
     */
    FinancingPlan findByFinancingCode(@Param("financingCode") String financingCode);

    int countByEntUserIdAndAndCompanyName(String entUserId,String companyName);


}
