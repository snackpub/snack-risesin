package com.risesin.systemapi.plan.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * riskCheckResult实体类
 *
 * @author Administrator
 */
@Data
@Entity
@ApiModel("风控审核结果对象")
@Table(name = "t_risk_check_result")
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class RiskCheckResult implements Serializable {

    /**
     * 主键
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;

    /**
     * 审核状态 0:拒绝；1:通过
     */
    @Column(name = "audit_status")
    @ApiModelProperty("审核状态 0:拒绝；1:通过")
    private Byte auditStatus;

    /**
     * 审核意见
     */
    @Column(name = "audit_opinion")
    @ApiModelProperty("审核意见")
    private String auditOpinion;

    /**
     * 子方案code
     */
    @Column(name = "child_plan_code")
    @ApiModelProperty("子方案code")
    private Integer childPlanCode;

    /**
     * 资料审核人员（平台用户）
     */
    @Column(name = "fk_sys_user_id")
    @ApiModelProperty("资料审核人员（平台用户）")
    private String sysUserId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty("创建时间")
    @CreatedDate
    private LocalDateTime createTime;

}
