package com.risesin.user.controller;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.jpaplus.support.RemoveIds;
import com.risesin.core.log.annotation.ApiLog;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.jackson.JsonUtil;
import com.risesin.core.tool.utils.Func;
import com.risesin.user.entity.User;
import com.risesin.user.service.IUserService;
import com.risesin.user.vo.UserVO;
import com.risesin.user.wrapper.UserWrapper;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统用户管理
 *
 * @author honey
 */
@RestController
@AllArgsConstructor
@Api(tags = "用户管理")
@RequestMapping("/user")
public class UserController {

    private IUserService service;

    /**
     * 查询单条
     */
    @ApiLog("查看详情")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查看详情", notes = "传入id")
    @GetMapping("/detail/{id}")
    public R<UserVO> detail(@PathVariable String id) {
        User detail = service.findById(id);
        return R.data(UserWrapper.build().entityVO(detail));
    }

    /**
     * 根据条件查询用户列表
     */
    @ApiLog("根据条件查询用户列表")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "根据条件查询用户列表", notes = "根据条件分页查询")
    @PostMapping(value = "/findPageSearch")
    public R<PageResult<UserVO>> findSearch(@ApiParam(value = "搜索集合map") @RequestBody Map searchMap) {
        Query query = new Query();
        query.setPageNo((Integer) searchMap.get("pageNo"));
        query.setPageSize((Integer) searchMap.get("pageSize"));
        Page<User> pages = service.findSearch(searchMap, query);
        return R.data(UserWrapper.build().pageVO(pages));
    }

    /**
     * 新增
     */
    @ApiLog("用户新增")
    @PostMapping("/add")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增", notes = "传入User")
    public R<String> add(@RequestBody UserVO user) {
        service.add(user);
        return R.data("添加成功");
    }

    /**
     * 修改
     */
    @ApiLog("用户修改")
    @PostMapping("/update")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改", notes = "传入User")
    public R<String> update(@RequestBody UserVO user) {
        service.update(user);
        return R.data("修改成功");
    }

    /**
     * 修改feign调用
     */
    @ApiLog("用户修改")
    @PostMapping("/updateFeign")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改(可供feign调用)", notes = "传入User")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key 为 user", value = "value为 userVO的json字符串", paramType = "Body", dataType = "string")
    })
    public R<String> updateFeign( @RequestBody HashMap<String,String> map) {
        String userStr = map.get("user");
        if(StringUtils.isEmpty(userStr)){
            return R.fail(ResultCode.PARAM_MISS);
        }
        UserVO userVO = JsonUtil.parse(userStr, UserVO.class);
        service.update(userVO);
        return R.data("修改成功");
    }

    /**
     * 删除
     */
    @ApiLog("用户删除")
    @PostMapping("/remove")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "批量删除", notes = "传入ids")
    public R<Boolean> remove(@RequestBody RemoveIds ids) {
        return R.status(service.removeByIds(Func.toStrList(ids.getIds())));
    }


    /**
     * 设置角色
     */
    @ApiLog("设置角色")
    @PostMapping("/grant")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "用户设置角色", notes = "传入userId集合以及roleIds集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", paramType = "Body", dataType = "string"),
            @ApiImplicitParam(name = "roleId", value = "角色ids集合", paramType = "Body", dataType = "string")
    })
    public R<Boolean> grant(@ApiIgnore @RequestBody UserVO user) {
        boolean temp = service.grant(user.getId(), user.getRoleId());
        return R.data(temp);
    }

    /**
     * 修改状态
     *
     * @param user
     * @return
     */
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "更新状态", notes = "传入id和状态")
    @RequestMapping(value = "/updateStatusById", method = RequestMethod.POST)
    public R<String> updateStatusById(@RequestBody User user) {
        service.updateStatus(user.getId(), user.getStatus());
        return R.success("更新状态成功!");
    }

//    @PostMapping("/reset-password")
//    @ApiOperationSupport(order = 8)
//    @ApiOperation(value = "初始化密码", notes = "传入userId集合")
//    public R resetPassword(@RequestBody RemoveIds ids) {
//        boolean temp = service.resetPassword(userIds);
//        return R.status(temp);
//    }

    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @param newPassword1
     * @return
     */
//    @PostMapping("/update-password")
//    @ApiOperationSupport(order = 9)
//    @ApiOperation(value = "修改密码", notes = "传入密码")
//    public R updatePassword(BladeUser user, @ApiParam(value = "旧密码", required = true) @RequestParam String oldPassword,
//                            @ApiParam(value = "新密码", required = true) @RequestParam String newPassword,
//                            @ApiParam(value = "新密码", required = true) @RequestParam String newPassword1) {
//        boolean temp = userService.updatePassword(user.getUserId(), oldPassword, newPassword, newPassword1);
//        return R.status(temp);
//    }

    /**
     * 用户列表
     *
     * @param user
     * @return
     */
//    @GetMapping("/user-list")
//    @ApiOperationSupport(order = 10)
//    @ApiOperation(value = "用户列表", notes = "传入user")
//    public R<List<SysUser>> userList(SysUser user) {
//        List<SysUser> list = userService.list();
//        return R.data(list);
//    }
}
