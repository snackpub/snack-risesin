package com.risesin.systemuserservice.service.plan;

import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.utils.Func;
import com.risesin.system.service.plan.EnterpriseInfoService;
import com.risesin.systemapi.plan.entity.EnterpriseInfo;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * 企业信息管理
 *
 * @author : honey
 * @date : 2019-11-28 09:05
 **/
//@RestController
//@AllArgsConstructor
//@Api(tags = "企业信息管理")
//@RequestMapping("/enterpriseInfo")
public class EnterpriseInfoController {

    private EnterpriseInfoService infoService;


    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入enterpriseInfoId")
    public R<EnterpriseInfo> detail(@RequestParam @ApiParam("主键ID") @NotBlank(message = "id不能为空") String infoId) {
        EnterpriseInfo detail = infoService.findById(Func.toInt(infoId));
        return R.data(detail);
    }

    /**
     * 新增
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "新增", notes = "传入EnterpriseInfo")
    public R submit(@Valid @RequestBody @ApiParam("EnterpriseInfo对象") EnterpriseInfo entInfo) {
        infoService.add(entInfo);
        return R.success("操作成功!");
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "修改", notes = "传入ActionPlan")
    public R update(@Valid @RequestBody @ApiParam("ActionPlan对象") EnterpriseInfo entInfo) {
        infoService.update(entInfo);
        return R.success("操作成功!");
    }


    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "融资主体名称", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = " orgCode", value = "组织机构码", paramType = "query", dataType = "string")
    })
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "列表", notes = "传入name/orgCode")
    public R<List<EnterpriseInfo>> list(@ApiIgnore @RequestParam Map<String, Object> query) {
        List<EnterpriseInfo> search = infoService.findSearch(query);
        return R.data(search);
    }

    @PostMapping("/remove")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除", notes = "传入id")
    public R remove(@ApiParam(value = "主键", required = true) @RequestParam String id) {
        infoService.deleteById(Func.toInt(id));
        return R.success(ResultCode.SUCCESS);
    }
}
