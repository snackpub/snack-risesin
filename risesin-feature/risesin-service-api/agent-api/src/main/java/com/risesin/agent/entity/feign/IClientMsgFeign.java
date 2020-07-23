/**
 *
 */
package com.risesin.agent.entity.feign;


import com.risesin.agent.entity.feign.vo.MsgCenter;
import com.risesin.core.launch.constant.AppConstant;
import com.risesin.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign接口类
 *
 * @author honey
 */
@FeignClient(
        value = AppConstant.APPLICATION_ENTERPRISE_USER_SERVICE,
        fallback = ClientMsgFeignFallback.class
)
public interface IClientMsgFeign {

    String API_PREFIX = "/message";

    @PostMapping(API_PREFIX + "/add")
    R<String> add(@RequestBody MsgCenter msgCenter);

}
