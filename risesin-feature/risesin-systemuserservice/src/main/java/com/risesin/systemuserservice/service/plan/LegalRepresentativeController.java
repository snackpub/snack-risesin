package com.risesin.systemuserservice.service.plan;

import com.risesin.core.boot.ctrl.RisesinController;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.utils.Func;
import com.risesin.system.service.plan.LegalRepresentativeService;
import com.risesin.systemapi.plan.entity.LegalRepresentative;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * 法人代表信息管理
 *
 * @author : honey
 * @date : 2019-11-28 10:29
 **/
//@RestController
//@AllArgsConstructor
////@Api(tags = "法人代表信息管理")
//@RequestMapping("/legalRepr")
public class LegalRepresentativeController extends RisesinController {

    private LegalRepresentativeService representativeService;

    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入ID")
    public R<LegalRepresentative> detail(@RequestParam @ApiParam("主键ID") @NotBlank(message = "id不能为空") String id) {
        LegalRepresentative detail = representativeService.findById(Func.toInt(id));
        return R.data(detail);
    }

    /**
     * 新增
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "新增", notes = "传入LegalRepresentative")
    public R submit(@Valid @RequestBody @ApiParam("LegalRepresentative对象") LegalRepresentative lr) {
        representativeService.add(lr);
        return R.success("操作成功!");
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "修改", notes = "传入LegalRepresentative")
    public R update(@Valid @RequestBody @ApiParam("LegalRepresentative对象") LegalRepresentative lr) {
        representativeService.update(lr);
        return R.success("操作成功!");
    }


    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "法人代表姓名", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = " idNumber", value = "法人代表身份证", paramType = "query", dataType = "string")
    })
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "列表", notes = "传入name/idNumber")
    public R<List<LegalRepresentative>> list(@ApiIgnore @RequestParam Map<String, Object> query) {
        List<LegalRepresentative> search = representativeService.findSearch(query);
        return R.data(search);
    }

    @PostMapping("/remove")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除", notes = "传入id")
    public R remove(@ApiParam(value = "主键", required = true) @RequestParam String id) {
        representativeService.deleteById(Func.toInt(id));
        return R.success(ResultCode.SUCCESS);
    }


}
