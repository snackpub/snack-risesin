package com.risesin.systemapi.plan.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * financingDemand实体类
 *
 * @author Darling
 */
@Data
@ApiModel("融资需求")
public class FinancingDemandBO implements Serializable {

    /**
     * 融资金额
     */
    @ApiModelProperty("融资金额")
    private BigDecimal financingAmount;

    /**
     * 借款周期
     */
    @ApiModelProperty("借款周期")
    private Integer loanPeriod;

    /**
     * 排除融资方式
     */
    @ApiModelProperty("排除融资方式")
    private String excludeFinancingType;

    /**
     * 是否上征信：1 否 0 是
     */
    @ApiModelProperty("是否上征信：1 否 0 是")
    private Boolean enableCredit;

}
