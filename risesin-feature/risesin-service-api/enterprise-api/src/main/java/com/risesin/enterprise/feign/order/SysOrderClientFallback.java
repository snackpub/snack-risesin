package com.risesin.enterprise.feign.order;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.tool.api.R;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-20
 * @DESCRIPTION 付款订单回调
 * @since 1.0.0
 */
@Component
public class SysOrderClientFallback implements ISysOrderClient{
    @Override
    public R getOrderList(Map<String, Object> params, Query query) {
        return null;
    }

    @Override
    public R getFeeCount(String enterpriseUserId) {
        return null;
    }

    @Override
    public R detail(String id) {
        return null;
    }

    @Override
    public R<BigDecimal> getMonthAmount(Map<String, Object> params) {
        return null;
    }

    @Override
    public R totalAmount(String enterpriseUserId) {
        return null;
    }


    @Override
    public R getUnionAcount(String childPlanId) {
        return null;
    }
}
