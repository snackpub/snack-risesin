/**
 *
 */
package com.risesin.systemapi.feign;

import com.risesin.core.tool.api.R;
import com.risesin.systemapi.dict.entity.Dict;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Feign失败配置
 *
 * @author honey
 */
@Component
public class IDictClientFallback implements IDictClient {

    @Override
    public R<String> getValue(String code, Integer dictKey) {
        return R.fail("获取数据失败");
    }

    @Override
    public R<List<Dict>> getList(String code) {
        return R.fail("获取数据失败");
    }
}
