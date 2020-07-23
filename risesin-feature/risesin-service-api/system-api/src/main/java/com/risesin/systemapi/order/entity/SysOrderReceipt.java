package com.risesin.systemapi.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * sysOrderReceipt实体类
 *
 * @author Administrator
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "t_sys_order_receipt")
@ApiModel("系统订单回执对象")
@EntityListeners(AuditingEntityListener.class)
public class SysOrderReceipt implements Serializable {

    @Id
    @Column(name = "receipt_code")
    @ApiModelProperty("回执编号")
    private String receiptCode;

    @ApiModelProperty("方案编号")
    @Column(name = "child_plan_code")
    private String childPlanCode;

    @ApiModelProperty("订单编号")
    @Column(name = "order_code")
    private String orderCode;

    @ApiModelProperty("收款方")
    @Column(name = "payee")
    private String payee;

    @ApiModelProperty("收款方银行开户行/支付宝/微信。例如：农业银行")
    @Column(name = "payee_bank_name")
    private String payeeBankName;

    @ApiModelProperty("收款方银行卡号/支付宝账号/微信账号")
    @Column(name = "payee_bank_card_code")
    private String payeeBankCode;

    @ApiModelProperty("付款方")
    @Column(name = "payer")
    private String payer;

    @ApiModelProperty("付款方银行开户行/支付宝/微信。例如：农业银行")
    @Column(name = "payer_bank_name")
    private String payerBankName;

    @ApiModelProperty("付款方银行卡号/支付宝账号/微信账号")
    @Column(name = "payer_bank_card_code")
    private String payerBankCode;

    @ApiModelProperty("付款金额")
    @Column(name = "pay_amout")
    private BigDecimal payAmout;

    @ApiModelProperty("流水号")
    @Column(name = "sequence_number")
    private String sequenceNumber;

    /**
     * 支付方式:WX_JSAPI(微信公众号支付);WX_NATIVE(微信原生扫码支付);WX_APP(微信APP支付);WX_MWEB(微信H5支付);ALIPAY_MOBILE(支付宝移动支付);
     * ALIPAY_PC(支付宝PC支付);ALIPAY_WAP(支付宝WAP支付);ALIPAY_QR(支付宝当面付之扫码支付);WANGYIN(网银支付)
     */
    @ApiModelProperty("支付方式：1 网银转账 2 微信 3 支付宝")
    @Column(name = "pay_method")
    private String payMethod;

    @ApiModelProperty("支付时间")
    @Column(name = "pay_time")
    private LocalDateTime payTime;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    @CreatedDate
    private LocalDateTime createTime;

    @ApiModelProperty("订单修改时间")
    @Column(name = "update_time")
    @LastModifiedDate
    private LocalDateTime updateTime;

    @ApiModelProperty("状态：1待确认 2已付款")
    @Column(name = "flow")
    private Byte flow;

    @ApiModelProperty("经手人姓名 (系统自动创建凭证经手人为admin)")
    @Column(name = "sys_user_name")
    private String sysUserName;

    @ApiModelProperty("经手人ID (系统自动生成凭证，经手人为admin对应的ID)")
    @Column(name = "fk_sys_user")
    private String sysUserId;

    @ApiModelProperty("凭证图片地址 (如为支付宝微信支付，该字段可为空)")
    @Column(name = "receipt_path")
    private String receiptPath;

    @ApiModelProperty("凭证图片名称 (如为支付宝微信支付，该字段可为空)")
    @Column(name = "receipt_img_name")
    private String receiptImgName;

}
