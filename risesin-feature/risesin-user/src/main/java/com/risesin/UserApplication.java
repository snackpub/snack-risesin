package com.risesin;

import com.risesin.core.config.BaseDaoFactoryBean;
import com.risesin.core.enums.RepositoryImplPostfix;
import com.risesin.core.launch.RisesinApplication;
import com.risesin.core.launch.constant.AppConstant;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 启动类
 *
 * @author Administrator
 */

@SpringCloudApplication
@EnableFeignClients(AppConstant.BASE_PACKAGES)
@EnableJpaRepositories(
        repositoryFactoryBeanClass = BaseDaoFactoryBean.class,
        repositoryImplementationPostfix = RepositoryImplPostfix.POSTFIX_CUSTOM
)
public class UserApplication {

    public static void main(String[] args) {
        RisesinApplication.run(AppConstant.APPLICATION_USER_NAME, UserApplication.class, args);
    }

}
