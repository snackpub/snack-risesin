package com.risesin.enterprise.feign.order;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.tool.api.R;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-20
 * @DESCRIPTION 收款订单回调
 * @since 1.0.0
 */
@Component
public class PanyOrderClientFallback implements IPanyOrderClient {

    @Override
    public R findSearch(Map searchMap, Query query) {
        return R.fail("查询失败");
    }

    @Override
    public R updateFlow(Map params) {
        return R.fail("确认收款失败");
    }

}
