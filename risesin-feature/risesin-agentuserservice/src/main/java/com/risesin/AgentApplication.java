package com.risesin;

import com.risesin.core.config.BaseDaoFactoryBean;
import com.risesin.core.enums.RepositoryImplPostfix;
import com.risesin.core.launch.RisesinApplication;
import com.risesin.core.launch.constant.AppConstant;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 启动服务
 *
 * @author Administrator
 */
@SpringCloudApplication
@EnableFeignClients(AppConstant.BASE_PACKAGES)
@EnableJpaRepositories(
        repositoryFactoryBeanClass = BaseDaoFactoryBean.class,
        repositoryImplementationPostfix = RepositoryImplPostfix.POSTFIX_CUSTOM
)
public class AgentApplication {

    public static void main(String[] args) {
        RisesinApplication.run(AppConstant.APPLICATION_AGEN_USER_SERVICE_NAME, AgentApplication.class, args);
    }

}
