package com.risesin.enterprise.feign.plan;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.launch.constant.AppConstant;
import com.risesin.core.tool.api.R;
import com.risesin.enterprise.plan.vo.ActionChildPlan;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-20
 * @DESCRIPTION 子方案Feign
 * @since 1.0.0
 */
@FeignClient(
        value = AppConstant.APPLICATION_SYSTEM_NAME,
        fallback = ActionChildPlanClientFallback.class
)
public interface IActionChildPlanClient {

    String API_PREFIX = "/actionChildPlan";


    /**
     * 执行子方案展示
     *
     * @param searchInfo 条件:childPlanCode,flow,pageNo,pageSize,planId(必填)
     * @param query      分页信息
     * @return
     */
    @ApiOperation(value = "查询执行子方案", notes = "传入searchInfo")
    @PostMapping(API_PREFIX + "/childplan")
    R getChildPlans(@RequestBody Map<String, ?> searchInfo, @RequestParam Query query);

    /**
     * 未完成的子方案列表
     *
     * @param planId
     * @return
     */
    @ApiOperation(value = "未完成的子方案列表", notes = "传入planId")
    @GetMapping(API_PREFIX + "/incompleteChildPlanList/{planId}")
    R getIncompleteActionPlanInfo(@PathVariable String planId);

    /**
     * 终审额度确认
     *
     * @param actionChildPlan
     * @return
     */
    @PostMapping(API_PREFIX + "/confirmAmount")
    @ApiOperation(value = "终审额度确认", notes = "传入childPlanCode和accept")
    R confirmAmount(@RequestBody ActionChildPlan actionChildPlan);

    /**
     * 确认收款将子方案流程状态改为确认收款(10)
     *
     * @param actionChildPlan
     * @return
     */
    @PostMapping(API_PREFIX + "/updateFlowByCode")
    @ApiOperation(value = "更新子方案flow", notes = "传入childplancode,flow")
    R<String> updateFlowByCode(@RequestBody ActionChildPlan actionChildPlan);



}
