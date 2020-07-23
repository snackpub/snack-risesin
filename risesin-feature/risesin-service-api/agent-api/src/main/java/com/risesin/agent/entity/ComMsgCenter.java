package com.risesin.agent.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * comMsgCenter实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_com_msg_center")
@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@ApiModel(value = "消息中心", description = "消息中心")
public class ComMsgCenter implements Serializable {

    /**
     * pk_id
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 消息类型
     */
    @Column(name = "msg_type")
    @ApiModelProperty(value = "消息类型")
    private Byte msgType;
    /**
     * 消息标题
     */
    @Column(name = "msg_title")
    @ApiModelProperty(value = "消息标题")
    private String msgTitle;
    /**
     * 消息内容
     */
    @Column(name = "msg_content", columnDefinition = "text ")
    @ApiModelProperty(value = "消息内容")
    private String msgContent;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @CreatedDate
    @ApiModelProperty(value = " 创建时间")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @LastModifiedDate
    @ApiModelProperty(value = "修改时间")
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    /**
     * 发布时间
     */
    @Column(name = "release_time")
    @ApiModelProperty(value = "发布时间")
    @CreatedDate
    private LocalDateTime releaseTime;

    /**
     * 状态 -1已读 0未读
     */
    @Column(name = "status")
    @ApiModelProperty(value = "状态 -1已读 0未读")
    private Byte status;

}
