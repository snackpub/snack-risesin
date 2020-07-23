package com.risesin.messageservice.kafka;

import com.risesin.messageservice.RisesinMessageServiceApplication;
import com.risesin.messageservice.service.kafka.consumer.KafkaReceiver;
import com.risesin.messageservice.service.kafka.provider.KafkaSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


/**
 * @AUTHOR Baby
 * @CREATE 2019/9/27
 * @DESCRIPTION kafka测试
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RisesinMessageServiceApplication.class)
//测试web项目所加注解
@WebAppConfiguration
public class kafkaTest {

    @Autowired
    private KafkaSender kafkaSender;

    @Autowired
    private KafkaReceiver kafkaReceiver;

    @Test
    public void producer() {
        kafkaSender.send();
    }

    @Test
    public void consumer() {
        kafkaReceiver.consumerListen();
    }
}
