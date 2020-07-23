package com.risesin.agent.entity.feign;

import com.risesin.agent.entity.feign.vo.MsgCenter;
import com.risesin.core.tool.api.R;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR Baby
 * @CREATE 2019/12/16
 * @DESCRIPTION 客户端消息
 * @since 1.0.0
 */
@Component
public class ClientMsgFeignFallback implements IClientMsgFeign {
    @Override
    public R<String> add(MsgCenter msgCenter) {
        return R.fail("添加消息失败");
    }
}
