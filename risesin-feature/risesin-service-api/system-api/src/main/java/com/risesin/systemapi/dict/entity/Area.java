package com.risesin.systemapi.dict.entity;

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
 * area实体类
 *
 * @author Administrator
 */
@Data
@Entity
@DynamicInsert
@Table(name = "t_area")
@EntityListeners(AuditingEntityListener.class)
public class Area implements Serializable {

    /**
     * 主键
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 地区编号
     */
    @Column(name = "code")
    private String code;

    /**
     * 地区名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 父节点
     */
    @Column(name = "parent_code")
    private String parentCode;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /**
     * 状态： -1 不启用 0 启用 1 加入黑名单 2注销（删除）
     */
    @Column(name = "status")
    private Byte status;

    @ApiModelProperty("创建人id")
    @Column(name = "fk_sys_user_id")
    private String sysUserId;

    @ApiModelProperty("创建人姓名")
    @Column(name = "create_user")
    private String createUser;

}
