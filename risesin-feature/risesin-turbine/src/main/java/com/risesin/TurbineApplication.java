package com.risesin;

import com.risesin.core.launch.RisesinApplication;
import com.risesin.core.launch.constant.AppConstant;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine
@EnableHystrixDashboard
@SpringCloudApplication
public class TurbineApplication {

    public static void main(String[] args) {
        RisesinApplication.run(AppConstant.APPLICATION_TURIBINE_NAME, TurbineApplication.class, args);
    }
}
