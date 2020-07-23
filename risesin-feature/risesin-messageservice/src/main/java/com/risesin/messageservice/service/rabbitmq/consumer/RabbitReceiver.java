package com.risesin.messageservice.service.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @AUTHOR Baby
 * @CREATE 2019/9/30
 * @DESCRIPTION 消费者
 * @since 1.0.0
 */
@RabbitListener(queues = "helloQueue")
public class RabbitReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1  : " + hello);
    }
}
