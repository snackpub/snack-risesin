package com.risesin.systemuserservice.service.system;

import com.risesin.core.boot.ctrl.RisesinController;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.log.annotation.ApiLog;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.utils.Func;
import com.risesin.system.service.system.SysMenuService;
import com.risesin.system.wrapper.system.MenuWrapper;
import com.risesin.systemapi.system.entity.SysMenu;
import com.risesin.systemapi.system.vo.MenuVO;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 控制器
 *
 * @author honey
 */
@RestController
@AllArgsConstructor
@RequestMapping("/menu")
@Api(value = "菜单管理", tags = "菜单管理")
public class MenuController extends RisesinController {

    private SysMenuService menuService;

    /**
     * 详情
     */
    @ApiLog("详情")
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入ID")
    public R<MenuVO> detail(@ApiParam(value = "主键ID", required = true) @RequestParam String id) {
        SysMenu detail = menuService.findById(Func.toInt(id));
        return R.data(MenuWrapper.build().entityVO(detail));
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "state", value = "菜单状态", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "name", value = "菜单名称", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "createBy", value = "创建人", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "startDate", value = "开始时间", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endDate", value = "结束时间", paramType = "query", dataType = "string")
    })
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "列表", notes = "传入menu")
    @ApiLog("列表查询")
    public R<List<MenuVO>> list(@ApiIgnore @RequestParam Map<String, Object> menu) {
        List<SysMenu> list = menuService.findSearch(menu);
        return R.data(MenuWrapper.build().listNodeVO(list));
    }

    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param query     查询对象
     * @return 分页结果
     */
    @ApiLog("条件分页查询")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping(value = "/findPageSearch")
    public R<PageResult> findSearch(@RequestBody Map<String, Object> searchMap,
                                    @RequestBody Query query) {
        Page<SysMenu> pageList = menuService.findSearch(searchMap, QueryUtil.queryIfNullForNew(query).getPageNo(), QueryUtil.queryIfNullForNew(query).getPageSize());
        return R.data(MenuWrapper.build().pageVO(pageList));
    }


    /**
     * 新增或修改
     */
    @ApiLog("新增或修改")
    @PostMapping("/submit")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "新增或修改", notes = "传入menu")
    public R submit(@Valid @RequestBody SysMenu menu) {
        menuService.add(menu);
        return R.success(ResultCode.SUCCESS);
    }


    /**
     * 修改状态
     */
    @ApiLog("修改状态")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "更新状态", notes = "传入id和状态")
    @PostMapping(value = "/updateStatusById")
    public R<String> updateStatusById(@RequestBody SysMenu menu) {
        menuService.updateStatus(Func.toStr(menu.getId()), menu.getStatus());
        return R.data("修改成功");
    }

    /**
     * 删除
     */
    @ApiLog("删除")
    @PostMapping("/remove")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(menuService.removeByIds(Func.toIntList(ids)));
    }

    /**
     * 前端菜单数据
     */
    @ApiLog("前端菜单数据")
    @PostMapping("/routes")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "前端菜单数据", notes = "前端菜单数据")
    public R<List<MenuVO>> routes(@ApiParam("角色ID") @RequestParam String roleId) {
        List<MenuVO> list = menuService.routes(roleId);
        return R.data(list);
    }

    /**
     * 前端按钮数据
     */
//    @PostMapping("/buttons")
//    @ApiOperationSupport(order = 6)
//    @ApiOperation(value = "前端按钮数据", notes = "前端按钮数据")
//    public R<List<MenuVO>> buttons(@RequestBody RisesinUser user) {
//        List<MenuVO> list = menuService.buttons(user.getRoleId());
//        return R.data(list);
//    }

    /**
     * 获取菜单树形结构
     */
    @ApiLog("树形结构")
    @GetMapping("/tree")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "树形结构", notes = "树形结构")
    public R<List<MenuVO>> tree() {
        List<MenuVO> tree = menuService.tree();
        return R.data(tree);
    }

    /**
     * 获取菜单分配树形结构
     */
    @ApiLog("菜单分配树形结构")
    @GetMapping("/grant-tree")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "菜单分配树形结构", notes = "菜单分配树形结构")
    public R<List<MenuVO>> grantTree() {
        return R.data(menuService.grantTree());
    }


    /**
     * 获取菜单分配树形结构
     */
//    @GetMapping("/role-tree-keys")
//    @ApiOperationSupport(order = 9)
//    @ApiOperation(value = "角色所分配的树", notes = "角色所分配的树")
//    public R<List<String>> roleTreeKeys(String roleIds) {
//        return R.data(menuService.roleTreeKeys(roleIds));
//    }

    /**
     * 获取配置的角色菜单
     */
//    @GetMapping("auth-routes")
//    @ApiOperationSupport(order = 10)
//    @ApiOperation(value = "菜单的角色权限")
//    public R<List<Kv>> authRoutes(RisesinUser user) {
//        return R.data(menuService.authRoutes(user));
//    }

}
