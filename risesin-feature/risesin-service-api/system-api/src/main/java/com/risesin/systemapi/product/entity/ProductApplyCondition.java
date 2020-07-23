package com.risesin.systemapi.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * productApplyCondition实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_product_apply_condition")
@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@ApiModel("准入条件")
public class ProductApplyCondition implements Serializable {

    /**
     * 主键
     */
    @Id
    @Column(name = "pk_id")
    @ApiModelProperty("主键")
    private String id;

    /**
     * 排除行业
     */
    @Column(name = "exclude_industry")
    @ApiModelProperty(value = "排除行业", hidden = true)
    @JsonIgnore
    private String excludeIndustry;
    /**
     * 可选地区
     */
    @Column(name = "area")
    @JsonIgnore
    @ApiModelProperty(value = "可选地区", hidden = true)
    private String area;
    /**
     * 适用职业： 1 公务员或企事业单位 2 世界五百强企业 3 其他
     */
    @Column(name = "applicable_career")
    @JsonIgnore
    @ApiModelProperty(value = "适用职业： 1 公务员或企事业单位 2 世界五百强企业 3 其他", hidden = true)
    private String applicableCareer;
    /**
     * 申请人最小年龄
     */
    @Column(name = "applicant_age_min")
    @ApiModelProperty("申请人最小年龄")
    private Byte applicantAgeMin;
    /**
     * 申请人最大年龄
     */
    @Column(name = "applicant_age_max")
    @ApiModelProperty("申请人最大年龄")
    private Byte applicantAgeMax;
    /**
     * 经营时间
     */
    @Column(name = "operate_time")
    @ApiModelProperty("经营时间")
    private Integer operateTime;
    /**
     * 法人占股
     */
    @Column(name = "corporation_share")
    @ApiModelProperty("法人占股")
    private BigDecimal corporationShare;
    /**
     * 注册资本
     */
    @Column(name = "registered_capital")
    @ApiModelProperty("注册资本")
    private BigDecimal registeredCapital;
    /**
     * 核年销售总额
     */
    @Column(name = "sell_amounts_year")
    @ApiModelProperty("核年销售总额")
    private BigDecimal sellAmountsYear;
    /**
     * 上年度总营收
     */
    @Column(name = "last_year_revenue")
    @ApiModelProperty("上年度总营收")
    private BigDecimal lastYearRevenue;
    /**
     * 上年度扣非营收
     */
    @Column(name = "rerevenue_exclude_manage")
    @ApiModelProperty("上年度扣非营收")
    private BigDecimal rerevenueExcludeManage;
    /**
     * 亏损状态
     */
    @Column(name = "loss_status")
    @ApiModelProperty("亏损状态")
    private Byte lossStatus;
    /**
     * 核实总营收
     */
    @Column(name = "total_amounts")
    @ApiModelProperty("核实总营收")
    private BigDecimal totalAmounts;
    /**
     * 企业负债率
     */
    @Column(name = "corporate_debt")
    @ApiModelProperty("企业负债率")
    private BigDecimal corporateDebt;
    /**
     * 开票
     */
    @Column(name = "invoice")
    @ApiModelProperty("开票")
    private String invoice;
    /**
     * 征信逾期
     */
    @Column(name = "credit_reporting_time")
    @ApiModelProperty("5年内征信逾期时长")
    private Integer creditReportingTime;
    /**
     * 法人变更时长
     */
    @Column(name = "corporation_change_time")
    @ApiModelProperty("法人变更时长")
    private Integer corporationChangeTime;
    /**
     * 增值税实际纳税
     */
    @Column(name = "vat_money")
    @ApiModelProperty("增值税实际纳税")
    private BigDecimal vatMoney;
    /**
     * 1年纳税评级：A级 B级 C级 D级 M级 以上
     */
    @Column(name = "nearly_one_year_tax_rating")
    @ApiModelProperty(value = "1年纳税评级：A级 B级 C级 D级 M级 以上", hidden = true)
    private String nearlyOneYearTaxRating;
    /**
     * 排除贷款五级分类 ：1 正常 2关注 3次贷 4 可疑 5 损失
     */
    @Column(name = "exclude_loan_category")
    @ApiModelProperty(value = "排除贷款五级分类 ：1 正常 2关注 3次贷 4 可疑 5 损失", hidden = true)
    private String excludeLoanCategory;
    /**
     * 排除征信报告状态 担保人代还 1 以资抵债 2 展期 3 结清 4 结束 5 还款情况未知
     */
    @Column(name = "exclude_credit_status")
    @ApiModelProperty(value = "排除征信报告状态 担保人代还 1 以资抵债 2 展期 3 结清 4 结束 5 还款情况未知", hidden = true)
    private String excludeCreditStatus;
    /**
     * 排除信用卡状态： 1 正常 2 冻结 3 止付 4 挂失 5 收卡 6 作废 7 呆账 8代偿
     */
    @Column(name = "exclude_credit_card_status")
    @ApiModelProperty(value = "排除信用卡状态： 1 正常 2 冻结 3 止付 4 挂失 5 收卡 6 作废 7 呆账 8代偿", hidden = true)
    private String excludeCreditCardStatus;

