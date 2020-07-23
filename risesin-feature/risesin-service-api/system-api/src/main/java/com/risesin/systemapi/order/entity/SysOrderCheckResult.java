package com.risesin.systemapi.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * sysOrderCheckResult实体类
 *
 * @author Administrator
 */
@Data
@Entity
@ApiModel("订单审核结果表")
@Table(name = "t_sys_order_check_result")
@EntityListeners(AuditingEntityListener.class)
public class SysOrderCheckResult implements Serializable {


    /**
     * 主键
     */
    @Id
    @Column(name = "pk_id")
    @ApiModelProperty("主键")
    private String id;


    /**
     * 审核意见
     */
    @Column(name = "reason")
    @ApiModelProperty("审核意见")
    private String reason;
    /**
     * 通过结果：-1 拒绝 0 通过
     */
    @Column(name = "is_pass")
    @ApiModelProperty("通过结果：-1 拒绝 0 通过")
    private Byte isPass;
    /**
     * 收款订单ID
     */
    @ApiModelProperty("收款订单ID")
    @Column(name = "fk_sys_order_id")
    private String sysOrderId;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @CreatedDate
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 审核人
     */
    @ApiModelProperty("审核人")
    @Column(name = "fk_sys_user")
    @CreatedBy
    private String approvingPerson;

}
