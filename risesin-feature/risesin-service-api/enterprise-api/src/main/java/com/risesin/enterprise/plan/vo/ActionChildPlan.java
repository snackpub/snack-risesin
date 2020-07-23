package com.risesin.enterprise.plan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * actionChildPlan实体类
 *
 * @author Administrator
 */

@Data
@ApiModel("子执行方案")
public class ActionChildPlan implements Serializable {

    @ApiModelProperty("编号主键")
    private String childPlanCode;

    @ApiModelProperty("子方案名称")
    private String childPlanName;

    @ApiModelProperty("执行方案id")
    private Integer planId;

    @ApiModelProperty("产品ID")
    private Integer fkProductId;

    @ApiModelProperty("提供产品机构(助贷机构ID)")
    private Integer fkLoanAgencyId;

    @ApiModelProperty("融资金额")
    private BigDecimal financingAmount;

    @ApiModelProperty("助贷机构审核意见")
    private String agentOpinion;

    @ApiModelProperty("助贷机构终审额度")
    private BigDecimal finalAmount;

    @ApiModelProperty(value = "客户收款方")
    private String payee;

    @ApiModelProperty(value = "客户收款方银行开户行")
    private String payeeBankName;

    @ApiModelProperty(value = "客户收款方银行卡号")
    private String payeeBankCode;

    @ApiModelProperty("客户确认时间")
    private LocalDateTime confirmTime;

    @ApiModelProperty("客户是否接受：0 不接受 1 接受")
    private Boolean accept;

    @ApiModelProperty("贷款周期")
    private Short loanCycle;

    @ApiModelProperty("还款方式：1 等额本息 2 等额本金 3 按月付息 4 到期还本")
    private Byte repayment;

    @ApiModelProperty("融资申请(1);完善资料(2);资料审核(3);风控审核(4);预付款审核(5);预付款订单支付(6);资方额度审批(7);客户额度确认(8);放款(9);确认收款(10);关闭(11)")
    private Byte flow;

    @ApiModelProperty("审核状态")
    private Byte auditStatus;

    @ApiModelProperty("审核意见")
    private String auditOpinion;

    @ApiModelProperty("创建人ID(平台用户)")
    private String createUser;

    @ApiModelProperty("经手人(平台加前缀sys_，助贷端加前缀loan_)")
    private String handlePerson;

    @ApiModelProperty("利率")
    private BigDecimal interestRate;

    @ApiModelProperty("利息总额")
    private BigDecimal interestTotal;

    @ApiModelProperty(hidden = true)
    private String calculateInterest;

    @ApiModelProperty(hidden = true)
    private String otherCharges;

    @ApiModelProperty("其他")
    private String other;

    @ApiModelProperty("状态: 0 启用(正常)  2注销（删除）")
    private Byte status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

}
