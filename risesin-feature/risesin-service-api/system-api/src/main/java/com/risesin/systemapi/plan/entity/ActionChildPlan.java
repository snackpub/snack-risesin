package com.risesin.systemapi.plan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * actionChildPlan实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_action_child_plan")
@Data
@ApiModel("子执行方案")
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class ActionChildPlan implements Serializable {

    @Id
    @Column(name = "child_plan_code")
    @ApiModelProperty("编号主键")
    private String childPlanCode;

    @Column(name = "child_plan_name")
    @ApiModelProperty("子方案名称")
    private String childPlanName;

    @Column(name = "fk_plan_id")
    @ApiModelProperty("执行方案id")
    private Integer planId;

    @Column(name = "fk_product_id")
    @ApiModelProperty("产品ID")
    private Integer fkProductId;

    @Column(name = "fk_loan_agency_id")
    @ApiModelProperty("提供产品机构(助贷机构ID)")
    private Integer fkLoanAgencyId;

    @Column(name = "financing_amount")
    @ApiModelProperty("融资金额")
    private BigDecimal financingAmount;

    @Column(name = "agent_opinion")
    @ApiModelProperty("助贷机构审核意见")
    private String agentOpinion;

    @Column(name = "final_amount")
    @ApiModelProperty("助贷机构终审额度")
    private BigDecimal finalAmount;

    /**
     * 客户收款方
     */
    @Column(name = "payee")
    @ApiModelProperty(value = "客户收款方")
    private String payee;
    /**
     * 客户收款方银行开户行
     */
    @Column(name = "payee_bank_name")
    @ApiModelProperty(value = "客户收款方银行开户行")
    private String payeeBankName;
    /**
     * 客户收款方银行卡号
     */
    @Column(name = "payee_bank_code")
    @ApiModelProperty(value = "客户收款方银行卡号")
    private String payeeBankCode;

    @Column(name = "confirm_time")
    @ApiModelProperty("客户确认时间")
    private LocalDateTime confirmTime;

    @ApiModelProperty("客户是否接受：0 不接受 1 接受")
    @Column(name = "is_accept")
    private Boolean accept;

    @Column(name = "fk_ent_user_id")
    @ApiModelProperty(value = "企业用户id")
    private String entUserId;

    @Column(name = "loan_cycle")
    @ApiModelProperty("贷款周期")
    private Short loanCycle;

    @Column(name = "repayment")
    @ApiModelProperty("还款方式：1 等额本息 2 等额本金 3 按月付息 4 到期还本")
    private Byte repayment;

    @Column(name = "flow")
    @ApiModelProperty("融资申请(1);完善资料(2);资料审核(3);风控审核(4);预付款审核(5);预付款订单支付(6);资方额度审批(7);客户额度确认(8);放款(9);确认收款(10);关闭(11)")
    private Byte flow;

    @Column(name = "audit_status")
    @ApiModelProperty("审核状态")
    private Byte auditStatus;

    @Column(name = "audit_opinion")
    @ApiModelProperty("审核意见")
    private String auditOpinion;

    @ApiModelProperty("创建人ID(平台用户)")
    @Column(name = "create_user")
    private String createUser;

    @Column(name = "handle_person")
    @ApiModelProperty("经手人(平台加前缀sys_，助贷端加前缀loan_)")
    private String handlePerson;

    @Column(name = "interest_rate")
    @ApiModelProperty("利率")
    private BigDecimal interestRate;

    @Column(name = "interest_total")
    @ApiModelProperty("利息总额")
    private BigDecimal interestTotal;

    @Column(name = "calculate_interest")
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private String calculateInterest;

    @Column(name = "other_charges")
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private String otherCharges;

    @ApiModelProperty("其他")
    @Column(name = "other")
    private String other;

    @Column(name = "status")
    @ApiModelProperty("状态: 0 启用(正常)  2注销（删除）")
    private Byte status;

    @Column(name = "create_time")
    @ApiModelProperty("创建时间")
    @CreatedDate
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @ApiModelProperty("修改时间")
    @LastModifiedDate
    private LocalDateTime updateTime;

}
