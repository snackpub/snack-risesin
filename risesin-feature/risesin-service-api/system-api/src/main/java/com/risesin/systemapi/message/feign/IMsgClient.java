package com.risesin.systemapi.message.feign;

import com.risesin.core.launch.constant.AppConstant;
import com.risesin.core.tool.api.R;
import com.risesin.systemapi.message.entity.MsgCenter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * MsgClient Feign接口类
 *
 * @author honey
 * @date 2019/12/16
 */
@FeignClient(value = AppConstant.APPLICATION_SYSTEM_NAME, fallback = IMsgClientFallback.class)
public interface IMsgClient {

    String API_PREFIX = "/msgCenter";

    /**
     * 消息增加
     *
     * @param msgCenter E
     * @return R
     */
    @PostMapping(API_PREFIX + "/add")
    R<String> add(@RequestBody MsgCenter msgCenter);


}
