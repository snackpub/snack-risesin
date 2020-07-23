package com.risesin.agent.entity.vo;

import com.risesin.agent.entity.ComPanyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * comPanyOrder实体类
 *
 * @author Administrator
 */
@Data
@ApiModel(value = "订单vo", description = "订单vo")
public class ComPanyOrderVO extends ComPanyOrder implements Serializable {

    /**
     * 流程状态 1 未放款 2 审核通过 3 审核拒绝 4 已放款
     */
    @ApiModelProperty(value = "流程状态 1 未放款 2 审核通过 3 审核拒绝 4放款中 5已放款")
    private String flowName;
    /**
     * 支付方式
     */
    @ApiModelProperty(value = "微信,支付宝,网银")
    private String payMethodName;
}
