package com.risesin.systemapi.flowlog.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * user_log实体类
 *
 * @author honey
 * @date 2019-12-22
 */
@Data
@Entity
@DynamicInsert
@Table(name = "user_log")
@EntityListeners(AuditingEntityListener.class)
public class UserLog implements Serializable {

    @Id
    @Column(name = "pk_id")
    @ApiModelProperty(value = "主键ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 操作内容
     */
    @Column(name = "content")
    @ApiModelProperty(value = "操作内容")
    private String content;
    /**
     * 创建用户
     */
    @Column(name = "create_user")
    @ApiModelProperty(value = "创建用户")
    private String createUser;
    /**
     * 操作用户
     */
    @Column(name = "oper_user")
    @ApiModelProperty(value = "操作用户")
    private String operationUser;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @Column(name = "status")
    @ApiModelProperty(value = "状态： -1 操作失败 0 操作成功")
    private Byte status;
}