    /**
     * 限制项目用地： 1 工业用地 2 物流仓储用地 3 科教用地
     */
    @Column(name = "restricted_land")
    @ApiModelProperty(value = "限制项目用地： 1 工业用地 2 物流仓储用地 3 科教用地", hidden = true)
    private String restrictedLand;
    /**
     * 企业注册地
     */
    @Column(name = "business_registration_place")
    @ApiModelProperty(value = "企业注册地", hidden = true)
    private String businessRegistrationPlace;
    /**
     * 公民国籍
     */
    @Column(name = "citizen_international")
    @ApiModelProperty(value = "公民国籍", hidden = true)
    private String citizenInternational;

    /**
     * 网贷次数
     */
    @Column(name = "person_loans")
    @ApiModelProperty("网贷次数")
    private Integer personLoans;
    /**
     * 申请区域是否需要房产证
     */
    @Column(name = "is_hourse")
    @ApiModelProperty("申请区域是否需要房产证")
    private Boolean isHourse;

    /**
     * 是否有特殊行业经营资质
     */
    @Column(name = "is_special_manage")
    @ApiModelProperty("是否有特殊行业经营资质")
    private Boolean isSpecialManage;
    /**
     * 是否需要设备租赁标的物
     */
    @Column(name = "is_equipment_rent")
    @ApiModelProperty("是否需要设备租赁标的物")
    private Boolean isEquipmentRent;
    /**
     * 是否允许有质押的应收账款
     */
    @Column(name = "is_receivables")
    @ApiModelProperty("是否允许有质押的应收账款")
    private Boolean isReceivables;
    /**
     * 是否允许有刑事或民事纠纷
     */
    @Column(name = "is_criminal")
    @ApiModelProperty("是否允许有刑事或民事纠纷")
    private Boolean isCriminal;
    /**
     * 公司及法人名下净资产是否需大于申请额度
     */
    @Column(name = "is_superpass_amounts")
    @ApiModelProperty("公司及法人名下净资产是否需大于申请额度")
    private Boolean isSuperpassAmounts;

    /**
     * 是否逾期
     */
    @Column(name = "has_overdue")
    @ApiModelProperty("是否逾期")
    private Boolean hasOverdue;

    /**
     * 最大逾期时长
     */
    @Column(name = "max_overdue_time")
    @ApiModelProperty("最大逾期时长")
    private Integer maxOverdueTime;

    /**
     * 应收款总额
     */
    @Column(name = "receivables")
    @ApiModelProperty("应收款总额")
    private BigDecimal receivables;

    /**
     * 质押应收款
     */
    @Column(name = "whether_pledge_amount")
    @ApiModelProperty("质押应收款")
    private BigDecimal whetherPledgeAmount;

    /**
     * 其他
     */
    @Column(name = "other")
    @ApiModelProperty("其他")
    private String other;


}
