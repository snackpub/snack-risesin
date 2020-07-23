package com.risesin.enterprise.feign.plan;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.tool.api.R;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-24
 * @DESCRIPTION 融资预案回调
 * @since 1.0.0
 */
@Component
public class FinancingPlanClientFallback implements IFinancingPlanClient {

    @Override
    public R applyFinancing(Map financingApplicationMap) {
        return R.fail("申请失败");
    }

    @Override
    public R list(Map<String, Object> params, Query query) {
        return R.fail("查询失败");
    }

    @Override
    public R getFinancingPlanByCode(String financingCode) {
        return R.fail("查询失败");
    }
}
