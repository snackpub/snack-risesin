package com.risesin;

import com.risesin.core.launch.RisesinApplication;
import com.risesin.core.launch.constant.AppConstant;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动服务
 *
 * @author honey
 */
@SpringCloudApplication
@EnableFeignClients(AppConstant.BASE_PACKAGES)
public class AuthApplication {

    public static void main(String[] args) {
        RisesinApplication.run(AppConstant.APPLICATION_AUTH_NAME, AuthApplication.class, args);
    }

}
