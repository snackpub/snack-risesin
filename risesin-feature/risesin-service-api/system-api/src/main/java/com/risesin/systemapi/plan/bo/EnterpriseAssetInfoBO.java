package com.risesin.systemapi.plan.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * enterpriseAssetInfo实体类
 *
 * @author Administrator
 */

@Data
@ApiModel("企业资产信息")
public class EnterpriseAssetInfoBO implements Serializable {

    /**
     * 资产总值
     */
    @ApiModelProperty("资产总值")
    private BigDecimal totalAssets;

    /**
     * 非经营性资产总值
     */
    @ApiModelProperty("非经营性资产总值")
    private BigDecimal nonOperatingAssets;

    /**
     * 申请区域房产：(5)处
     */
    @ApiModelProperty("申请区域房产：(5)处")
    private Short realEstateNumber;

    /**
     * 申请区域房产估值
     */
    @ApiModelProperty("申请区域房产估值")
    private BigDecimal realEstateValuation;

    /**
     * 质押物估值
     */
    @ApiModelProperty("质押物估值")
    private BigDecimal pledgeValuation;

    /**
     * 应收款总额
     */
    @ApiModelProperty("应收款总额")
    private BigDecimal totalReceivables;

    /**
     * 是否有特别经营许可:否(0);是(1)
     */
    @ApiModelProperty("是否有特别经营许可")
    private Boolean hasBusinessLicense;

    /**
     * 是否存在以下项目用地:工业用地;物流仓储用地;科教用地
     */
    @ApiModelProperty("是否存在以下项目用地:工业用地;物流仓储用地;科教用地")
    private String projectSite;

    /**
     * 是否有刑事或民事纠纷:否(0);是(1)
     */
    @ApiModelProperty("是否有刑事或民事纠纷")
    private Boolean hasCase;

}
