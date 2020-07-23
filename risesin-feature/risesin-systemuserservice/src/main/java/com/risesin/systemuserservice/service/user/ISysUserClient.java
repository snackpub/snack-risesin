package com.risesin.systemuserservice.service.user;

import com.risesin.core.jpaplus.support.RemoveIds;
import com.risesin.core.launch.constant.AppConstant;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.user.entity.User;
import com.risesin.user.vo.UserVO;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 咨询服务端 User Feign接口类
 *
 * @author honey
 * @date 2019/12/20 10:08
 */
@FeignClient(
        value = AppConstant.APPLICATION_USER_NAME
//        fallback = ISysUserClientFallback.class
)
public interface ISysUserClient {

    String API_PREFIX = "/user";

    /**
     * 获取用户详情
     *
     * @param id 编号
     * @return {R}
     */
    @GetMapping(API_PREFIX + "/detail/{id}")
    R<UserVO> detail(@PathVariable String id);

    /**
     * 根据条件查询用户列表
     *
     * @param searchMap params
     * @return ${R}
     */
    @PostMapping(API_PREFIX + "/findPageSearch")
    R<PageResult<UserVO>> findSearch(@ApiParam(value = "params") @RequestBody Map searchMap);

    /**
     * 新增
     *
     * @param user userVo
     * @return ${R}
     */
    @PostMapping(API_PREFIX + "/add")
    R<String> add(@RequestBody UserVO user);

    @PostMapping(API_PREFIX + "/update")
    R<String> update(@RequestBody UserVO user);

    @PostMapping(API_PREFIX + "/remove")
    R<Boolean> remove(@RequestBody RemoveIds ids);

    @PostMapping(API_PREFIX + "/grant")
    R<String> grant(@RequestBody User user);

    @PostMapping(API_PREFIX + "/updateStatusById")
    R<String> updateStatusById(@RequestBody User user);
}
