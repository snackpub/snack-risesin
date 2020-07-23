package com.risesin.systemapi.plan.entity;

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
 * 企业征信信息实体类
 *
 * @author Administrator
 */
@Data
@Entity
@ApiModel("企业征信信息实体")
@Table(name = "t_enterprise_credit_info")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class EnterpriseCreditInfo implements Serializable {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "loan_classification")
    @ApiModelProperty("是否有以下贷款分类 1 关注 2 次贷 3 可疑 4 损失(多选框)")
    private String loanClassification;

    @Column(name = "credit_report_status")
    @ApiModelProperty("征信报告是否有以下状态 1 担保人代还 2 以资抵债 3 展期 4 还款情况未知(多选框)")
    private String creditReportStatus;

    @Column(name = "credit_due_times5_years")
    @ApiModelProperty("5年内逾期次数")
    private Integer creditDueTimes5Years;

    @Column(name = "credit_overdue5_years")
    @ApiModelProperty("5年内征信逾期时长（天）")
    private Integer creditOverdue5Years;

    @Column(name = "overdue_amount")
    @ApiModelProperty("征信总逾期金额")
    private BigDecimal overdueAmount;

    @ApiModelProperty("最长征信逾期天数")
    @Column(name = "longest_credit_overdue")
    private Integer longestCreditOverdue;

    @ApiModelProperty("征信逾期次数")
    @Column(name = "credit_num_over")
    private Integer creditNumOver;

    @Column(name = "credit_card_status")
    @ApiModelProperty("信用卡是否有以下状态: 1 冻结 2 止付 3 呆账 4 代偿(多选框)")
    private String creditCardStatus;

    @Column(name = "credit_card_days_past_due")
    @ApiModelProperty("信用卡逾期天数:最长逾期(99)天")
    private Integer creditCardDaysPastDue;

    @ApiModelProperty("信用卡逾期次数")
    @Column(name = "credit_card_deli")
    private Integer creditCardDeli;

    @Column(name = "is_curr_over")
    @ApiModelProperty("当前是否逾期：1 逾期 0 未逾期")
    private Boolean currOver;

    @CreatedDate
    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @LastModifiedDate
    @ApiModelProperty("修改时间")
    @Column(name = "update_time")
    private LocalDateTime updateTime;

}
