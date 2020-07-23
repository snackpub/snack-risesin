package com.risesin.systemuserservice.service.system;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.log.annotation.ApiLog;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.utils.Func;
import com.risesin.system.service.system.SysDeptService;
import com.risesin.system.wrapper.system.DeptWrapper;
import com.risesin.systemapi.system.entity.SysDept;
import com.risesin.systemapi.system.vo.DeptVO;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * <p>组织管理</p>
 *
 * @author : honey
 * @date : 2019-10-10 15:22
 **/
@Validated
@RestController
@AllArgsConstructor
@Api(tags = "组织机构管理")
@RequestMapping("/sys/dept")
public class DeptController {


    private SysDeptService service;

    /**
     * 详情
     */
    @ApiLog("详情查询")
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入deptId")
    public R<DeptVO> detail(@RequestParam @ApiParam("主键ID") @NotBlank(message = "id不能为空") String deptId) {
        SysDept detail = service.findById(Func.toInt(deptId));
        return R.data(DeptWrapper.build().entityVO(detail));
    }

    /**
     * 新增
     */
    @ApiLog("部门新增")
    @PostMapping("/submit")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "新增", notes = "传入dept")
    public R submit(@Valid @RequestBody @ApiParam("dept对象") SysDept dept) {
        service.add(dept);
        return R.success("操作成功！");
    }


    /**
     * 修改
     */
    @ApiLog("部门修改")
    @PostMapping("/update")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "修改", notes = "传入dep")
    public R update(@Valid @RequestBody @ApiParam("dept对象") SysDept dept) {
        service.update(dept);
        return R.success("操作成功！");
    }


    @ApiLog("部门删除")
    @PostMapping("/remove")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(service.removeByIds(Func.toIntList(ids)));
    }


    /**
     * 列表
     */
    @ApiLog("部门列表")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptName", value = "部门名称", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "status", value = "状态", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "startDate", value = "开始时间", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endDate", value = "结束时间", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "createBy", value = "创建人", paramType = "query", dataType = "string"),
    })
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "列表", notes = "传入deptName")
    public R list(@ApiIgnore @RequestParam Map<String, Object> params, @RequestParam Query query) {
        Page<SysDept> search = service.findSearch(params, query.getPageNo(), query.getPageSize());
        return R.data(DeptWrapper.build().pageVO(search));
    }

    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param query     查询对象
     * @return 分页结果
     */
    @ApiLog("页+多条件查询")
    @ApiOperationSupport(order = 8)
    @PostMapping(value = "/findPageSearch")
    @ApiOperation(value = "根据条件分页查询", notes = "根据条件分页查询")
    public R<PageResult> findSearch(@ApiParam(value = "搜索集合map") @RequestBody Map<String, Object> searchMap,
                                    @ApiParam(value = "查询对象") @RequestBody Query query) {
        Page<SysDept> pageList = service.findSearch(searchMap, QueryUtil.queryIfNullForNew(query).getPageNo(), QueryUtil.queryIfNullForNew(query).getPageSize());
        return R.data(DeptWrapper.build().pageVO(pageList));
    }


    /**
     * 获取组织机构树形结构
     */
    @ApiLog("树形结构")
    @GetMapping("/tree")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "树形结构", notes = "树形结构")
    public R<List<DeptVO>> tree() {
        List<DeptVO> tree = service.tree();
        return R.data(tree);
    }


    /**
     * 修改状态
     */
    @ApiLog("修改状态")
    @ApiOperationSupport(order = 8)
    @PostMapping(value = "/updateStatusById")
    @ApiOperation(value = "更新状态", notes = "传入id和状态")
    public R<String> updateStatusById(@RequestBody SysDept sysDept) {
        service.updateStatus(Func.toStr(sysDept.getId()), sysDept.getStatus());
        return R.data("修改成功");
    }
}
