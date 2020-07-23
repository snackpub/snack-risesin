package com.risesin.enterprise.feign.dict;

import com.risesin.core.tool.api.R;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-24
 * @DESCRIPTION 行业回调
 * @since 1.0.0
 */
@Component
public class IndustryClientFallback implements IIndustryClient{


    @Override
    public R listByPid(String parentCode) {
        return R.fail("查询失败");
    }

    @Override
    public R tree(Byte status) {
        return R.fail("查询失败");
    }
}
