package com.risesin.messageservice.service.listener;

import com.aliyuncs.CommonResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.risesin.messageservice.core.SmsUtil;
import com.risesin.messageservice.service.properties.SmsProperties;
import com.risesin.messageservice.service.util.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-10-08
 * @DESCRIPTION 短息监听类
 * @since 1.0.0
 */

@Component
@RabbitListener(queues = "${risesin-properties.sms.queueName}")
public class SmsListener {

    private static final Logger log = LoggerFactory.getLogger(SmsListener.class);

    @Autowired
    private SmsProperties smsProperties;

    /**
     * 发送验证码
     *
     * @param map
     */
    @RabbitHandler
    public void sendSmsCode(Map<String, String> map) {
        String mobile = map.get("mobile");
        String checkCode = map.get("checkCode");
        if (StringUtils.isBlank(mobile) || mobile.length() != 11 || !mobile.startsWith("1")) {
            //号码不符合规范
            return;
        }
        try {
            CommonResponse response = SmsUtil.sendSms(smsProperties, mobile, checkCode);
            String resultData = response.getData();
            SendSmsResponse sendSmsResponse = JsonUtils.convertJsonToBean(resultData, SendSmsResponse.class);
            if (!(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK"))) {
                //返回码不为ok，发送失败
                log.error(resultData);
            }
        } catch (ClientException e) {
            log.error("阿里云服务端错误", e);
        }
    }


}
