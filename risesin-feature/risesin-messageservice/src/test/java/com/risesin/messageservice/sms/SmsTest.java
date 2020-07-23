package com.risesin.messageservice.sms;

import com.risesin.messageservice.MessageServiceApplicationTests;
import com.risesin.messageservice.service.listener.SmsListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-10-08
 * @DESCRIPTION 短信测试类
 * @since 1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SmsTest extends MessageServiceApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendSmsCode() {
        for (int i = 0; i < 10000; i++) {
            //试用记得给我打钱（4分5/次）
            rabbitTemplate.convertAndSend("sms", "17512345678" + i);//手机号
            System.out.println(i);
        }
    }


    @Test
    public void sendSmsCode2() {
        SmsListener listener = new SmsListener();
        Map map = new HashMap<String, String>();
        map.put("mobile", "175");
        map.put("checkCode", "175");
        listener.sendSmsCode(map);
    }
}
