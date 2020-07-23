package com.risesin.user.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 用户拓展表
 *
 * @author Baby
 * @date 2019/12/19
 * @since 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Entity
@ApiModel("用户对象拓展表")
@Table(name = "risesin_user_ext")
@EntityListeners(AuditingEntityListener.class)
public class UserExt {

    /**
     * 主键ID：雪花分布式id
     */
    @Id
    @Column(name = "pk_id")
    @ApiModelProperty("用户id")
    private String id;

    /**
     * 用户来源
     */
    @Column(name = "source")
    @ApiModelProperty("用户来源")
    private String source;

    /**
     * 工作单位
     */
    @Column(name = "company_name")
    @ApiModelProperty("工作单位")
    private String companyName;

    /**
     * 地区
     */
    @Column(name = "area")
    @ApiModelProperty("地区code")
    private String area;

    /**
     * 街道地址
     */
    @Column(name = "address")
    @ApiModelProperty("街道地址")
    private String address;

    /**
     * 传真
     */
    @Column(name = "fax")
    @ApiModelProperty("传真")
    private String fax;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    @ApiModelProperty("创建人")
    private Integer createUser;
}
