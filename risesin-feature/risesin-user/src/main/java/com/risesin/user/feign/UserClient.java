package com.risesin.user.feign;


import com.risesin.core.tool.api.R;
import com.risesin.user.entity.UserInfo;
import com.risesin.user.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 用户服务Feign实现类
 *
 * @author Administrator
 * @date 2019/12/5
 */
@ApiIgnore
@RestController
@AllArgsConstructor
public class UserClient implements IUserClient {

    IUserService userService;

    @Override
    @GetMapping(API_PREFIX + "/getUserName")
    public R<String> getUserName(String id) {
        return R.data(userService.getUserName(id));
    }

    @Override
    public R<UserInfo> userInfoById(String userId) {
        return R.data(userService.userInfo(userId));
    }

    @Override
    @GetMapping(API_PREFIX + "/user-info-by-phone")
    public R<UserInfo> userInfo(String phone) {
        return R.data(userService.userInfo(phone));
    }

    @Override
    @GetMapping(API_PREFIX + "/user-info")
    public R<UserInfo> userInfo(@RequestParam("account") String account, @RequestParam("phone") String phone) {
        return R.data(userService.userInfo(account, phone));
    }
}
