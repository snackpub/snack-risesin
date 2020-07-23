package com.risesin.enterprise.feign.plan;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.launch.constant.AppConstant;
import com.risesin.core.tool.api.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(
        value = AppConstant.APPLICATION_SYSTEM_NAME,
        fallback = ActionPlanClientFallback.class
)
public interface IActionPlanClient {

    String API_PREFIX = "/actionPlan";

    /**
     * 主页展示6条未完成的融资方案
     *
     * @param enterpriseUserId
     * @return
     */
    @ApiOperation(value = "主页展示6条未完成的融资方案", notes = "传入enterpriseUserId")
    @GetMapping(API_PREFIX + "/incompletePlan/{enterpriseUserId}")
    R get6IncompleteActionPlanInfo(@PathVariable String enterpriseUserId);


    /**
     * 未完成的父执行方案列表
     *
     * @param enterpriseUserId
     * @return
     */
    @ApiOperation(value = "未完成的父执行方案列表", notes = "传入token")
    @GetMapping(API_PREFIX + "/incompletePlanList/{enterpriseUserId}")
    R getIncompleteActionPlanInfo(@PathVariable String enterpriseUserId);

    /**
     * 查询我的融资方案
     *
     * @param params 查询条件:flow(流程状态),companyName,actionPlanCode,enterpriseUserId(C端必填)
     * @param query  分页信息
     * @returns R
     */
    @ApiOperation(value = "“我的融资方案”(C端调用)", notes = "传入token")
    @PostMapping(API_PREFIX + "/myplans")
    R myActionPlans(@RequestBody Map<String, Object> params, @RequestParam Query query);

    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "详情", notes = "传入actionPlanId")
    R detail(@RequestParam @ApiParam("主键ID") String planId);


}
