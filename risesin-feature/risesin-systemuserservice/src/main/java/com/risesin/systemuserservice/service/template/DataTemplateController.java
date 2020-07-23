package com.risesin.systemuserservice.service.template;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.core.log.annotation.ApiLog;
import com.risesin.core.secure.annotation.PreAuth;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.constant.RoleConstant;
import com.risesin.core.tool.utils.Func;
import com.risesin.system.service.template.DataTemplateService;
import com.risesin.system.wrapper.dict.DataTemplateWrapper;
import com.risesin.systemapi.dict.entity.DataTemplate;
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
 * 资料模板管理
 *
 * @author honey
 * @date 2019/12/13 15:58
 */
@RestController
@AllArgsConstructor
@Api(value = "资料模板管理接口", tags = "资料模板管理")
@RequestMapping("/sys/dataTemplate")
public class DataTemplateController {


    private DataTemplateService service;

    /**
     * 详情
     */
    @ApiLog("详情查询")
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入dataTemplateId")
    public R detail(@RequestParam @ApiParam("主键ID") @NotBlank(message = "id不能为空") String dataTemplateId) {
        DataTemplate detail = service.findById(Func.toInt(dataTemplateId));
        return R.data(DataTemplateWrapper.build().entityVO(detail));
    }

    /**
     * 新增
     */
    @ApiLog("新增")
    @PostMapping("/submit")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "新增", notes = "传入dataTemplate")
    public R submit(@Valid @RequestBody @ApiParam("dataTemplate对象") DataTemplate dataTemplate) {
        service.add(dataTemplate);
        return R.success(ResultCode.SUCCESS);
    }

    /**
     * 修改
     */
    @ApiLog("修改")
    @PostMapping("/update")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "修改", notes = "传入dataTemplate")
    @PreAuth(RoleConstant.HAS_ROLE_ADMIN)
    public R update(@Valid @RequestBody @ApiParam("dataTemplate对象") DataTemplate dataTemplate) {
        service.update(dataTemplate);
        return R.success(ResultCode.SUCCESS);
    }

    @ApiLog("机构删除")
    @PostMapping("/remove")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除", notes = "传入ids")
    @PreAuth(RoleConstant.HAS_ROLE_ADMIN)
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(service.deleteLogic(Func.toIntList(ids), SqlConstant.DELETE));
    }

    /**
     * 分页+多条件查询
     *
     * @param params 查询条件封装
     * @param query  查询对象
     * @return 分页结果
     */
    @ApiLog("分页+ 多条件查询")
    @ApiOperationSupport(order = 8)
    @PostMapping(value = "/findPageSearch")
    @ApiOperation(value = "分页查询", notes = "根据条件分页查询")
    public R<PageResult> findSearch(@ApiParam(value = "参数集合") @RequestBody Map<String, Object> params,
                                    @ApiParam(value = "分页码") @ApiIgnore @RequestBody Query query) {
        Page<DataTemplate> page = service.findSearch(params, query);
        return R.data(DataTemplateWrapper.build().pageVO(page));
    }

    /**
     * 修改状态
     */
    @ApiLog("修改状态")
    @ApiOperationSupport(order = 8)
    @PostMapping(value = "/updateStatusById")
    @ApiOperation(value = "更新状态", notes = "传入id和状态")
    public R updateStatusById(@RequestBody DataTemplate dataTemplate) {
        service.updateStatus(dataTemplate.getId(), dataTemplate.getStatus());
        return R.success(ResultCode.SUCCESS);
    }


}
