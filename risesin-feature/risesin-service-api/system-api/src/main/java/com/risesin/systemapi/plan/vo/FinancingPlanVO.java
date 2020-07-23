package com.risesin.systemapi.plan.vo;

import com.risesin.systemapi.plan.bo.AdditionalCustomerMaterialsBO;
import com.risesin.systemapi.plan.entity.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * FinancingPlan 视图类
 *
 * @author honey
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FinancingPlanVO extends FinancingPlan {

    @ApiModelProperty("预案状态")
    private String statusName;

    @ApiModelProperty("预案信息")
    private AdditionalCustomerMaterialsBO customerMaterialsBO;


}
