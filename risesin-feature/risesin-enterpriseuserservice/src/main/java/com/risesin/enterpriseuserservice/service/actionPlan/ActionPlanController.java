package com.risesin.enterpriseuserservice.service.actionPlan;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.utils.Func;
import com.risesin.enterprise.feign.plan.IActionPlanClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-02
 * @DESCRIPTION 执行方案
 * @since 1.0.0
 */
@Api(tags = "企业端执行方案")
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/actionPlan")
public class ActionPlanController {

    private IActionPlanClient actionPlanClient;

    /**
     * 主页展示6条未完成的融资方案
     *
     * @return
     */
    @ApiOperation(value = "主页展示6条未完成的融资方案", notes = "")
    @GetMapping("/incompletePlan")
    public R get6IncompleteActionPlanInfo() {
        //TODO userId
        String userId = "1";
        return actionPlanClient.get6IncompleteActionPlanInfo(userId);
    }

    /**
     * 未完成的父执行方案列表
     *
     * @return
     */
    @ApiOperation(value = "未完成的父执行方案列表", notes = "传入token")
    @GetMapping("/incompletePlanList")
    public R getIncompleteActionPlanInfo() {
        //TODO userId
        String userId = "1";
        R incompleteActionPlanInfo = actionPlanClient.getIncompleteActionPlanInfo(userId);
        return incompleteActionPlanInfo;
    }

    /**
     * 查询我的融资方案
     *
     * @param params 查询条件:flow(流程状态),companyName,actionPlanCode,enterpriseUserId
     * @param query  分页信息
     * @returns R
     */
    @ApiOperation(value = "执行方案列表", notes = "传入token")
    @PostMapping("/myplans")
    public R myActionPlan(@RequestBody Map<String, Object> params, @ApiIgnore @RequestBody Query query) {
        //TODO userId
        params.put("enterpriseUserId", "1");
        return actionPlanClient.myActionPlans(params, query);
    }

    /**
     * 详情
     *
     * @param planId
     * @return
     */
    @GetMapping("/detail")
    @ApiOperation(value = "详情", notes = "传入actionPlanId")
    R detail(@RequestParam @ApiParam("主键ID") String planId) {
        if (Func.isBlank(planId)) {
            return R.fail("id为空");
        }
        return actionPlanClient.detail(planId);
    }


}
