package com.risesin.systemapi.serrating.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * t_service_rating实体类
 *
 * @author honey
 */
@Data
@Entity
@DynamicInsert
@Table(name = "t_service_rating")
@EntityListeners(AuditingEntityListener.class)
public class ServiceRating implements Serializable {
    /**
     * 主键ID：自动增长
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID：自动增长")
    private Integer id;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    /**
     * 修改时间
     */
    @Column(name = "update_time")
    @ApiModelProperty(value = "修改时间")
    private String updateTime;
    /**
     * 评价人
     */
    @Column(name = "custom_id")
    @ApiModelProperty(value = "评价人")
    private String customId;
    /**
     * 服务评分(1-5分)
     */
    @Column(name = "score")
    @ApiModelProperty(value = "服务评分(1-5分)")
    private String score;
    /**
     * 服务提供者
     */
    @Column(name = "ser_provider_id")
    @ApiModelProperty(value = "服务提供者")
    private String serProviderId;
    /**
     * 评价类型
     */
    @Column(name = "type")
    @ApiModelProperty(value = "评价类型")
    private Integer type;
    /**
     * 融资预案id
     */
    @Column(name = "fk_fina_plan_code")
    @ApiModelProperty(value = "融资预案id")
    private String finaPlanCode;
    /**
     * 评价描述
     */
    @Column(name = "description")
    @ApiModelProperty(value = "评价描述")
    private String description;
    /**
     * 状态
     */
    @Column(name = "status")
    @ApiModelProperty(value = "状态")
    private Byte status;
}
