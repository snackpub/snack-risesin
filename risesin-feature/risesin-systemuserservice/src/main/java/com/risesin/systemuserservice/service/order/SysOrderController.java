package com.risesin.systemuserservice.service.order;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.log.annotation.ApiLog;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.utils.Func;
import com.risesin.system.service.order.impl.SysOrderServiceImpl;
import com.risesin.system.wrapper.order.OrderWrapper;
import com.risesin.systemapi.order.entity.SysOrder;
import com.risesin.systemapi.order.vo.FeeAmountVO;
import com.risesin.systemapi.order.vo.FeeCenterCountVO;
import com.risesin.systemapi.order.vo.SysOrderCheckResultVO;
import com.risesin.systemapi.order.vo.SysOrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 系统订单管理
 *
 * @author Darling
 * @date 2019-12-05
 * @since 1.0.0
 */

@RestController
@AllArgsConstructor
@Api(tags = "系统订单管理")
@RequestMapping("/sys/order")
public class SysOrderController {

    private SysOrderServiceImpl sysOrderServiceImpl;


    /**
     * 费用中心信息查询
     *
     * @param params enterpriseUserId(C端必填);status;channelId(缴费方式);payOrderId;enterpriseName,planName(所属方案);childPlanName(方案名称)
     * @param query  分页信息
     * @return ${R}
     */
    @ApiLog("C端列表")
    @ApiOperation(value = "C端列表", notes = "传入params")
    @PostMapping("/orderList")
    public R<PageResult<SysOrder>> getOrderList(@RequestBody Map<String, Object> params, Query query) {
        if (StringUtils.isEmpty((String) params.get("enterpriseUserId"))) {
            return R.fail("id不能为空!");
        }

        Page<SysOrder> page = sysOrderServiceImpl.findSearch(params, query);
        return R.data(new PageResult<>(page.getTotalElements(), page.getContent()));
    }


    /**
     * 分页查询
     *
     * @param params 参数
     * @param query  分页对象
     * @return ${R}
     */
    @ApiLog("列表")
    @ApiOperation(value = "列表", notes = "传入params")
    @PostMapping("/list")
    public R<PageResult<SysOrderVO>> list(@ApiIgnore @RequestBody Map<String, Object> params, @RequestBody Query query) {
        Page<SysOrder> pages = sysOrderServiceImpl.findSearch(params, query);
        return R.data(OrderWrapper.build().pageVO(pages));
    }


    /**
     * 费用中心统计查询(C端调用)
     *
     * @return
     */
    @ApiOperation(value = "费用中心统计查询(C端调用)", notes = "")
    @GetMapping("/feeCount/{enterpriseUserId}")
    public R<FeeCenterCountVO> getFeeCount(@PathVariable String enterpriseUserId) {
        if (Func.isNotBlank(enterpriseUserId)) {
            return R.data(sysOrderServiceImpl.getFeeCount(enterpriseUserId));
        }
        return R.fail(ResultCode.PARAM_VALID_ERROR.getCode(), ResultCode.PARAM_VALID_ERROR.getMessage());
    }


    /**
     * 收款订单详情
     */
    @ApiLog("付款订单详情")
    @ApiOperation(value = "付款订单详情", notes = "收款订单详情")
    @GetMapping("/detail/{id}")
    public R<SysOrderVO> detail(@PathVariable String id) {
        return R.data(OrderWrapper.build().entityVO(sysOrderServiceImpl.findById(id)));
    }


    /**
     * 审核
     */
    @ApiLog("审核")
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "审核", notes = "传入SysOrderCheckResultVO")
    @PostMapping("/verifier")
    public R<String> verifier(@RequestBody SysOrderCheckResultVO sysOrderCheckResultVO) {
        return R.data(sysOrderServiceImpl.verifier(sysOrderCheckResultVO));
    }


    /**
     * 新增订单
     */
    @ApiLog("新增订单")
    @ApiOperationSupport(order = 11)
    @ApiOperation(value = "新增订单", notes = "传入SysOrder")
    @PostMapping("/add")
    public R<String> add(@RequestBody SysOrderVO orderVO) {
        sysOrderServiceImpl.add(orderVO);
        return R.success(ResultCode.SUCCESS);
    }

    /**
     * 确认收款
     */
    @ApiLog("确认收款")
    @ApiOperationSupport(order = 12)
    @ApiOperation(value = "确认收款", notes = "传入SysOrder")
    @PostMapping("/confirmPayment")
    public R<String> confirmPayment(@RequestBody SysOrder order) {
        return R.status(sysOrderServiceImpl.confirmPayment(order));
    }


    /**
     * 查询当年某月的消费总额(月总额)(C端调用)
     *
     * @param params
     * @return
     */
    @ApiOperationSupport(order = 13)
    @ApiOperation(value = "查询当年某月的消费总额(月总额)(C端调用)", notes = "传入map")
    @PostMapping("/monthAount")
    public R<BigDecimal> getMonthAmount(@RequestBody Map<String, Object> params) {
        BigDecimal amount = sysOrderServiceImpl.getMonthAmount(params);
        return R.data(amount);
    }

    /**
     * 咨询费用总额和收费项总额(C端调用)
     *
     * @return
     */
    @ApiOperationSupport(order = 14)
    @ApiOperation(value = "咨询费用总额和收费项总额(C端调用)", notes = "")
    @GetMapping("/totalAmount/{enterpriseUserId}")
    public R<FeeAmountVO> totalAmount(@PathVariable String enterpriseUserId) {
        if (Func.isNotBlank(enterpriseUserId)) {
            return R.data(sysOrderServiceImpl.getFeeAmount(enterpriseUserId));
        }
        return R.fail(ResultCode.PARAM_VALID_ERROR.getCode(), ResultCode.PARAM_VALID_ERROR.getMessage());
    }

    @ApiOperationSupport(order = 15)
    @ApiOperation(value = "获取订单信息(网银转账账号,C端调用)", notes = "传入childPlanId")
    @GetMapping("/unionAcount/{childPlanCode}")
    public R getUnionAcount(@PathVariable String childPlanCode) {
        if (Func.isNotBlank(childPlanCode)) {
            return R.data(sysOrderServiceImpl.getUnionAcount(childPlanCode));
        }
        return R.fail(ResultCode.PARAM_VALID_ERROR.getCode(), ResultCode.PARAM_VALID_ERROR.getMessage());
    }


}
