package com.risesin.messageservice.service.kafka.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @AUTHOR Baby
 * @CREATE 2019/9/27
 * @DESCRIPTION 消息
 * @since 1.0.0
 */
@Data
public class Message {
    private Long id;    //id

    private String msg; //消息

    private LocalDateTime sendTime;  //时间戳

}
