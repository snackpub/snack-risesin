package com.risesin.core.log;

import com.risesin.core.config.BaseDaoFactoryBean;
import com.risesin.core.launch.RisesinApplication;
import com.risesin.core.launch.constant.AppConstant;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * 日志服务
 *
 * @author honey
 * @date 2019/12/1
 */
@EnableJpaAuditing
@SpringCloudApplication
@EnableFeignClients(AppConstant.BASE_PACKAGES)
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseDaoFactoryBean.class, repositoryImplementationPostfix = "Custom")
public class LogApplication {

    public static void main(String[] args) {
        RisesinApplication.run(AppConstant.APPLICATION_LOG_NAME, LogApplication.class, args);
    }

}
