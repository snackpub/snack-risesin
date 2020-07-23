package com.risesin.enterprise.feign.order;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.launch.constant.AppConstant;
import com.risesin.core.tool.api.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-20
 * @DESCRIPTION 付款订单Feign
 * @since 1.0.0
 */
@FeignClient(
        value = AppConstant.APPLICATION_SYSTEM_NAME,
        fallback = SysOrderClientFallback.class
)
public interface ISysOrderClient {

    String API_PREFIX = "/sys/order";

    /**
     * 费用中心信息查询
     *
     * @param params enterpriseUserId(C端必填);status;channelId(缴费方式);payOrderId;enterpriseName,planName(所属方案);childPlanName(方案名称)
     * @param query  分页信息
     * @return ${R}
     */
    @ApiOperation(value = "C端列表", notes = "传入params")
    @PostMapping(API_PREFIX + "/orderList")
    R getOrderList(@RequestBody Map<String, Object> params, @RequestParam Query query);


    /**
     * 费用中心统计查询
     *
     * @param enterpriseUserId
     * @return
     */
    @ApiOperation(value = "费用中心统计查询(C端调用)", notes = "")
    @GetMapping(API_PREFIX + "/feeCount/{enterpriseUserId}")
    R getFeeCount(@PathVariable String enterpriseUserId);


    /**
     * 收款订单详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "付款订单详情", notes = "订单id")
    @GetMapping(API_PREFIX + "/detail/{id}")
    R detail(@PathVariable String id);

    /**
     * 查询当年某月的消费总额(月总额)
     *
     * @param params
     * @return
     */
    @ApiOperation(value = "查询当年某月的消费总额(月总额)", notes = "传入map")
    @PostMapping(API_PREFIX + "/monthAount")
    R<BigDecimal> getMonthAmount(@RequestBody Map<String, Object> params);

    /**
     * 咨询费用总额和收费项总额
     *
     * @param enterpriseUserId
     * @return
     */
    @ApiOperation(value = "咨询费用总额和收费项总额", notes = "")
    @GetMapping(API_PREFIX + "/totalAmount/{enterpriseUserId}")
    R totalAmount(@PathVariable String enterpriseUserId);

    /**
     * 获取订单信息(网银转账账号)
     *
     * @param childPlanId
     * @return
     */
    @ApiOperation(value = "获取订单信息(网银转账账号)", notes = "传入childPlanId")
    @GetMapping(API_PREFIX + "/unionAcount/{childPlanId}")
    R getUnionAcount(@PathVariable String childPlanId);


}
