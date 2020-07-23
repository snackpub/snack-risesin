package com.risesin.system.dao.plan;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.plan.entity.ActionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * actionPlan数据访问接口
 *
 * @author Administrator
 */
public interface ActionPlanDao extends JpaRepository<ActionPlan, Integer>, JpaSpecificationExecutor<ActionPlan>, BaseDao<ActionPlan, Integer> {

    /**
     * 根据时间降序查询前6条未完成的执行方案
     * select * from t_action_plan where fk_ent_user_id = ? and flow is in(0,1) order by create_time desc limit 6
     *
     * @param enterpriseUserId 企业用户id
     * @param flow            方案状态：申请(0)和进行中(1)
     * @param status           0(正常)
     * @return
     */
    List<ActionPlan> findTop6ByEntUserIdAndFlowIsInAndStatusOrderByCreateTimeDesc(String enterpriseUserId, byte flow[], byte status);

    /**
     * 根据时间降序查询未完成的执行方案(同上方法几乎一致)
     * select * from t_action_plan where fk_ent_user_id = ? and flow is in(0,1) order by create_time desc
     *
     * @param enterpriseUserId 企业用户id
     * @param flow            方案状态：申请(0)和进行中(1)
     * @param status           0(正常)
     * @return
     */
    List<ActionPlan> findActionPlansByEntUserIdAndFlowIsInAndStatusOrderByCreateTimeDesc(String enterpriseUserId, byte flow[], byte status);


    /**
     * 根据方案Code查询执行方案
     *
     * @param planCode 方案Code
     * @return ActionPlan
     */
    ActionPlan findActionPlanByPlanCode(@Param("planCode") String planCode);


    int countByEntUserIdAndFlowIsInAndStatus(String enterpriseUserId, byte flow[], byte status);


}
