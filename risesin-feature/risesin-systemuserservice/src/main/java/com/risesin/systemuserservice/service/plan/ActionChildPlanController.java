package com.risesin.systemuserservice.service.plan;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.log.exception.ServiceException;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.constant.RisesinConstant;
import com.risesin.core.tool.utils.Func;
import com.risesin.system.service.plan.ActionChildPlanService;
import com.risesin.systemapi.ext.entity.ExtFileData;
import com.risesin.systemapi.order.vo.SysOrderVO;
import com.risesin.systemapi.plan.entity.ActionChildPlan;
import com.risesin.systemapi.plan.vo.ActionChildPlanVO;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-01
 * @DESCRIPTION 执行子方案
 * @since 1.0.0
 */
@Api(tags = "执行子方案管理")
@RestController
@AllArgsConstructor
@RequestMapping("/actionChildPlan")
public class ActionChildPlanController {

    private ActionChildPlanService actionChildPlanService;

    /**
     * 执行子方案展示(C端调用)
     *
     * @param searchInfo 条件:childPlanCode,flow,pageNo,pageSize,planId(必填)
     * @param query      分页信息
     * @return
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询执行子方案(C端调用)", notes = "传入searchInfo")
    @PostMapping("/childplan")
    public R<PageResult> getChildPlans(@RequestBody Map<String, ?> searchInfo, @RequestBody Query query) {
        Page<ActionChildPlan> page = actionChildPlanService.findSearch(searchInfo, QueryUtil.queryIfNullForNew(query).getPageNo(),
                QueryUtil.queryIfNullForNew(query).getPageSize());
        return R.data(new PageResult<>(page.getTotalElements(), page.getContent()));
    }

    /**
     * 根据方案编号查询
     *
     * @param childPlanCode
     * @return
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "根据方案编号查询", notes = "传入方案编号")
    @RequestMapping(value = "/{childPlanCode}", method = RequestMethod.GET)
    public R<ActionChildPlanVO> findByChildPlanCode(@PathVariable String childPlanCode) {
        return R.data(actionChildPlanService.findByChildPlanCode(childPlanCode));
    }

    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param query     查询对象
     * @return 分页结果
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "根据条件分页查询", notes = "根据条件分页查询")
    @RequestMapping(value = "/findPageSearch", method = RequestMethod.POST)
    public R<PageResult> findSearch(@ApiParam(value = "搜索集合map") @RequestBody Map searchMap,
                                    @ApiParam(value = "查询对象") @RequestBody Query query) {
        Page<ActionChildPlan> pageList = actionChildPlanService.findSearch(searchMap, QueryUtil.queryIfNullForNew(query).getPageNo(),
                QueryUtil.queryIfNullForNew(query).getPageSize());

        return R.data(new PageResult<ActionChildPlan>(pageList.getTotalElements(), pageList.getContent()));
    }


    /**
     * 增加
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "子方案增加", notes = "传入ActionChildPlan")
    @PostMapping("/add")
    public R<String> add(@RequestBody @ApiParam("执行子方案VO") ActionChildPlanVO actionChildPlanVO) {
        actionChildPlanService.add(actionChildPlanVO);
        return R.data("增加成功");
    }

    /**
     * 修改
     *
     * @param actionChildPlanVO
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "更新", notes = "update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R<String> update(@RequestBody ActionChildPlanVO actionChildPlanVO) {
        actionChildPlanService.update(actionChildPlanVO);
        return R.data("修改成功");
    }

    /**
     * 删除
     *
     * @param code
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "根据编号删除子方案", notes = "方案编号")
    @RequestMapping(value = "/deleteById/{code}", method = RequestMethod.POST)
    public R<String> delete(@PathVariable String code) {
        actionChildPlanService.deleteById(code);
        return R.data("删除成功");
    }

    /**
     * 修改状态
     *
     * @param actionChildPlan
     * @return
     */
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "更新状态", notes = "传入id和状态")
    @RequestMapping(value = "/updateStatusById", method = RequestMethod.POST)
    public R<String> updateStatusById(@RequestBody ActionChildPlan actionChildPlan) {
        actionChildPlanService.updateStatus(actionChildPlan.getChildPlanCode(), actionChildPlan.getStatus());
        return R.data("修改成功");
    }

    /**
     * 批量删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "批量删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(actionChildPlanService.removeByIds(Func.toStrList(ids)));
    }

    /**
     * 发布方案
     */
    @GetMapping("/releseChildPlan/{code}")
    @ApiOperationSupport(order = 11)
    @ApiOperation(value = "发布方案", notes = "传入code")
    public R<String> releseChildPlan(@PathVariable String code) {
        if (StringUtils.isEmpty(code)) {
            throw new ServiceException(ResultCode.PARAM_MISS);
        }
        // 更新流程到 完善资料
        actionChildPlanService.updateChildPlanFlowByCode(RisesinConstant.COMPLETE_DATA, code);
        return R.data("更新成功");
    }

    /**
     * 资料审核
     */
    @GetMapping("/dataVerification/{code}")
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "资料审核", notes = "传入code")
    public R<List<ExtFileData>> dataVerification(@ApiParam(value = "子方案code", required = true) @PathVariable String code) {
        return R.data(actionChildPlanService.dataVerification(code));
    }

    /**
     * 更新资料流程
     */
    @PostMapping("/updateDataFlowById")
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "更新资料流程", notes = "传入资料实体")
    public R<String> updateDataFlowById(@ApiParam(value = "资料审核id", required = true) @RequestBody ExtFileData extFileData) {
        actionChildPlanService.updateDataFlowById(extFileData); // TODO 需要流转处理人员
        return R.data("操作成功");
    }

    /**
     * 发布风控审核
     */
    @GetMapping("/releseRiskAudit/{code}")
    @ApiOperationSupport(order = 11)
    @ApiOperation(value = "发布风控审核", notes = "传入code")
    public R<String> releseRiskAudit(@PathVariable String code) {
        if (StringUtils.isEmpty(code)) {
            throw new ServiceException(ResultCode.PARAM_MISS);
        }
        // 更新流程到 完善资料
        actionChildPlanService.updateChildPlanFlowByCode(RisesinConstant.RISK_AUDIT, code);
        return R.data("更新成功");
    }

    /**
     * 风控审核
     */
    @GetMapping("/riskControlAudit/{planId}")
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "风控审核", notes = "传入子方案父Id")
    public R<List<ActionChildPlanVO>> riskControlAudit(@ApiParam(value = "执行方案Id") @PathVariable Integer planId) {
        List<ActionChildPlanVO> childPlanVOS = actionChildPlanService.riskControlAudit(planId);
        return R.data(childPlanVOS);
    }

    /**
     * 更新风控审核状态
     */
    @PostMapping("/updateRiskAuditStatusByCode")
    @ApiOperationSupport(order = 11)
    @ApiOperation(value = "更新风控审核状态", notes = "传入code,auditOpinion,auditStatus")
    public R<String> updateRiskAuditStatusByCode(@RequestBody ActionChildPlan actionChildPlan) {
        actionChildPlanService.updateRiskAuditStatusByCode(actionChildPlan);
        return R.data("更新成功");
    }

    /**
     * 提交风控审核
     */
    @GetMapping("/commitRiskAudit/{code}")
    @ApiOperationSupport(order = 11)
    @ApiOperation(value = "提交风控审核", notes = "传入code")
    public R<String> commitRiskAudit(@PathVariable String code) {
        if (StringUtils.isEmpty(code)) {
            throw new ServiceException(ResultCode.PARAM_MISS);
        }
        actionChildPlanService.commitRiskAudit(code);
        return R.data("更新成功");
    }

    /**
     * 更新子方案flow
     */
    @PostMapping("/updateFlowByCode")
    @ApiOperationSupport(order = 12)
    @ApiOperation(value = "更新子方案flow", notes = "传入childplancode,flow")
    public R<String> updateFlowByCode(@RequestBody ActionChildPlan actionChildPlan) {
        actionChildPlanService.updateChildPlanFlowByCode(actionChildPlan.getFlow(), actionChildPlan.getChildPlanCode());
        return R.data("更新成功");
    }

    /**
     * 未完成的子方案列表(C端调用)
     *
     * @param planId
     * @return
     */
    @ApiOperationSupport(order = 13)
    @ApiOperation(value = "未完成的子方案列表(C端调用)", notes = "传入planId")
    @GetMapping("/incompleteChildPlanList/{planId}")
    public R<List<ActionChildPlan>> getIncompleteActionPlanInfo(@PathVariable String planId) {
        if (Func.isNotBlank(planId)) {
            return R.data(actionChildPlanService.getIncompleteChildPlanInfo(Integer.parseInt(planId)));
        }
        return R.fail(ResultCode.PARAM_VALID_ERROR.getCode(), ResultCode.PARAM_VALID_ERROR.getMessage());
    }

    /**
     * 前置收款订单详情
     */
    @ApiOperationSupport(order = 14)
    @ApiOperation(value = "根据子方案编号获取前置收费详情", notes = "传入childPlanCodes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "childPlanCodes", value = "Json格式,所有子方案以逗号分隔的string", paramType = "Body", dataType = "string")
    })
    @PostMapping("/frontItemDetail")
    public R frontItemDetail(@ApiIgnore @RequestBody Map<String, Object> childPlanCodes) {
        String childPlanCodesStr = (String) childPlanCodes.get("childPlanCodes");
        List<com.risesin.systemapi.plan.interfaceresult.ActionChildPlanResult> list = actionChildPlanService.findActionChildPlanByFeeDetails(Func.toStrList(childPlanCodesStr));
        HashMap<String, Object> respMap = new HashMap<>(16);
        respMap.put("item", list);
        respMap.put("flow", 0);
        // 随机产生经手财务 TODO
        respMap.put("financeStaff", 0);
        return R.data(respMap);
    }

    /**
     * 创建订单
     */
    @PostMapping("/createOrder")
    @ApiOperationSupport(order = 15)
    @ApiOperation(value = "创建订单", notes = "传入sysOrder")
    public R<String> createOrder(@RequestBody SysOrderVO sysOrderVO) {
        actionChildPlanService.createOrder(sysOrderVO);
        return R.data("创建成功");
    }

    /**
     * C端终审额度确认
     *
     * @param actionChildPlan
     * @return
     */
    @PostMapping("/confirmAmount")
    @ApiOperationSupport(order = 16)
    @ApiOperation(value = "C端终审额度确认(C端调用)", notes = "传入childPlanCode和accept")
    public R confirmAmount(@RequestBody ActionChildPlan actionChildPlan) {
        actionChildPlanService.confirmAmount(actionChildPlan);
        return R.success("操作成功");
    }








}
