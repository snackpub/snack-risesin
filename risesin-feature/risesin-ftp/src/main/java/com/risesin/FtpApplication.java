package com.risesin;

import com.risesin.core.config.BaseDaoFactoryBean;
import com.risesin.core.enums.RepositoryImplPostfix;
import com.risesin.core.launch.RisesinApplication;
import com.risesin.core.launch.constant.AppConstant;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Baby
 */
@SpringCloudApplication
@EnableConfigurationProperties
@EnableAsync
@EnableJpaRepositories(
        repositoryFactoryBeanClass = BaseDaoFactoryBean.class,
        repositoryImplementationPostfix = RepositoryImplPostfix.POSTFIX_CUSTOM
)
public class FtpApplication {

    public static void main(String[] args) {
        RisesinApplication.run(AppConstant.APPLICATION_FTP_NAME, FtpApplication.class, args);
    }

}
