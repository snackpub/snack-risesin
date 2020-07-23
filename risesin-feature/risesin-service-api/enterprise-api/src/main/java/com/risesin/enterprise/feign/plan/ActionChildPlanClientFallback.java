package com.risesin.enterprise.feign.plan;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.tool.api.R;
import com.risesin.enterprise.plan.vo.ActionChildPlan;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-20
 * @DESCRIPTION 子方案回调类
 * @since 1.0.0
 */
@Component
public class ActionChildPlanClientFallback implements IActionChildPlanClient {

    @Override
    public R getChildPlans(Map<String, ?> searchInfo, Query query) {
        return R.fail("查询失败");
    }

    @Override
    public R getIncompleteActionPlanInfo(String planId) {
        return R.fail("查询失败");
    }

    @Override
    public R confirmAmount(ActionChildPlan actionChildPlan) {
        return R.fail("确认额度失败");
    }

    @Override
    public R<String> updateFlowByCode(ActionChildPlan actionChildPlan) {
        return R.fail("确认收款失败");
    }

}
