package com.risesin.systemapi.plan.vo;

import com.risesin.systemapi.plan.entity.FinancingDemand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-13
 * @DESCRIPTION 融资需求VO
 * @since 1.0.0
 */
@Data
@ApiModel("融资需求VO")
public class FinancingDemandVO extends FinancingDemand {

    @ApiModelProperty("排除融资方式集合")
    private List<String> excludeFinancingTypeList;
}
