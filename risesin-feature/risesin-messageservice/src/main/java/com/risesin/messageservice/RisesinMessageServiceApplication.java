package com.risesin.messageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @AUTHOR Darling
 * @CREATE 2019-09-26
 * @DESCRIPTION messageservice启动类
 * @since 1.0.0
 */

@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
public class RisesinMessageServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RisesinMessageServiceApplication.class, args);
    }
}
