package com.risesin.systemapi.plan.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * financingDemand实体类
 *
 * @author Administrator
 */
@Data
@Entity
@ApiModel("融资需求对象")
@Table(name = "t_financing_demand")
@DynamicInsert
public class FinancingDemand implements Serializable {

    @Id
    @ApiModelProperty("主键")
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("融资金额,单位:万元")
    @Column(name = "financing_amount")
    private BigDecimal financingAmount;

    @ApiModelProperty("借款周期")
    @Column(name = "loan_period")
    private Integer loanPeriod;

    @Column(name = "exclude_financing_type")
    @ApiModelProperty("排除融资方式")
    private String excludeFinancingType;

    @ApiModelProperty("是否上征信：1 否 0 是")
    @Column(name = "is_enable_credit")
    private Boolean enableCredit;

}
