package com.risesin.systemapi.plan.bo;

import com.risesin.systemapi.plan.entity.BusinessInfo;
import com.risesin.systemapi.plan.entity.EnterpriseInfo;
import com.risesin.systemapi.plan.entity.LegalRepresentative;
import com.risesin.systemapi.plan.vo.EnterpriseAssetInfoVO;
import com.risesin.systemapi.plan.vo.EnterpriseCreditInfoVO;
import com.risesin.systemapi.plan.vo.FinancingDemandVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @AUTHOR Darling
 * @CREATE 2019-11-26
 * @DESCRIPTION 融资申请信息
 * @since 1.0.0
 */

@Data
@ApiModel("融资申请信息")
public class FinancingApplication implements Serializable {

    @ApiModelProperty("融资需求")
    private FinancingDemandVO financingDemandVO;

    @ApiModelProperty("企业基本信息")
    private EnterpriseInfo enterpriseInfo;

    @ApiModelProperty("法人信息")
    private LegalRepresentative legalRepresentative;

    @ApiModelProperty("企业资产信息")
    private EnterpriseAssetInfoVO enterpriseAssetInfoVO;

    @ApiModelProperty("企业经营信息")
    private BusinessInfo businessInfo;

    @ApiModelProperty("企业征信信息")
    private EnterpriseCreditInfoVO enterpriseCreditInfoVO;


}
