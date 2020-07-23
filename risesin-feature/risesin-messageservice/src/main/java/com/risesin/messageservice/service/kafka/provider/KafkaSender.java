package com.risesin.messageservice.service.kafka.provider;

import com.alibaba.fastjson.JSONObject;
import com.risesin.messageservice.service.kafka.constant.Topic;
import com.risesin.messageservice.service.kafka.entity.Message;
import com.risesin.messageservice.service.util.JsonUtils;
import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Properties;

/**
 * @AUTHOR Baby
 * @CREATE 2019/9/27
 * @DESCRIPTION 发送者
 * @since 1.0.0
 */
@Component
public class KafkaSender {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static Properties props;
    private static Producer<String, String> producer;

    // kafka生产者配置
    static {
        props = new Properties();
        props.put("bootstrap.servers", "192.168.1.199:9092,192.168.1.199:9093,192.168.1.199:9094");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 消息生产者
        producer = new KafkaProducer(props);
    }

    //发送消息方法
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg("这是一条消息");
        message.setSendTime(LocalDateTime.now());
        ProducerRecord<String, String> producerRecord = new ProducerRecord(Topic.TOPNAME_1, JsonUtils.convertObjectToJSON(message));

        logger.info("发送消息 ----->>>>>  message = {}", JSONObject.toJSON(message));
        producer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e != null) {
                    logger.error("~~~~~生产者发生错误~~~~", e);
                }

                // 这里可以处理发送成功后的数据逻辑 TODO
                System.out.println("The offset of the record we just sent is: " + recordMetadata.topic());
            }
        });
    }
}
