package com.risesin.messageservice.service.rabbitmq.productor;

import com.risesin.messageservice.service.rabbitmq.entity.TestUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR Baby
 * @CREATE 2019/9/30
 * @DESCRIPTION 消息生产者
 * @since 1.0.0
 */
@Component
public class RabbitSender {
    private static final Logger log = LoggerFactory.getLogger(RabbitSender.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 用于单生产者-》单消费者测试
     */
    public void send(TestUser testUser) {
        log.info("生产消息", testUser);
        rabbitTemplate.convertAndSend("helloQueue", testUser);
    }

    public void send() {
        log.info("生产消息");
        rabbitTemplate.convertAndSend("helloQueue", "testUser~~~~~~~~~~~~ ");
    }
}
