package com.risesin.enterprise.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @AUTHOR Darling
 * @CREATE 2019-11-26
 * @DESCRIPTION 融资申请信息
 * @since 1.0.0
 */

@Data
@ApiModel("融资申请信息")
public class FinancingApplication {

    @ApiModelProperty("融资需求")
    private FinancingDemandBO financingDemand; // 融资需求

    @ApiModelProperty("企业基本信息")
    private EnterpriseInfoBO enterpriseInfo; //企业基本信息

    @ApiModelProperty("法人信息")
    private LegalRepresentativeBO legalRepresentative; //法人信息

    @ApiModelProperty("企业资产信息")
    private EnterpriseAssetInfoBO enterpriseAssetInfo; //企业资产信息

    @ApiModelProperty("企业经营信息")
    private BusinessInfoBO businessInfo; //企业经营信息

    @ApiModelProperty("企业征信信息")
    private EnterpriseCreditInfoBO enterpriseCreditInfo; //企业征信信息

    @ApiModelProperty("申请人ID")
    private Integer enterpriseUserId; //企业用户ID

}
