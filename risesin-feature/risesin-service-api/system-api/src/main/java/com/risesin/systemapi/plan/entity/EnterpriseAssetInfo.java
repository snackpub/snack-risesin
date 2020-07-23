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
 * 企业资产信息实体类
 *
 * @author Administrator
 */
@Data
@Entity
@ApiModel("企业资产信息对象")
@Table(name = "t_enterprise_asset_info")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class EnterpriseAssetInfo implements Serializable {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total_assets")
    @ApiModelProperty("资产总值")
    private BigDecimal totalAssets;

    @ApiModelProperty("非经营性资产总值")
    @Column(name = "nonoperating_assets")
    private BigDecimal nonOperatingAssets;

    @ApiModelProperty("申请区域房产")
    @Column(name = "real_estate_number")
    private Short realEstateNumber;

    @ApiModelProperty("申请区域房产估值")
    @Column(name = "real_estate_valuation")
    private BigDecimal realEstateValuation;

    @ApiModelProperty("质押物估值")
    @Column(name = "pledge_valuation")
    private BigDecimal pledgeValuation;

    @ApiModelProperty("应收款总额")
    @Column(name = "total_receivables")
    private BigDecimal totalReceivables;

    @ApiModelProperty("是否有特别经营许可:否(0);是(1)")
    @Column(name = "has_business_license")
    private Boolean hasBusinessLicense;

    @ApiModelProperty("特别经营许可")
    @Column(name = "business_license")
    private String businessLicense;

    @ApiModelProperty("是否存在以下项目用地:1 工业用地 2 物流仓储用地 3 科教用地(多选框)")
    @Column(name = "project_site")
    private String projectSite;

    @Column(name = "has_case")
    @ApiModelProperty("是否有刑事或民事纠纷:否(0);是(1)")
    private Boolean hasCase;

    @CreatedDate
    @Column(name = "create_time")
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @LastModifiedDate
    @ApiModelProperty("修改时间")
    @Column(name = "update_time")
    private LocalDateTime updateTime;

}
