package com.risesin.messageservice.service.rabbitmq.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @AUTHOR Baby
 * @CREATE 2019/9/30
 * @DESCRIPTION 测试消息
 * @since 1.0.0
 */
@Accessors(chain = true) // 可将对象转换成链式设置值(流的形式)
@Data
@ToString
public class TestUser implements Serializable {
    private String name;
    private Integer age;
    private LocalDateTime localDateTime;

}
