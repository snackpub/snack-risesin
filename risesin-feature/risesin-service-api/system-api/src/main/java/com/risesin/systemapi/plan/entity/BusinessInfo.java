package com.risesin.systemapi.plan.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 企业营业信息实体类
 *
 * @author Administrator
 */
@Data
@Entity
@ApiModel("BusinessInfo对象")
@Table(name = "t_business_info")
@DynamicInsert
public class BusinessInfo implements Serializable {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("上年度总营收")
    @Column(name = "last_year_revenue")
    private BigDecimal lastYearRevenue;

    @ApiModelProperty("上年度扣非营收")
    @Column(name = "rerevenue_exclude_manage")
    private BigDecimal rerevenueExcludeManage;

    @Column(name = "debtrate")
    @ApiModelProperty("企业负债率")
    private BigDecimal debtrate;

    @ApiModelProperty("亏损状态:近(99)个月无亏损")
    @Column(name = "deficit_status")
    private Integer deficitStatus;

    @ApiModelProperty("已有贷款，单位：笔")
    @Column(name = "loan_num")
    private Integer loanNum;

    @ApiModelProperty("网贷次数")
    @Column(name = "net_loan_count")
    private Integer netLoanCount;

    @ApiModelProperty("信用卡额度使用比例,单位：%")
    @Column(name = "credit_card_usage_rate")
    private BigDecimal creditCardUsageRate;

    @Column(name = "invoice_time")
    @ApiModelProperty("发票开票时长：(99)月。单位：月")
    private Integer invoiceTime;

    @Column(name = "general_invoice_time")
    @ApiModelProperty("普通发票开票时长:(99)个月 单位：月")
    private Integer generalInvoiceTime;

    @Column(name = "vat_invoice_time")
    @ApiModelProperty("增值专用发票开票时长:(99)个月 单位：月")
    private Integer vatInvoiceTime;

    @Column(name = "vat_invoice_amount")
    @ApiModelProperty("增值专用发票开票金额")
    private BigDecimal vatInvoiceAmount;

    @ApiModelProperty("上年度开票金额")
    @Column(name = "last_year_invoice_amount")
    private BigDecimal lastYearInvoiceAmount;

    @Column(name = "quarter")
    @ApiModelProperty("季报（一季报，半年报，三季报）。‘1':一季报，‘2'半年报，‘3'：三季报。默认值为0")
    private Byte quarter;

    @Column(name = "quarter_revenue")
    @ApiModelProperty("季报或半年报营业金额")
    private BigDecimal quarterRevenue;

    @Column(name = "this_year_tax_rate")
    @ApiModelProperty("本年度纳税评级。级别：A;B;C;D;M")
    private String thisYearTaxRate;

    @Column(name = "last_year_tax_rate")
    @ApiModelProperty("上年度纳税评级。级别：A;B;C;D;M")
    private String lastYearTaxRate;

}
