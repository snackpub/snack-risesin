package com.risesin.agent.entity;

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
 * comPanyOrder实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_com_pany_order")
@Data
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class ComPanyOrder implements Serializable {

    /**
     * 订单编号
     */
    @Id
    @Column(name = "pk_id")
    @ApiModelProperty(value = "订单编号")
    private String id;

    /**
     * 方案名称
     */
    @Column(name = "child_plan_name")
    @ApiModelProperty(value = "方案名称")
    private String childPlanName;
    /**
     * 方案编号
     */
    @Column(name = "child_plan_code")
    @ApiModelProperty(value = "方案编号")
    private String childPlanCode;
    /**
     * 流水号
     */
    @Column(name = "sequence_number")
    @ApiModelProperty(value = "流水号")
    private String sequenceNumber;
    /**
     * 收款方
     */
    @Column(name = "payee")
    @ApiModelProperty(value = "收款方")
    private String payee;
    /**
     * 收款方银行开户行
     */
    @Column(name = "payee_bank_name")
    @ApiModelProperty(value = "收款方银行开户行")
    private String payeeBankName;
    /**
     * 收款方银行卡号
     */
    @Column(name = "payee_bank_code")
    @ApiModelProperty(value = "收款方银行卡号")
    private String payeeBankCode;
    /**
     * 转账金额
     */
    @Column(name = "amount")
    @ApiModelProperty(value = "转账金额")
    private BigDecimal amount;
    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    @Column(name = "pay_method")
    private String payMethod;
    /**
     * 付款时间
     */
    @ApiModelProperty(value = "付款时间")
    @Column(name = "pay_time")
    private LocalDateTime payTime;
    /**
     * 状态：-1 删除  0正常
     */
    @ApiModelProperty(value = "状态：-1 删除  0正常")
    @Column(name = "status")
    private Byte status;
    /**
     * 凭证图片名称
     */
    @ApiModelProperty(value = "凭证图片名称")
    @Column(name = "receipt_img_name")
    private String receiptImgName;
    /**
     * 凭证图片地址
     */
    @ApiModelProperty(value = "凭证图片地址")
    @Column(name = "receipt_path", columnDefinition = "longtext")
    private String receiptPath;
    /**
     * 付款方
     */
    @ApiModelProperty(value = "付款方")
    @Column(name = "payer")
    private String payer;
    /**
     * 付款方银行开户行
     */
    @ApiModelProperty(value = "付款方银行开户行")
    @Column(name = "payer_bank_name")
    private String payerBankName;
    /**
     * 付款方银行卡号
     */
    @ApiModelProperty(value = "付款方银行卡号")
    @Column(name = "payer_bank_code")
    private String payerBankCode;
    /**
     * 审核人id
     */
    @ApiModelProperty(value = "审核人id")
    @Column(name = "verifier_id")
    private String verifierId;
    /**
     * 审核人名称
     */
    @ApiModelProperty(value = "审核人名称")
    @Column(name = "verifier_name")
    private String verifierName;
    /**
     * 审核意见
     */
    @ApiModelProperty(value = "审核意见")
    @Column(name = "verify_result")
    private String verifyResult;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @LastModifiedDate
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @CreatedDate
    @Column(name = "create_time")
    private LocalDateTime createTime;
    /**
     * 流程状态 1 未放款 2 审核通过 3 审核拒绝 4放款中 5 已放款 6 确认收款
     */
    @Column(name = "flow")
    @ApiModelProperty(value = "流程状态 1 未放款 2 审核通过 3 审核拒绝 4放款中 5 已放款 6 确认收款")
    private Byte flow;
    /**
     * 创建人(创建放款订单的人)
     */
    @Column(name = "create_id")
    @ApiModelProperty(value = "创建人(创建放款订单的人)")
    private String createId;
    /**
     * 创建人姓名
     */
    @Column(name = "create_name")
    @ApiModelProperty(value = "创建人姓名")
    private String createName;

    @Column(name = "fk_ent_user_id")
    @ApiModelProperty(value = "企业用户id")
    private String entUserId;

}
