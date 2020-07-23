package com.risesin.enterpriseuserservice.service.financingPlan;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.tool.api.R;
import com.risesin.enterprise.feign.plan.IFinancingPlanClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-11-27
 * @DESCRIPTION 企业端融资预案
 * @since 1.0.0
 */

@Api(tags = "企业端融资预案")
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/finPlan")
public class FinancingPlanController {

    private IFinancingPlanClient financingPlanClient;

    /**
     * 企业端融资申请
     *
     * @param financingApplication
     * @return
     */
    @ApiOperation(value = "融资申请", notes = "传入financingApplication")
    @PostMapping("/applyFinancing")
    @ApiOperationSupport(order = 1)
    public R applyFinancing(@RequestBody @ApiParam("financingApplication对象") Map financingApplication) {
        return financingPlanClient.applyFinancing(financingApplication);
    }

    /**
     *  融资预案列表
     * @param params
     * @param query
     * @return
     */
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "列表查询")
    @ApiOperationSupport(order = 2)
    public R list(@RequestBody Map<String, Object> params,@ApiIgnore @RequestBody Query query) {
        //TODO userId
        params.put("enterpriseUserId","1");
        return financingPlanClient.list(params,query);
    }

    /**
     * 根据code查询
     *
     * @param financingCode 融资预案Code
     * @return
     */
    @ApiOperation(value = "根据code查询(C端调用)", notes = "传入financingCode")
    @ApiOperationSupport(order = 3)
    @PostMapping("/findByCode/{financingCode}")
    public R getFinancingPlanByCode(@PathVariable String financingCode) {
        return financingPlanClient.getFinancingPlanByCode(financingCode);
    }

}
