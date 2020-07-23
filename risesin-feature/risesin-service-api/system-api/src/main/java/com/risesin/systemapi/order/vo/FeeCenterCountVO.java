package com.risesin.systemapi.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-16
 * @DESCRIPTION C端主页费用额统计VO
 * @since 1.0.0
 */

@Data
@ApiModel("C端费用中心费用额统计VO")
public class FeeCenterCountVO {

    @ApiModelProperty("消费总额")
    private BigDecimal paidMoney;

    @ApiModelProperty("待缴费额度")
    private BigDecimal unpaidMoney;

    @ApiModelProperty("可索取发票金额")
    private BigDecimal invoiceMoney;

    @ApiModelProperty("待支付方案")
    private Integer unpaidNum;

}
