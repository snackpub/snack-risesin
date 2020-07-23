package com.risesin.systemapi.message.feign;

import com.risesin.core.tool.api.R;
import com.risesin.systemapi.message.entity.MsgCenter;
import org.springframework.stereotype.Component;

/**
 * Feign失败配置
 *
 * @author honey
 * @date 2019/12/16 11:10
 */
@Component
public class IMsgClientFallback implements IMsgClient {
    @Override
    public R<String> add(MsgCenter msgCenter) {
        return R.fail("操作失败");
    }
}
