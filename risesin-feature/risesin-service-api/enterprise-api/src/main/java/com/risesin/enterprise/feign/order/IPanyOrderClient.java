package com.risesin.enterprise.feign.order;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.launch.constant.AppConstant;
import com.risesin.core.tool.api.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-20
 * @DESCRIPTION 收款订单Feign
 * @since 1.0.0
 */
@FeignClient(
        value = AppConstant.APPLICATION_AGEN_USER_SERVICE_NAME,
        fallback = PanyOrderClientFallback.class
)
public interface IPanyOrderClient {

    String API_PREFIX = "/comPanyOrder";

    @ApiOperation(value = "根据条件分页查询", notes = "根据条件分页查询")
    @PostMapping(API_PREFIX + "/findPageSearch")
    R findSearch(@ApiParam(value = "搜索集合map") @RequestBody Map searchMap,
                               @ApiParam(value = "第几页") @RequestParam Query query);

    @PostMapping(API_PREFIX + "/updateFlow")
    R updateFlow(Map params);

}
