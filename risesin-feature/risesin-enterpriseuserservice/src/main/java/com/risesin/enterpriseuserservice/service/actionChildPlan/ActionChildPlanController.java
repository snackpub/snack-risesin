package com.risesin.enterpriseuserservice.service.actionChildPlan;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.utils.Func;
import com.risesin.enterprise.feign.plan.IActionChildPlanClient;
import com.risesin.enterprise.plan.vo.ActionChildPlan;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-02
 * @DESCRIPTION
 * @since 1.0.0
 */

@Api(tags = "企业端执行子方案")
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/actionChildPlan")
public class ActionChildPlanController {

    private IActionChildPlanClient actionChildPlanClient;

    /**
     * 执行子方案展示
     *
     * @param searchInfo 条件:childPlanCode,flow,pageNo,pageSize,planId(必填)
     * @param query      分页信息
     * @return
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询执行子方案", notes = "传入searchInfo")
    @PostMapping("/childplan")
    public R getChildPlans(@RequestBody Map<String, ?> searchInfo,@ApiIgnore @RequestBody Query query) {
        if (searchInfo == null) {
            return R.fail("参数有误");
        }
        if (searchInfo.get("planId") == null) {
            return R.fail("id不能为空。");
        }

        return actionChildPlanClient.getChildPlans(searchInfo, query);
    }

    /**
     * 未完成的子方案列表
     *
     * @param planId
     * @return
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "未完成的子方案列表", notes = "传入planId")
    @GetMapping("/incompleteChildPlanList/{planId}")
    public R getIncompleteActionPlanInfo(@PathVariable String planId) {
        if (Func.isNotBlank(planId)) {
            return actionChildPlanClient.getIncompleteActionPlanInfo(planId);
        }
        return R.fail(ResultCode.PARAM_VALID_ERROR.getCode(), ResultCode.PARAM_VALID_ERROR.getMessage());
    }

    /**
     * 终审额度确认
     *
     * @param actionChildPlan
     * @return
     */
    @PostMapping("/confirmAmount")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "终审额度确认", notes = "传入childPlanCode和accept")
    public R confirmAmount(@RequestBody ActionChildPlan actionChildPlan) {
        return actionChildPlanClient.confirmAmount(actionChildPlan);
    }


}
