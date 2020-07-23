package com.risesin.enterprise.feign.plan;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.tool.api.R;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-24
 * @DESCRIPTION 执行方案回调
 * @since 1.0.0
 */
@Component
public class ActionPlanClientFallback implements IActionPlanClient {

    @Override
    public R get6IncompleteActionPlanInfo(String enterpriseUserId) {
        return R.fail("查询失败");
    }

    @Override
    public R getIncompleteActionPlanInfo(String enterpriseUserId) {
        return R.fail("查询失败");
    }

    @Override
    public R myActionPlans(Map<String, Object> params, Query query) {
        return R.fail("查询失败");
    }

    @Override
    public R detail(String planId) {
        return R.fail("查询失败");
    }

}
