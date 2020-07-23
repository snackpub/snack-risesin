package com.risesin.system.dao.plan;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.plan.entity.ActionChildPlan;
import com.risesin.systemapi.plan.interfaceresult.ActionChildPlanResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * actionChildPlan数据访问接口
 *
 * @author Administrator
 */
public interface ActionChildPlanDao extends JpaRepository<ActionChildPlan, Integer>, JpaSpecificationExecutor<ActionChildPlan>, BaseDao<ActionChildPlan, Integer> {

    /**
     * 根据方案编号查询
     *
     * @param childPlanCode
     * @return
     */
    ActionChildPlan findByChildPlanCode(String childPlanCode);

    /**
     * 根据父方案id查询所有
     *
     * @param planId
     * @return
     */
    List<ActionChildPlan> findAllByPlanId(Integer planId);

    /**
     * 查询未完成的子方案列表
     *
     * @param planId 方案id
     * @param flow   流程状态：不等于 结束(12);关闭(13)
     * @param status 正常(0)
     * @return
     */
    List<ActionChildPlan> findActionChildPlansByPlanIdAndFlowNotInAndStatus(Integer planId, byte[] flow, byte status);

    /**
     * 更新风控审核状态
     * @param childPlanCode
     * @param auditStatus
     * @param auditOpinion
     */
    @Query("update ActionChildPlan t set t.auditStatus=:auditStatus,t.auditOpinion=:auditOpinion where t.childPlanCode=:childPlanCode")
    @Modifying
    @Transactional
    void updateRiskAuditStatusByCode(@Param("childPlanCode") String childPlanCode, @Param("auditStatus") Byte auditStatus, @Param("auditOpinion") String auditOpinion);

    /**
     * 根据子方案编号批量查询子方案详情
     * 获取收费项与子方案编号
     *
     * @param childPlanCodes 子方案编号集合
     * @return ActionChildPlanResult
     */
    @Query(value = "SELECT child_plan_code as childPlanCode,other_charges as otherCharges,child_plan_name as childPlanName FROM t_action_child_plan WHERE child_plan_code IN (?1)", nativeQuery = true)
    List<ActionChildPlanResult> findActionChildPlanByFeeDetails(Collection<? extends Serializable> childPlanCodes);

    /**
     * 根据子方案修改流程
     * @param flow
     * @param code
     */
    @Query("update ActionChildPlan t set t.flow=:flow where t.childPlanCode=:code")
    @Modifying
    @Transactional
    void updateChildPlanFlow(@Param("flow") byte flow, @Param("code") String code);
}
