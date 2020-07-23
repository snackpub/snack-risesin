package com.risesin.agent.service.plan.controller;

import com.risesin.agent.entity.feign.IPlanServiceFeign;
import com.risesin.agent.entity.feign.vo.ActionChildPlan;
import com.risesin.agent.entity.feign.vo.ActionChildPlanVO;
import com.risesin.agent.entity.feign.vo.ExtFileData;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.log.annotation.ApiLog;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.constant.RisesinConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @AUTHOR Baby
 * @CREATE 2019/11/25
 * @DESCRIPTION 方案管理
 * @since 1.0.0
 */
@RestController
@RequestMapping("/plan")
@Api(tags = "助贷端--方案管理")
@AllArgsConstructor
public class PlanController {

    private IPlanServiceFeign planServiceFeign;
    /**
     * 根据方案编号查询
     * @param childPlanCode
     * @return
     */
    @ApiLog(type = "1")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "根据方案编号查询", notes = "传入方案编号")
    @RequestMapping(value = "/{childPlanCode}", method = RequestMethod.GET)
    public R<ActionChildPlanVO> findByChildPlanCode(@PathVariable String childPlanCode) {
        return planServiceFeign.findByChildPlanCode(childPlanCode);
    }

    /**
     * 分页+多条件查询
     * @param searchMap 查询条件封装
     * @param query 查询对象
     * @return 分页结果
     */
    @ApiLog(type = "1")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "根据条件分页查询", notes = "根据条件分页查询")
    @RequestMapping(value="/findPageSearch",method=RequestMethod.POST)
    public R<PageResult> findSearch(@ApiParam(value="搜索集合map") @RequestBody Map searchMap ,
                                    @ApiParam(value="查询对象")@RequestBody Query query){
        return planServiceFeign.findSearch(searchMap,query);
    }

    /**
     * 更新子方案flow
     */
    @ApiLog(type = "1")
    @PostMapping("/updateFlowByCode")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "更新子方案flow", notes = "传入childplancode,flow")
    public R<String> updateFlowByCode(@RequestBody ActionChildPlan actionChildPlan) {
        return planServiceFeign.updateFlowByCode(actionChildPlan);
    }

    /**
     * 查询客户上传所有资料
     */
    @ApiLog(type = "1")
    @GetMapping("/dataVerification/{code}")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "查询客户上传所有资料", notes = "传入code")
    public R<List<ExtFileData>> dataVerification(@ApiParam(value = "子方案code", required = true) @PathVariable String code) {
        return planServiceFeign.dataVerification(code);
    }

    /**
     * 审核额度
     * @param actionChildPlanVO
     */
    @ApiLog(type = "1")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "审核额度", notes = "传入审核意见和终审额度")
    @RequestMapping(value="/auditAmount",method= RequestMethod.POST)
    public R<String> auditAmount(@RequestBody ActionChildPlanVO actionChildPlanVO ){
        // 修改流程
        actionChildPlanVO.setFlow(RisesinConstant.CUSTOMER_CONFIRM);
        planServiceFeign.updateFlowByCode(actionChildPlanVO);
        return planServiceFeign.update(actionChildPlanVO);
    }

}
