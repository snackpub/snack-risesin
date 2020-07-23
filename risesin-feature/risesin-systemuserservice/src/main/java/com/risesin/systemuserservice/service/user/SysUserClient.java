package com.risesin.systemuserservice.service.user;

import com.risesin.core.jpaplus.support.RemoveIds;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.user.entity.User;
import com.risesin.user.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * System User Feign Client
 *
 * @author honey
 * @date 2019/12/20 10:38
 */
@Api(tags = "系统用户管理")
@RestController
@AllArgsConstructor
public class SysUserClient implements ISysUserClient {

    private ISysUserClient userClient;

    @Override
    @ApiOperationSupport(order = 1)
    @GetMapping(API_PREFIX + "/detail/{id}")
    @ApiOperation(value = "查看详情", notes = "传入id")
    public R<UserVO> detail(String id) {
        return userClient.detail(id);
    }

    @Override
    @ApiOperationSupport(order = 2)
    @PostMapping(API_PREFIX + "/findPageSearch")
    @ApiOperation(value = "分页查询", notes = "根据条件分页查询")
    public R<PageResult<UserVO>> findSearch(Map searchMap) {
        return userClient.findSearch(searchMap);
    }

    @Override
    @ApiOperationSupport(order = 3)
    @PostMapping(API_PREFIX + "/add")
    @ApiOperation(value = "新增", notes = "传入User")
    public R<String> add(UserVO user) {
        userClient.add(user);
        return R.success(ResultCode.SUCCESS);
    }

    @Override
    @ApiOperationSupport(order = 4)
    @PostMapping(API_PREFIX + "/update")
    @ApiOperation(value = "修改", notes = "传入User")
    public R<String> update(UserVO user) {
        userClient.update(user);
        return R.success(ResultCode.SUCCESS);
    }

    @Override
    @ApiOperationSupport(order = 5)
    @PostMapping(API_PREFIX + "/remove")
    @ApiOperation(value = "批量删除", notes = "传入ids")
    public R<Boolean> remove(RemoveIds ids) {
        return userClient.remove(ids);
    }

    @Override
    @ApiOperationSupport(order = 6)
    @PostMapping(API_PREFIX + "/grant")
    @ApiOperation(value = "用户设置角色", notes = "传入userId集合以及roleIds集合")
    public R<String> grant(User user) {
        return userClient.grant(user);
    }

    @Override
    @ApiOperationSupport(order = 7)
    @PostMapping(API_PREFIX + "/updateStatusById")
    @ApiOperation(value = "更新状态", notes = "传入id和状态")
    public R<String> updateStatusById(User user) {
        return userClient.updateStatusById(user);
    }
}
