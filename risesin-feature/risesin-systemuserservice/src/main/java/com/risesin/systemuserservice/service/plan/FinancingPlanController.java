package com.risesin.systemuserservice.service.plan;

import com.risesin.core.boot.ctrl.RisesinController;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.log.annotation.ApiLog;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.jackson.JsonUtil;
import com.risesin.core.tool.utils.Func;
import com.risesin.system.service.plan.FinancingPlanService;
import com.risesin.system.wrapper.plan.FinancingPlanWrapper;
import com.risesin.systemapi.plan.bo.FinancingApplication;
import com.risesin.systemapi.plan.entity.FinancingPlan;
import com.risesin.systemapi.plan.vo.FinancingPlanVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * 融资预案管理
 *
 * @author : honey
 * @date : 2019-11-28 11:57
 **/
@RestController
@AllArgsConstructor
@Api(tags = "融资预案管理")
@RequestMapping("/finPlan")
@CrossOrigin
public class FinancingPlanController extends RisesinController {

    private FinancingPlanService service;


    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入ID")
    public R<FinancingPlan> detail(@RequestParam @ApiParam("主键ID") @NotBlank(message = "id不能为空") String id) {
        FinancingPlan detail = service.findById(Func.toInt(id));
        return R.data(detail);
    }

    /**
     * 新增
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "新增", notes = "传入FinancingPlan")
    public R submit(@Valid @RequestBody @ApiParam("FinancingPlan对象") FinancingPlan financingPlan) {
        service.add(financingPlan);
        return R.success("操作成功!");
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "修改", notes = "传入FinancingPlan")
    public R update(@Valid @RequestBody @ApiParam("FinancingPlan对象") FinancingPlan financingPlan) {
        service.update(financingPlan);
        return R.success("操作成功!");
    }


    /**
     * 列表
     */
    @ApiLog("融资预案列表查询")
    @PostMapping("/list")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "列表", notes = "列表查询")
    public R<PageResult<FinancingPlanVO>> list(@RequestBody Map<String, Object> params, @ApiIgnore @RequestBody Query query) {
        Page<FinancingPlan> search = service.findSearch(params, query);
        return R.data(FinancingPlanWrapper.build().pageVO(search));
    }

    @PostMapping("/remove")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键", required = true) @RequestParam String ids) {
        return R.status(service.removeBatch(Func.toIntList(ids)));
    }


    /**
     * 企业端融资申请(C端调用)
     *
     * @param financingApplicationMap
     * @return
     */
    @ApiOperation(value = "融资申请(C端调用)", notes = "传入financingApplicationMap")
    @ApiOperationSupport(order = 6)
    @PostMapping("/applyFinancing")
    public R applyFinancing(@RequestBody @ApiParam("financingApplication对象") Map financingApplicationMap) {

        FinancingApplication financingApplication = JsonUtil.toPojo(financingApplicationMap, FinancingApplication.class);

        service.addFinancingPlan(financingApplication);
        //TODO 记录操作日志用于页面展示
        return R.success("操作成功");
    }


    /**
     * 根据code查询(C端调用)
     *
     * @param financingCode 融资预案Code
     * @return
     */
    @ApiOperation(value = "根据code查询(C端调用)", notes = "传入financingCode")
    @ApiOperationSupport(order = 7)
    @GetMapping("/findByCode/{financingCode}")
    public R getFinancingPlanByCode(@PathVariable String financingCode) {
        return R.data(service.findByCode(financingCode));
    }

}
