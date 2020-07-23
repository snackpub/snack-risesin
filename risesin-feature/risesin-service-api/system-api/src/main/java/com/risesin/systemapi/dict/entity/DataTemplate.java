package com.risesin.systemapi.dict.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * dataTemplate实体类
 *
 * @author Administrator
 */
@Data
@Entity
@Table(name = "t_data_template")
@ApiModel("申请资料模板")
@EntityListeners(AuditingEntityListener.class)
public class DataTemplate implements Serializable {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id")
    private Integer id;

    /**
     * 表字段
     */
    @Column(name = "field")
    @ApiModelProperty("表字段")
    private String field;
    /**
     * 类型 1 企业 2 个人
     */
    @Column(name = "type")
    @ApiModelProperty("类型 1 企业 2 个人")
    private String type;

    /**
     * 字段类型
     */
    @ApiModelProperty("字段类型")
    @Column(name = "field_type")
    private String fieldType;

    /**
     * 字段前缀
     */
    @Column(name = "field_prefix")
    @ApiModelProperty("字段前缀")
    private String fieldPrefix;

    /**
     * 字段后缀
     */
    @Column(name = "field_suffix")
    @ApiModelProperty("字段后缀")
    private String fieldSuffix;

    /**
     * 状态 -1：不启用 0：启用 2: 删除
     */
    @Column(name = "status")
    @ApiModelProperty("状态 -1：不启用 0：启用 2: 删除")
    private Byte status;
    /**
     * 字段描述
     */
    @Column(name = "description")
    @ApiModelProperty("字段描述")
    private String description;
    /**
     * 字段单位
     */
    @Column(name = "field_unit")
    @ApiModelProperty("字段单位")
    private String fieldUnit;
    /**
     * 选项
     */
    @Column(name = "options")
    @ApiModelProperty("选项")
    private String options;

    @Column(name = "has_value")
    @ApiModelProperty("是否需要填值")
    private Boolean hasValue;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty("创建时间")
    @CreatedDate
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @ApiModelProperty("创建时间")
    @LastModifiedDate
    private LocalDateTime updateTime;

}
