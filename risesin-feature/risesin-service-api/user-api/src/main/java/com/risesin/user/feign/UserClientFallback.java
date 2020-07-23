package com.risesin.user.feign;

import com.risesin.core.tool.api.R;
import com.risesin.user.entity.UserInfo;
import org.springframework.stereotype.Component;

/**
 * feign 调用失败配置
 *
 * @author honey
 * @date 2019/12/5 11:41
 **/
@Component
public class UserClientFallback implements IUserClient {

    @Override
    public R<String> getUserName(String id) {
        return R.fail("获取数据失败");
    }

    @Override
    public R<UserInfo> userInfoById(String userId) {
        return R.fail("获取数据失败");
    }

    @Override
    public R<UserInfo> userInfo(String phone) {
        return R.fail("获取数据失败");
    }

    @Override
    public R<UserInfo> userInfo(String account, String phone) {
        return R.fail("获取数据失败");
    }
}
