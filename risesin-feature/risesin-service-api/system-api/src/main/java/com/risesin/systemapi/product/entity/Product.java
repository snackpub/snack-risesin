package com.risesin.systemapi.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
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
 * product实体类
 *
 * @author Administrator
 */
@Data
@Entity
@ApiModel("产品")
@DynamicInsert
@Table(name = "t_product")
@EntityListeners(AuditingEntityListener.class)
public class Product implements Serializable {

    /**
     * 主键
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键")
    private Integer id;

    /**
     * 准入条件
     */
    @Column(name = "fk_application_condition")
    @ApiModelProperty(value = " 准入条件id", hidden = true)
    @JsonIgnore
    private String fk_application_condition;

    /**
     * 产品名称
     */
    @ApiModelProperty("产品名称")
    @Column(name = "product_name")
    private String productName;
    /**
     * (参考)利率，并不作为最终子方案的利率，执行方案生成人员可根据此利率上下浮动进行定价。
     */
    @Column(name = "interest_rate")
    @ApiModelProperty("(参考)利率，并不作为最终子方案的利率，执行方案生成人员可根据此利率上下浮动进行定价。")
    private BigDecimal interestRate;

    /**
     * 融资方式
     */
    @ApiModelProperty(value = "融资方式", hidden = true)
    @Column(name = "financing_method_json")
    private String financingMethodJson;

    /**
     * 产品描述
     */
    @ApiModelProperty("产品描述")
    @Column(name = "product_describe")
    private String productDescribe;
    /**
     * 是否上征信
     */
    @ApiModelProperty("主键")
    @Column(name = "is_credit_investigation")
    private Boolean isCreditInvestigation;
    /**
     * 审批最小额度(万)
     */
    @ApiModelProperty("审批最小额度(万)")
    @Column(name = "approval_amount_min")
    private Integer approvalAmountMin;
    /**
     * 审批最大额度(万)
     */
    @ApiModelProperty("审批最大额度(万)")
    @Column(name = "approval_amount_max")
    private BigDecimal approvalAmountMax;
    /**
     * 借款最小周期(月)
     */
    @ApiModelProperty("借款最小周期(月)")
    @Column(name = "loan_period_min")
    private Integer loanPeriodMin;
    /**
     * 其他借款周期(月)
     */
    @ApiModelProperty("其他借款周期(月)")
    @Column(name = "loan_period_other")
    private Integer loanPeriodOther;
    /**
     * 借款最大周期(月)
     */
    @ApiModelProperty("借款最大周期(月)")
    @Column(name = "loan_period_max")
    private Integer loanPeriodMax;
    /**
     * 申请模板资料
     */
    @Column(name = "apply_template_data")
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private String applyTemplateData;
    /**
     * 收费标准
     */
    @ApiModelProperty("收费标准")
    @Column(name = "charge_standard")
    private String chargeStandard;

    /**
     * 收费项
     */
    @ApiModelProperty(value = "收费项", hidden = true)
    @JsonIgnore
    @Column(name = "charge_items")
    private String chargeItems;


    /**
     * 添加时间
     */
    @ApiModelProperty("添加时间")
    @Column(name = "create_time")
    @CreatedDate
    private LocalDateTime createTime;
    /**
     * 产品上下架状态：-1下架 0 启用 2注销（删除）
     */
    @ApiModelProperty("产品上下架状态：-1下架 0 启用 2注销(删除)")
    @Column(name = "status")
    private Byte status;
    /**
     * 产品类型(1.短期 2.中期 3.长期)
     */
    @ApiModelProperty("产品类型(1.短期 2.中期 3.长期)")
    @Column(name = "type")
    private Byte type;

}
