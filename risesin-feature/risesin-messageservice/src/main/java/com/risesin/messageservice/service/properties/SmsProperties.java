package com.risesin.messageservice.service.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR Darling
 * @CREATE 2019-09-27
 * @DESCRIPTION 阿里云短信配置类
 * @since 1.0.0
 */
@ConfigurationProperties("risesin-properties.aliyun.sms")
@Component
@Data
public class SmsProperties {
    private String accessKeyId; //主账号AccessKey的ID
    private String accessKeySecret; //阿里短信服务秘钥
    private String signName; //短信签名名称
    private String templateCode; //短信模板ID
}
