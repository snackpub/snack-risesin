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
 * comUser实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_com_user")
@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@ApiModel(value = "用户", description = "用户")
public class ComUser implements Serializable {

    /**
     * 主键
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 用户账号
     */
    @Column(name = "user_account")
    @ApiModelProperty(value = "用户账号")
    private String userAccount;
    /**
     * 手机号
     */
    @Column(name = "phone")
    @ApiModelProperty(value = "手机号")
    private String phone;
    /**
     * 部门ID
     */
    @Column(name = "dept_id")
    @ApiModelProperty(value = "部门ID")
    private Integer deptId;
    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(name = "update_time")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;
    /**
     * 状态：-1 不启用 0 启用 1 黑名单 2 注销
     */
    @Column(name = "status")
    @ApiModelProperty(value = "状态：-1 不启用 0 启用 1 黑名单 2 注销")
    private Byte status;
    /**
     * 来源
     */
    @Column(name = "source")
    @ApiModelProperty(value = " 来源")
    private String source;

    /**
     * 创建人
     */
    @Column(name = "fk_created_by")
    @ApiModelProperty(value = "创建人")
    private String fkCreatedBy;

}
