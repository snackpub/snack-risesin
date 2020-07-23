package com.risesin.agent.entity.feign;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.log.model.LogApi;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @AUTHOR Baby
 * @CREATE 2019/12/18
 * @DESCRIPTION log调用回滚
 * @since 1.0.0
 */
@Component
public class ApiLogFeignFallback implements IApiLogFeign {
    @Override
    public R<LogApi> detailApi(String logId) {
        return R.fail("error");
    }

    @Override
    public R<PageResult> listApi(Map<String, Object> params, Query query) {
        return R.fail("error");
    }
}
