package com.risesin.systemapi.plan.interfaceresult;

/**
 * 子方案编号与收费项批量展示
 *
 * @author honey
 * @date 2019/12/11 14:36
 */
public interface ActionChildPlanResult {

    /**
     * 子方案编号
     *
     * @return str
     */
    String getChildPlanCode();

    /**
     * 产品收费项
     *
     * @return str
     */
    String getOtherCharges();

    String getChildPlanName();

}
