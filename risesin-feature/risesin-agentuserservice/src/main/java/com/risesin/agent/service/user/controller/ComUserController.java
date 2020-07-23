package com.risesin.agent.service.user.controller;

import com.risesin.agent.entity.feign.IUserServiceFeign;
import com.risesin.agent.entity.feign.vo.User;
import com.risesin.agent.entity.feign.vo.UserVO;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.RemoveIds;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.jackson.JsonUtil;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * comUser控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/comUser")
@AllArgsConstructor
@Api(tags = "助贷端--用户管理")
public class ComUserController {

    private IUserServiceFeign iUserServiceFeign;

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public R<UserVO> findById(@PathVariable String id) {
        return iUserServiceFeign.detail(id);
    }

    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param query     查询对象
     * @return 分页结果
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "根据条件分页查询", notes = "根据条件分页查询")
    @RequestMapping(value = "/findPageSearch", method = RequestMethod.POST)
    public R<PageResult<UserVO>> findSearch(@ApiParam(value = "搜索集合map") @RequestBody Map searchMap,
                                    @ApiParam(value = "查询对象") @RequestBody Query query) {
        return iUserServiceFeign.findSearch(searchMap,query);
    }

    /**
     * 增加
     *
     * @param userVO
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "添加用户", notes = "添加用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public R<String> add(@ApiParam(value = "助贷用户") @RequestBody UserVO userVO) {
       return iUserServiceFeign.add(userVO);
    }

    /**
     * 修改
     *
     * @param userVO
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "更新用户", notes = "更新用户")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R<String> update(@ApiParam(value = "助贷用户") @RequestBody UserVO userVO) {
        HashMap map = new HashMap();
        map.put("user", JsonUtil.toJson(userVO));
        R<String> update = iUserServiceFeign.updateFeign(map);
        return update;
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除", notes = "传入ids")
    public R<Boolean> remove(@ApiParam(value = "主键集合", required = true) @RequestBody RemoveIds ids) {
        return iUserServiceFeign.remove(ids);
    }

    /**
     * 修改状态
     *
     * @param user
     * @return
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "更新状态", notes = "传入id和状态")
    @RequestMapping(value = "/updateStatusById", method = RequestMethod.POST)
    public R<String> updateStatusById(@RequestBody User user) {
        return iUserServiceFeign.updateStatusById(user);
    }

    /**
     * 设置角色
     */
    @PostMapping("/grant")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "给用户设置角色", notes = "传入userId集合以及roleIds集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", paramType = "Body", dataType = "string"),
            @ApiImplicitParam(name = "roleId", value = "角色ids集合", paramType = "Body", dataType = "string")
    })
    public R<Boolean> grant(@ApiIgnore @RequestBody UserVO user) {
        return iUserServiceFeign.grant(user);
    }
}
