package com.risesin.enterpriseuserservice.service.order;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.constant.RisesinConstant;
import com.risesin.core.tool.utils.Func;
import com.risesin.enterprise.feign.order.IPanyOrderClient;
import com.risesin.enterprise.feign.order.ISysOrderClient;
import com.risesin.enterprise.feign.plan.IActionChildPlanClient;
import com.risesin.enterprise.plan.vo.ActionChildPlan;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-11-28
 * @DESCRIPTION 费用订单
 * @since 1.0.0
 */

@Api(tags = "费用中心")
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/feeCenter")
public class OrderController {

    private ISysOrderClient sysOrderClient;

    private IPanyOrderClient panyOrderClient;

    private IActionChildPlanClient actionChildPlanClient;


    /**
     * 费用中心信息查询
     *
     * @param params enterpriseUserId(C端必填);status;channelId(缴费方式);payOrderId;enterpriseName,planName(所属方案);childPlanName(方案名称)
     * @param query  分页信息
     * @return ${R}
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "C端订单列表", notes = "传入params")
    @PostMapping("/orderList")
    public R getOrderList(@ApiIgnore @RequestBody Map<String, Object> params, @RequestBody Query query) {
        //TODO 根据token获取id
        params.put("enterpriseUserId", "1");
        return sysOrderClient.getOrderList(params, query);
    }

    /**
     * 费用中心统计查询(C端调用)
     *
     * @return
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "费用中心统计查询", notes = "")
    @GetMapping("/feeCount")
    public R getFeeCount() {
        //TODO 根据token获取用户id
        String enterpriseUserId = "1";
        return sysOrderClient.getFeeCount(enterpriseUserId);
    }


    /**
     * 收款订单详情
     *
     * @param id
     * @return
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "付款订单详情", notes = "传入订单id")
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable String id) {
        return sysOrderClient.detail(id);
    }

    /**
     * 查询当年某月的消费总额(月总额)
     *
     * @param params enterpriseId,year,month
     * @return
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "查询当年某月的消费总额(月总额)", notes = "传入{year,month}")
    @PostMapping("/monthAount")
    public R<BigDecimal> getMonthAmount(@RequestBody Map<String, Object> params) {
        //TODO 根据token获取id
        params.put("enterpriseUserId", "1");
        return sysOrderClient.getMonthAmount(params);
    }

    /**
     * 首页咨询费用总额和收费项总额
     *
     * @return
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "首页咨询费用总额和收费项总额", notes = "")
    @GetMapping("/totalAmount")
    public R totalAmount() {
        //TODO 根据token获取id
        String enterpriseUserId = "1";
        return sysOrderClient.totalAmount(enterpriseUserId);
    }

    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "获取订单信息(网银转账账号)", notes = "传入childPlanCode")
    @GetMapping("/unionAcount/{childPlanCode}")
    public R getUnionAcount(@PathVariable String childPlanCode) {
        if (Func.isNotBlank(childPlanCode)) {
            return sysOrderClient.getUnionAcount(childPlanCode);
        }
        return R.fail(ResultCode.PARAM_VALID_ERROR.getCode(), ResultCode.PARAM_VALID_ERROR.getMessage());
    }

    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "收款订单列表", notes = "传入params")
    @PostMapping("/receiptOrderList")
    public R getReceiptOrderList(@ApiParam(value = "搜索集合map") @RequestBody Map params,
                                 @ApiParam(value = "第几页") @RequestBody Query query) {
        //TODO 根据token获取用户id
        params.put("enterpriseUserId", "1");
        //状态为已放款(5)
        params.put("flow", RisesinConstant.HAS_LENDING);
        return panyOrderClient.findSearch(params, query);
    }


    /**
     * @param params
     * @return
     */
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "确认收款", notes = "传入id和childPlanCode")
    @PostMapping("/comfirm")
    public R comfirmReceipt(@RequestBody Map params) {

        //将助贷端订单状态改为确认收款(6)
        params.put("flow", RisesinConstant.CONFIRM_RECEIPT);
        panyOrderClient.updateFlow(params);

        //将子方案流程状态改为确认收款(10)
        ActionChildPlan actionChildPlan = new ActionChildPlan();
        actionChildPlan.setChildPlanCode((String) params.get("childPlanCode"));
        actionChildPlan.setFlow(RisesinConstant.CONFIRM_RECEIVER);
        actionChildPlanClient.updateFlowByCode(actionChildPlan);

        return R.success("ok");
    }
}
