package com.risesin.enterprise.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * businessInfo实体类
 *
 * @author Administrator
 */

@Data
public class BusinessInfoBO implements Serializable {

    /**
     * 上年度总营收
     */
    @ApiModelProperty("上年度总营收")
    private BigDecimal lastYearRevenue;

    /**
     * 上年度扣非营收
     */
    @ApiModelProperty("上年度扣非营收")
    private BigDecimal rerevenueExcludeManage;

    /**
     * 企业负债率
     */
    @ApiModelProperty("企业负债率")
    private BigDecimal debtrate;

    /**
     * 已有贷款：5笔
     */
    @ApiModelProperty("已有贷款笔数")
    private Integer loanNum;

    /**
     * 信用卡额度使用比例
     */
    @ApiModelProperty("信用卡额度使用比例")
    private BigDecimal creditCardUsageRate;

    /**
     * 发票开票时长：(99)个月。单位：月
     */
    @ApiModelProperty("发票开票时长：(99)个月。单位：月")
    private Integer invoiceTime;

    /**
     * 上年度开票金额
     */
    @ApiModelProperty("上年度开票金额")
    private BigDecimal lastYearInvoiceAmount;

    /**
     * 季报（一季报，半年报，三季报）。‘1':一季报，‘2'半年报，‘3'：三季报。默认值为0。
     */
    @ApiModelProperty("季报（一季报，半年报，三季报）。‘1':一季报，‘2'半年报，‘3'：三季报。默认值为0。")
    private Byte quarter;

    /**
     * 季报或半年报纳税金额
     */
    @ApiModelProperty("季报或半年报纳税金额")
    private BigDecimal quarterTax;

    /**
     * 本年度纳税评级。级别：A;B;C;D;M
     */
    @ApiModelProperty("本年度纳税评级。级别：A;B;C;D;M")
    private char thisYearTaxRate;

    /**
     * 上年度纳税评级。级别：A;B;C;D;M
     */
    @ApiModelProperty("上年度纳税评级。级别：A;B;C;D;M ")
    private char lastYearTaxRate;

}
