package com.risesin.enterprise.order.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * comPanyOrder实体类
 *
 * @author Administrator
 */
@Data
public class ComPanyOrder implements Serializable {

    @ApiModelProperty(value = "订单编号")
    private String id;

    @ApiModelProperty(value = "方案名称")
    private String childPlanName;

    @ApiModelProperty(value = "方案编号")
    private String childPlanCode;

    @ApiModelProperty(value = "流水号")
    private String sequenceNumber;

    @ApiModelProperty(value = "收款方")
    private String payee;

    @ApiModelProperty(value = "收款方银行开户行")
    private String payeeBankName;

    @ApiModelProperty(value = "收款方银行卡号")
    private String payeeBankCode;

    @ApiModelProperty(value = "转账金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "支付方式")
    private String payMethod;

    @ApiModelProperty(value = "付款时间")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "状态：-1 删除  0正常")
    private Byte status;

    @ApiModelProperty(value = "凭证图片名称")
    private String receiptImgName;

    @ApiModelProperty(value = "凭证图片地址")
    private String receiptPath;

    @ApiModelProperty(value = "付款方")
    private String payer;

    @ApiModelProperty(value = "付款方银行开户行")
    private String payerBankName;

    @ApiModelProperty(value = "付款方银行卡号")
    private String payerBankCode;

    @ApiModelProperty(value = "审核人id")
    private String verifierId;

    @ApiModelProperty(value = "审核人名称")
    private String verifierName;

    @ApiModelProperty(value = "审核意见")
    private String verifyResult;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "流程状态 1 未放款 2 审核通过 3 审核拒绝 4放款中 5 已放款 6 确认收款")
    private Byte flow;

    @ApiModelProperty(value = "创建人(创建放款订单的人)")
    private String createId;

    @ApiModelProperty(value = "创建人姓名")
    private String createName;

    @ApiModelProperty(value = "企业用户id")
    private String entUserId;

}
