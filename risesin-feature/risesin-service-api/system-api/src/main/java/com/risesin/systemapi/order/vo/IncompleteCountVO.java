package com.risesin.systemapi.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-20
 * @DESCRIPTION C端首页未完成的事项统计
 * @since 1.0.0
 */
@Data
@ApiModel("C端首页未完成的事项统计")
public class IncompleteCountVO {

    @ApiModelProperty("待支付订单数")
    private Integer unpaidNum;

    @ApiModelProperty("未完成方案数")
    private Integer incompletePlanNum;

    @ApiModelProperty("待处理事项数")
    private Integer todoMessageNum;
}
