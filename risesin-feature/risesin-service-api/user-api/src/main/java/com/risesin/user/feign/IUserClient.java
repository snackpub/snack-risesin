package com.risesin.user.feign;

import com.risesin.core.launch.constant.AppConstant;
import com.risesin.core.tool.api.R;
import com.risesin.user.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户feign接口
 *
 * @author honey
 * @date 2019/12/5
 */

@FeignClient(
        value = AppConstant.APPLICATION_USER_NAME,
        fallback = UserClientFallback.class
)
public interface IUserClient {

    String API_PREFIX = "/user";


    /**
     * 根据用户id查找用户名
     *
     * @param id 用户id
     * @return R
     */
    @GetMapping(API_PREFIX + "/getUserName")
    R<String> getUserName(@RequestParam("id") String id);

    /**
     * 获取用户信息
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping(API_PREFIX + "/user-info-by-id")
    R<UserInfo> userInfoById(@RequestParam("userId") String userId);

    /**
     * 获取用户信息
     *
     * @param phone 用户手机号
     * @return ${R}
     */
    @GetMapping(API_PREFIX + "/user-info-by-phone")
    R<UserInfo> userInfo(@RequestParam("phone") String phone);

    /**
     * 获取用户信息
     *
     * @param account 账号
     * @param phone   手机号
     * @return R
     */
    @GetMapping(API_PREFIX + "/user-info")
    R<UserInfo> userInfo(@RequestParam("account") String account, @RequestParam("phone") String phone);


}
