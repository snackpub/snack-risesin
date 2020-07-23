package com.risesin.agent.entity.feign;

import com.risesin.agent.entity.feign.vo.User;
import com.risesin.agent.entity.feign.vo.UserVO;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.RemoveIds;
import com.risesin.core.launch.constant.AppConstant;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @AUTHOR Baby
 * @CREATE 2019/11/27
 * @DESCRIPTION 方案管理
 * @since 1.0.0
 */
@FeignClient(
        contextId = "userServiceFeign",
        value = AppConstant.APPLICATION_USER_NAME,
        fallback = UserServiceFeignFallback.class
)
public interface IUserServiceFeign {
     String PREFIX= "/user-ser";

    /**
     * 查询单条
     */
    @GetMapping(PREFIX + "/detail/{id}")
    R<UserVO> detail(@PathVariable String id) ;

    /**
     * 根据条件查询用户列表
     */
    @PostMapping(value =PREFIX +  "/findPageSearch")
    R<PageResult<UserVO>> findSearch(@RequestBody(required = false) Map searchMap,@RequestParam("query") Query query);

    /**
     * 新增
     */
    @PostMapping(PREFIX + "/add")
    R<String> add(@RequestBody UserVO user);

    /**
     * 修改
     */
    @PostMapping(PREFIX + "/updateFeign")
    R<String> updateFeign( @RequestBody HashMap<String,String> map);

    /**
     * 删除
     */
    @PostMapping(PREFIX + "/remove")
    R<Boolean> remove(@RequestBody RemoveIds ids);


    /**
     * 设置角色
     */
    @PostMapping(PREFIX + "/grant")
    R<Boolean> grant( @RequestBody UserVO user);

    /**
     * 修改状态
     * @param user
     * @return
     */
    @RequestMapping(value = PREFIX + "/updateStatusById", method = RequestMethod.POST)
    R<String> updateStatusById(@RequestBody User user);

}
