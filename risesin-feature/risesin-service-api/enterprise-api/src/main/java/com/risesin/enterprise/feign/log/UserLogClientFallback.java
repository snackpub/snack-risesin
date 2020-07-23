package com.risesin.enterprise.feign.log;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-25
 * @DESCRIPTION 用户操作日志回调
 * @since 1.0.0
 */
@Component
public class UserLogClientFallback implements IUserLogClient{
    @Override
    public R<PageResult> findSearch(Map<String, Object> params, Query query) {
        return R.fail("查询失败");
    }
}
