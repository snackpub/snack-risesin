package com.risesin.enterprise.feign.plan;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.launch.constant.AppConstant;
import com.risesin.core.tool.api.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(
        value = AppConstant.APPLICATION_SYSTEM_NAME,
        fallback = FinancingPlanClientFallback.class
)
public interface IFinancingPlanClient {

    String API_PREFIX = "/finPlan";

    /**
     * 企业端融资申请
     *
     * @param financingApplicationMap
     * @return
     */
    @ApiOperation(value = "融资申请", notes = "传入financingApplication")
    @PostMapping(API_PREFIX + "/applyFinancing")
    R applyFinancing(@RequestBody @ApiParam("financingApplication对象") Map financingApplicationMap);

    /**
     * 列表
     */
    @PostMapping(API_PREFIX + "/list")
    @ApiOperation(value = "列表", notes = "列表查询")
    R list(@RequestBody Map<String, Object> params, @RequestParam Query query);

    /**
     * 根据code查询(C端调用)
     *
     * @param financingCode 融资预案Code
     * @return
     */
    @ApiOperation(value = "根据code查询", notes = "传入financingCode")
    @PostMapping(API_PREFIX + "/findByCode/{financingCode}")
    R getFinancingPlanByCode(@PathVariable String financingCode);


}
