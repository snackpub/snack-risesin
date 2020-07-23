package com.risesin.systemuserservice.service.system;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.core.log.annotation.ApiLog;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.utils.Func;
import com.risesin.system.service.system.SysLoanAgencyService;
import com.risesin.system.wrapper.system.LoanAgencyWrapper;
import com.risesin.systemapi.system.entity.SysDept;
import com.risesin.systemapi.system.entity.SysLoanAgency;
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
 * 助贷机构管理
 *
 * @author honey
 * @date 2019/12/12 14:09
 */
@RestController
@AllArgsConstructor
@Api(value = "助贷机构接口", tags = "助贷机构管理")
@RequestMapping("/sys/loanAgency")
public class LoanAgencyController {


    private SysLoanAgencyService service;

    /**
     * 详情
     */
    @ApiLog("详情查询")
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入loanAgencyId")
    public R detail(@RequestParam @ApiParam("主键ID") @NotBlank(message = "id不能为空") String loanAgencyId) {
        SysLoanAgency detail = service.findById(Func.toInt(loanAgencyId));
        return R.data(LoanAgencyWrapper.build().entityVO(detail));
    }

    /**
     * 新增
     */
    @ApiLog("新增")
    @PostMapping("/submit")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "新增", notes = "传入loanAgency")
    public R submit(@Valid @RequestBody @ApiParam("loanAgency对象") SysLoanAgency loanAgency) {
        service.add(loanAgency);
        return R.success(ResultCode.SUCCESS);
    }

    /**
     * 修改
     */
    @ApiLog("修改")
    @PostMapping("/update")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "修改", notes = "传入loanAgency")
    public R update(@Valid @RequestBody @ApiParam("loanAgency对象") SysLoanAgency loanAgency) {
        service.update(loanAgency);
        return R.success(ResultCode.SUCCESS);
    }

    @ApiLog("机构删除")
    @PostMapping("/remove")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(service.deleteLogic(Func.toIntList(ids), SqlConstant.UPDATE));
    }

    /**
     * 分页+多条件查询
     *
     * @param params 查询条件封装
     * @param query  查询对象
     * @return 分页结果
     */
    @ApiLog("分页+多条件查询")
    @ApiOperationSupport(order = 8)
    @PostMapping(value = "/findPageSearch")
    @ApiOperation(value = "分页查询", notes = "根据条件分页查询")
    public R<PageResult> findSearch(@ApiParam(value = "搜索集合map") @RequestBody Map<String, Object> params,
                                    @ApiParam(value = "分页码") @ApiIgnore @RequestBody Query query) {
        Page<SysLoanAgency> page = service.findSearch(params, query);
        return R.data(LoanAgencyWrapper.build().pageVO(page));
    }

    /**
     * 修改状态
     */
    @ApiLog("修改状态")
    @ApiOperationSupport(order = 8)
    @PostMapping(value = "/updateStatusById")
    @ApiOperation(value = "更新状态", notes = "传入id和状态")
    public R updateStatusById(@RequestBody SysDept sysDept) {
        service.updateStatus(sysDept.getId(), sysDept.getStatus());
        return R.data(ResultCode.SUCCESS);
    }

}
