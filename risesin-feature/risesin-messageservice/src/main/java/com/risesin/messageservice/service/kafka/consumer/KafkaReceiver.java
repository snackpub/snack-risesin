package com.risesin.messageservice.service.kafka.consumer;

import com.risesin.messageservice.service.kafka.constant.Topic;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @AUTHOR Baby
 * @CREATE 2019/9/27
 * @DESCRIPTION 消费者类
 * @since 1.0.0
 */
@Component
public class KafkaReceiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // 记录消费者 位移量
    private static Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
    private static Properties props;
    private static KafkaConsumer<String, String> consumer;

    // kafka生产者配置
    static {
        props = new Properties();
        props.put("bootstrap.servers", "192.168.1.199:9092");
        props.put("group.id", "consumer-group-1");
        props.put("auto.commit.interval.ms", "1");
        props.put("auto.offset.reset", "earliest");
        //设置一次fetch请求取得的数据最大为1k
        props.put("fetch.max.bytes", "1024");
        props.put("enable.auto.commit", "false");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 消息生产者
        consumer = new KafkaConsumer<>(props);
        // 订阅主题
        consumer.subscribe(Pattern.compile("^mykafka.*$"), new ConsumerRebalanceListener() {
            // 消费者停止消费消费后，在重平衡开始前调用。
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                //在消费者负责的分区被回收前提交数据库事务，保存消费的记录和位移
                //commitDBTransaction(); 提交数据库的事物
            }

            // 在分区分配给消费者后，在消费者开始读取消息前调用。
            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                System.out.println("Lost partitions in rebalance.Committing currentoffsets:" + currentOffsets);
                consumer.commitSync(currentOffsets);
            }
        });

    }

    // 消费
    public void consumerListen() {

        try {
            while (true) {
                // 拉取队列信息
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    logger.debug("topic = %s, partition = %s, offset = %d,customer = %s, country = %s\n",
                            record.topic(), record.partition(), record.offset(),
                            record.key(), record.value());
                    // 对数据做一些操作 TODO

                    // 放入位移量
                    currentOffsets.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1, "no metadata"));

                }

                // 异步提交位移
                consumer.commitAsync(currentOffsets, null);
            }
        } catch (Exception e) {
            logger.error("Unexpected error,消费错误~~", e);
        } finally {
            try {
                // 在正常处理流程中，我们使用异步提交来提高性能，但最后使用同步提交来保证位移提交成功。
                consumer.commitSync();
            } finally {
                consumer.close();
            }
        }
    }


//    // 简单消费者
//    @KafkaListener(topics = {Topic.TOPNAME_1})
//    public void listen(ConsumerRecord<?, ?> record) {
//        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
//        if (kafkaMessage.isPresent()) {
//            Object message = kafkaMessage.get();
//            logger.info("----------------- record =" + record);
//            logger.info("------------------ message =" + message);
//        }
//
//    }
}
