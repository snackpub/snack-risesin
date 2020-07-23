package com.risesin.systemapi.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-10
 * @DESCRIPTION C端主页费用额统计VO
 * @since 1.0.0
 */
@Data
@ApiModel("C端主页费用额统计VO")
public class FeeAmountVO {

    @ApiModelProperty("咨询费")
    private BigDecimal consultFee;

    @ApiModelProperty("其它费")
    private BigDecimal otherFee;

    @ApiModelProperty("可开发票额度")
    private BigDecimal invoiceMoney;
}
