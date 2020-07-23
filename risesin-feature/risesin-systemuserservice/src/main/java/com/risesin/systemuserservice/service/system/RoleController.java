package com.risesin.systemuserservice.service.system;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.utils.CollectionUtil;
import com.risesin.core.tool.utils.Func;
import com.risesin.system.service.system.ISysRoleService;
import com.risesin.system.wrapper.system.RoleWrapper;
import com.risesin.systemapi.system.entity.SysRole;
import com.risesin.systemapi.system.vo.RoleVO;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author : honey
 * @date : 2019-10-25
 **/
@RestController
@RequestMapping("/role")
@Api(value = "角色管理", tags = "角色管理")
@AllArgsConstructor
public class RoleController {


    private ISysRoleService service;

    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入roleId")
    public R<RoleVO> detail(@RequestParam @ApiParam("主键ID") String id) {
        SysRole detail = service.findById(Func.toInt(id));
        return R.data(RoleWrapper.build().entityVO(detail));
    }

    /**
     * 新增
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "新增", notes = "传入role")
    public R submit(@RequestBody @ApiParam("role对象") SysRole role) {
        service.add(role);
        return R.success(ResultCode.SUCCESS);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "修改", notes = "传入role")
    public R update(@Valid @RequestBody @ApiParam("role对象") SysRole role) {
        service.update(role, role.getId());
        return R.success(ResultCode.SUCCESS);
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(service.deleteLogic(Func.toIntList(ids)));
    }


    /**
     * 分页查询
     *
     * @param searchMap 查询条件封装
     * @param query     分页对象
     * @return 分页结果
     */
    @PostMapping(value = "/findPageSearch")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "根据条件分页查询", notes = "根据条件分页查询")
    public R<PageResult> findSearch(@RequestBody Map<String, Object> searchMap,
                                    @RequestBody Query query) {
        Page<SysRole> pageList = service.findSearch(searchMap, query);
        return R.data(RoleWrapper.build().pageVO(pageList));
    }

    /**
     * 获取角色树形结构
     */
    @GetMapping("/tree")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "树形结构", notes = "树形结构")
    public R<List<RoleVO>> tree() {
        List<RoleVO> tree = service.tree();
        return R.data(tree);
    }


    /**
     * 设置角色拥有的菜单
     */
    @PostMapping("/grant")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "角色菜单设置", notes = "传入roleId集合以及menuId集合")
    public R grant(@ApiIgnore @RequestBody Map<String, Object> params) {
        boolean bool = service.grant(Func.toIntList((String) params.get("roleIds")), Func.toIntList((String) params.get("menuIds")));
        return R.status(bool);
    }

    /**
     * 设置角色拥有的权限
     */
    @PostMapping("/authority")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "角色权限设置", notes = "传入roleId集合以及permissionId集合")
    public R authority(@ApiIgnore @RequestBody Map<String, Object> params) {
        if (CollectionUtil.isEmpty(params)) {
            return R.fail("roleIds参数，perIds参数不能为空");
        }
        boolean bool = service.authority(Func.toIntList((String) params.get("roleIds")), Func.toIntList((String) params.get("perIds")));
        return R.status(bool);
    }

    /**
     * 修改状态
     */
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "更新状态", notes = "传入id和状态")
    @PostMapping(value = "/updateStatusById")
    public R<String> updateStatusById(@RequestBody SysRole role) {
        service.updateStatus(Func.toInt(role.getId()), role.getStatus());
        return R.data("修改成功");
    }

}
