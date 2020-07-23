package com.risesin.systemapi.plan.bo;

import com.risesin.systemapi.enterprise.dto.EnterpriseUserDTO;
import com.risesin.systemapi.plan.entity.*;
import com.risesin.systemapi.plan.vo.AdditionalCustomerMaterialsVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 完善客户资料 additionalCustomerMaterialsBO
 *
 * @author honey
 * @date 2019/12/2
 */
@Data
@ApiModel("完善客户资料业务对象")
@EqualsAndHashCode(callSuper = true)
public class AdditionalCustomerMaterialsBO extends AdditionalCustomerMaterialsVO {

    @ApiModelProperty("流程状态：申请(0);进行中(1);已完成(2);已关闭(3)")
    private Byte flow;

    public AdditionalCustomerMaterialsBO() {
    }

    public AdditionalCustomerMaterialsBO(FinancingPlan financingPlan, FinancingDemand financingDemand, EnterpriseInfo enterpriseInfo, LegalRepresentative representative, EnterpriseAssetInfo assetInfo, BusinessInfo businessInfo, EnterpriseCreditInfo creditInfo) {
        super(financingPlan, financingDemand, enterpriseInfo, representative, assetInfo, businessInfo, creditInfo);
    }

    public AdditionalCustomerMaterialsBO(FinancingDemand financingDemand, EnterpriseInfo enterpriseInfo, LegalRepresentative representative, EnterpriseAssetInfo assetInfo, BusinessInfo businessInfo, EnterpriseCreditInfo creditInfo, String financingCode) {
        super(financingDemand, enterpriseInfo, representative, assetInfo, businessInfo, creditInfo, financingCode);
    }
}
