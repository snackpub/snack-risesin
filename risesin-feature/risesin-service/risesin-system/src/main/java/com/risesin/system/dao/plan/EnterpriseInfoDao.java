package com.risesin.system.dao.plan;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.plan.entity.EnterpriseInfo;
import com.risesin.systemapi.plan.interfaceresult.FinancingBarInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * enterpriseInfo数据访问接口
 *
 * @author Administrator
 */
public interface EnterpriseInfoDao extends JpaRepository<EnterpriseInfo, Integer>, JpaSpecificationExecutor<EnterpriseInfo>, BaseDao<EnterpriseInfo, Integer> {

    @Query(value = "select f.company_name as companyName,f.pk_id as id,DATE_FORMAT(f.create_time,'%Y-%m-%d') as createTime " +
            "from t_financing_plan f  where f.fk_ent_user_id = 1 order by f.create_time desc limit 5", nativeQuery = true)
    List<FinancingBarInfo> find5FinancingPlanInfo(@Param("enterpriseUserId") String enterpriseUserId);

}
