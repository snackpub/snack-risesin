package com.risesin.systemapi.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * sysOrder实体类
 *
 * @author Administrator
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@ApiModel("系统订单对象")
@Table(name = "t_sys_order")
@EntityListeners(AuditingEntityListener.class)
public class SysOrder implements Serializable {

    @Id
    @ApiModelProperty("主键id")
    @Column(name = "pay_order_id")
    private String id;

    @ApiModelProperty("所属公司")
    @Column(name = "enterprise_name")
    private String enterpriseName;

    @ApiModelProperty("所属方案（对应融资方案名称）")
    @Column(name = "plan_name")
    private String planName;

    @ApiModelProperty("方案名称（对应子方案名称）")
    @Column(name = "child_plan_name")
    private String childPlanName;

    @ApiModelProperty("方案编号")
    @Column(name = "child_plan_code")
    private String childPlanCode;

    @ApiModelProperty("缴费类型:产品收费项(1);咨询服务费(2)")
    @Column(name = "expense_type")
    private Byte expenseType;

    @ApiModelProperty("订单创建时间")
    @Column(name = "create_time")
    @CreatedDate
    private LocalDateTime createTime;

    @ApiModelProperty("订单修改时间")
    @Column(name = "update_time")
    @LastModifiedDate
    private LocalDateTime updateTime;

    @ApiModelProperty("付款时间")
    @Column(name = "pay_time")
    private LocalDateTime payTime;

    @ApiModelProperty("订单创建人")
    @Column(name = "fk_create_user")
    @CreatedBy
    private String createUser;

    @ApiModelProperty("订单创建人姓名")
    @Column(name = "create_name")
    private String createName;


    @ApiModelProperty("银行卡id")
    @Column(name = "fk_bank_card_id")
    private Integer bankCardId;

    /**
     * 缴费方式:WX_JSAPI(微信公众号支付);WX_NATIVE(微信原生扫码支付);WX_APP(微信APP支付);WX_MWEB(微信H5支付);ALIPAY_MOBILE(支付宝移动支付);
     * ALIPAY_PC(支付宝PC支付);ALIPAY_WAP(支付宝WAP支付);ALIPAY_QR(支付宝当面付之扫码支付);WANGYIN(网银支付)
     */
    @ApiModelProperty("缴费方式：支付宝;微信;网银转账")
    @Column(name = "channel_id")
    private String channelId;

    @ApiModelProperty("缴费状态：审核中(1);待支付(2);支付中(3);已付款(4);确认收款(5);已超时(6)(一个月未支付状态设为\"已超时\")")
    @Column(name = "flow")
    private Byte flow;

    @ApiModelProperty("缴费金额(单位分)")
    @Column(name = "amount")
    private BigDecimal amount;

    @ApiModelProperty("支付渠道用户ID(微信openID或支付宝账号等第三方支付账号")
    @Column(name = "channel_user_id")
    private String channelUserId;

    @ApiModelProperty("额外参数")
    @Column(name = "extra")
    private String extra;

    @ApiModelProperty("描述信息")
    @Column(name = "description")
    private String description;

    @ApiModelProperty("异步通知地址")
    @Column(name = "notifyUrl")
    private String notifyUrl;

    @ApiModelProperty("收款方银行卡号")
    @Column(name = "payee_bank_card_code")
    private String payeeBankCardCode;

    @ApiModelProperty("收款方")
    @Column(name = "payee")
    private String payee;

    @ApiModelProperty("收款方银行开户行")
    @Column(name = "payee_bank_name")
    private String payeeBankName;

    @ApiModelProperty("付款方银行卡号")
    @Column(name = "payer_bank_card_code")
    private String payerBankCardCode;

    @ApiModelProperty("付款方")
    @Column(name = "payer")
    private String payer;

    @ApiModelProperty("付款方银行开户行")
    @Column(name = "payer_bank_name")
    private String payerBankName;

    @ApiModelProperty("企业用户ID")
    @Column(name = "fk_ent_user_id")
    private String entUserId;

    @ApiModelProperty("财务人员ID")
    @Column(name = "finance_staff")
    private String financeStaff;

    @ApiModelProperty("财务人员(订单审核人)")
    @Column(name = "verifier_name")
    private String verifierName;

    @ApiModelProperty("确认收款时间")
    @Column(name = "confirm_time")
    private LocalDateTime confirmTime;

    @ApiModelProperty("收费项")
    @Column(name = "other_charges")
    private String otherCharges;

    @Transient
    @ApiModelProperty("凭证图片地址 ")
    private String receiptPath;

    @Transient
    @ApiModelProperty("凭证图片名称")
    private String receiptImgName;

}
