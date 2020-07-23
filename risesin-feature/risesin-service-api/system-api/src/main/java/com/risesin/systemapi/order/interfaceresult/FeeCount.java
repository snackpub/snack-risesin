package com.risesin.systemapi.order.interfaceresult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("费用统计")
public interface FeeCount {

    @ApiModelProperty("总金额")
    BigDecimal getAmount();//总金额

    @ApiModelProperty("数量")
    int getNum();//数量

    @ApiModelProperty("支付状态未支付(0);已支付(1)")
    byte getStatus();//支付状态未支付(0);已支付(1)
}
