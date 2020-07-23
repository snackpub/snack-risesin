package com.risesin.systemapi.plan.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * actionPlan实体类
 *
 * @author Administrator
 */
@Data
@Entity
@ApiModel("执行方案实体")
@Table(name = "t_action_plan")
@EntityListeners(AuditingEntityListener.class)
public class ActionPlan implements Serializable {


    /**
     * 主键
     */
    @Id
    @Column(name = "pk_id")
    @ApiModelProperty("主键")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 执行方案编号
     */
    @Column(name = "plan_code")
    @ApiModelProperty("执行方案编号")
    private String planCode;
    /**
     * 执行方案名称
     */
    @Column(name = "plan_name")
    @ApiModelProperty("执行方案名称")
    private String planName;
    /**
     * 流程状态：申请(0);进行中(1);已完成(2);已关闭(3)
     */
    @Column(name = "flow")
    @ApiModelProperty("流程状态：申请(0);进行中(1);已完成(2);已关闭(3)")
    private Byte flow;
    /**
     * 逻辑状态: 0 启用(正常)  2注销（删除）
     */
    @Column(name = "status")
    @ApiModelProperty("逻辑状态: 0 启用(正常)  2注销（删除）")
    private Byte status;
    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "create_time")
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    /**
     * 最后修改时间
     */
    @LastModifiedDate
    @Column(name = "update_time")
    @ApiModelProperty("最后修改时间")
    private LocalDateTime updateTime;
    /**
     * 外键：企业用户ID
     */
    @Column(name = "fk_ent_user_id")
    @ApiModelProperty("外键：企业用户ID")
    private String entUserId;
    /**
     * 创建人ID(平台用户)
     */
    @Column(name = "create_user")
    @ApiModelProperty("创建人ID(平台用户)")
    @CreatedBy
    private String createUser;

    /**
     * 融资预案ID
     */
    @ApiModelProperty("融资预案ID")
    @Column(name = "fiancing_plan_id")
    private Integer fiancingPlanId;

    /**
     * 融资主体(冗余字段->t_enterprise_info)
     */
    @ApiModelProperty("融资主体")
    @Column(name = "company_name")
    private String companyName;

    /**
     * 融资金额(冗余字段->t_financing_demand)
     */
    @ApiModelProperty("融资金额")
    @Column(name = "financing_amount")
    private BigDecimal financingAmount;

    /**
     * 企业法人(冗余字段->t_leagl_representative)
     */
    @ApiModelProperty("法人代表")
    @Column(name = "legal_name")
    private String legalName;

}
