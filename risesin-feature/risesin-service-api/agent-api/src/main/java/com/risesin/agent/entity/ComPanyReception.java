package com.risesin.agent.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * comPanyReception实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_com_pany_reception")
@Data
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class ComPanyReception implements Serializable {

    /**
     * 回执单id
     */
    @Id
    @Column(name = "pk_id")
    @ApiModelProperty(value = "回执单id")
    private String id;

    /**
     * 订单编号
     */
    @Column(name = "fk_order_id")
    @ApiModelProperty(value = "订单编号")
    private String fkOrderId;
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
//	@Column(name = "pay_method" )
//	@ApiModelProperty(value = "支付方式")
//	private Byte payMethod;
    /**
     * 付款时间
     */
    @Column(name = "pay_time")
    @ApiModelProperty(value = "付款时间")
    private LocalDateTime payTime;
    /**
     * 付款方
     */
    @Column(name = "payer")
    @ApiModelProperty(value = "付款方")
    private String payer;
    /**
     * 付款方银行开户行
     */
    @Column(name = "payer_bank_name")
    @ApiModelProperty(value = "付款方银行开户行")
    private String payerBankName;
    /**
     * 付款方银行卡号
     */
    @Column(name = "payer_bank_code")
    @ApiModelProperty(value = "付款方银行卡号")
    private String payerBankCode;

    /**
     * 流水号
     */
    @Column(name = "sequence_number")
    @ApiModelProperty(value = "流水号")
    private String sequenceNumber;

    /**
     * 状态：-1 不一致  0正常
     */
    @Column(name = "status")
    @ApiModelProperty(value = "状态：-1 不一致  0正常")
    private Byte status;
    /**
     * 凭证图片名称
     */
    @Column(name = "receipt_img_name")
    @ApiModelProperty(value = "凭证图片名称")
    private String receiptImgName;
    /**
     * 凭证图片地址
     */
    @Column(name = "receipt_path", columnDefinition = "longtext")
    @ApiModelProperty(value = "凭证图片地址")
    private String receiptPath;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    @CreatedDate
    private LocalDateTime createTime;
}
