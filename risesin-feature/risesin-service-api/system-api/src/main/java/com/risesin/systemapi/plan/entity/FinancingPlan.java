package com.risesin.systemapi.plan.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 融资预案信息实体类
 *
 * @author Administrator
 */
@Data
@Entity
@ApiModel("融资预案信息实体")
@Table(name = "t_financing_plan")
@EntityListeners(AuditingEntityListener.class)
public class FinancingPlan implements Serializable {


    @Id
    @Column(name = "pk_id")
    @ApiModelProperty("主键ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ApiModelProperty("融资预案code")
    @Column(name = "financing_code")
    private String financingCode;

    @ApiModelProperty("融资预案名臣")
    @Column(name = "name")
    private String name;

    @ApiModelProperty("融资需求ID")
    @Column(name = "fk_fin_demand_id")
    private Integer finDemandId;

    @Column(name = "fk_ent_info_id")
    @ApiModelProperty("企业基本信息ID")
    private Integer entInfoId;

    @Column(name = "fk_leg_rep_id")
    @ApiModelProperty("法人代表信息ID")
    private Integer legRepId;

    @Column(name = "fk_business_id")
    @ApiModelProperty("企业经营信息ID")
    private Integer businessId;

    @Column(name = "fk_ent_asset_id")
    @ApiModelProperty("企业资产信息ID")
    private Integer entAssetId;

    @ApiModelProperty("企业征信信息ID")
    @Column(name = "fk_ent_credit_id")
    private Integer entCreditId;

    @ApiModelProperty("企业用户ID")
    @Column(name = "fk_ent_user_id")
    private String entUserId;

    @CreatedDate
    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @LastModifiedDate
    @ApiModelProperty("修改日期")
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "status")
    @ApiModelProperty("是否咨询 0 咨询 1 未咨询")
    private Byte status;

    @Column(name = "other", columnDefinition = "Text")
    @ApiModelProperty("其它")
    private String other;

    @ApiModelProperty("客服ID")
    @Column(name = "cust_serv_id")
    private Integer customerServiceId;

    @ApiModelProperty("客服名称")
    @Column(name = "cust_serv_name")
    private String customerServiceName;

    @ApiModelProperty("接待时间")
    @Column(name = "reception_time")
    private LocalDateTime receptionTime;

    @ApiModelProperty("融资主体")
    @Column(name = "company_name")
    private String companyName;

    @ApiModelProperty("法人代表")
    @Column(name = "legal_name")
    private String legalName;

    @ApiModelProperty("融资金额")
    @Column(name = "financing_amount")
    private BigDecimal financingAmount;

    @ApiModelProperty("PDF预览地址")
    @Column(name = "preview_link")
    private String previewLink;

    @ApiModelProperty("PDF下载地址")
    @Column(name = "download_link")
    private String downloadLink;


}
