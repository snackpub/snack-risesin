package com.risesin.systemapi.plan.vo;

import com.risesin.systemapi.enterprise.dto.EnterpriseUserDTO;
import com.risesin.systemapi.plan.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 完善客户资料 additionalCustomerMaterialsBO
 *
 * @author honey
 * @date 2019/12/2
 */
@Data
@ApiModel("完善客户资料对象")
public class AdditionalCustomerMaterialsVO {
    private FinancingPlan financingPlan;
    private FinancingDemand financingDemand;
    private EnterpriseInfo enterpriseInfo;
    private LegalRepresentative representative;
    private EnterpriseAssetInfo assetInfo;
    private BusinessInfo businessInfo;
    private EnterpriseCreditInfo creditInfo;

    @ApiModelProperty("融资预案code")
    private String financingCode;

    public AdditionalCustomerMaterialsVO() {
    }

    public AdditionalCustomerMaterialsVO(FinancingPlan financingPlan, FinancingDemand financingDemand, EnterpriseInfo enterpriseInfo, LegalRepresentative representative, EnterpriseAssetInfo assetInfo, BusinessInfo businessInfo, EnterpriseCreditInfo creditInfo) {
        this.financingPlan = financingPlan;
        this.financingDemand = financingDemand;
        this.enterpriseInfo = enterpriseInfo;
        this.representative = representative;
        this.assetInfo = assetInfo;
        this.businessInfo = businessInfo;
        this.creditInfo = creditInfo;
    }

    public AdditionalCustomerMaterialsVO(FinancingDemand financingDemand, EnterpriseInfo enterpriseInfo, LegalRepresentative representative, EnterpriseAssetInfo assetInfo, BusinessInfo businessInfo, EnterpriseCreditInfo creditInfo, String financingCode) {
        this.financingDemand = financingDemand;
        this.enterpriseInfo = enterpriseInfo;
        this.representative = representative;
        this.assetInfo = assetInfo;
        this.businessInfo = businessInfo;
        this.creditInfo = creditInfo;
        this.financingCode = financingCode;
    }
}
