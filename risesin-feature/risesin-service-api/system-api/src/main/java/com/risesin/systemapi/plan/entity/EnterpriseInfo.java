package com.risesin.systemapi.plan.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 企业信息对象
 *
 * @author Administrator
 */
@Data
@Entity
@Table(name = "t_enterprise_info")
@ApiModel("企业信息对象")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class EnterpriseInfo implements Serializable {

    @Id
    @Column(name = "pk_id")
    @ApiModelProperty("主键")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @ApiModelProperty("融资主体名称")
    private String name;

    @Column(name = "reg_address")
    @ApiModelProperty("公司注册地: 1 中国大陆 2 中国香港 3 中国台湾 4 境外")
    private String regAddress;

    @Column(name = "org_code")
    @ApiModelProperty("组织机构码")
    private String orgCode;

    @Column(name = "reg_time")
    @ApiModelProperty("注册时间")
    private String regTime;

    @Column(name = "reg_capital")
    @ApiModelProperty("注册资本")
    private BigDecimal regCapital;

    @ApiModelProperty("行业Code")
    @Column(name = "industry_code")
    private String industry;

    @Column(name = "area_code")
    @ApiModelProperty("区域Code")
    private String area;

    @Column(name = "legal_repr_change")
    @ApiModelProperty("法人代表变更:")
    private Short legalReprChange;

    @CreatedDate
    @Column(name = "create_time")
    @ApiModelProperty("人创建时间")
    private LocalDateTime createTime;

    @LastModifiedDate
    @ApiModelProperty("修改时间")
    @Column(name = "update_time")
    private LocalDateTime updateTime;

}
