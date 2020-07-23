package com.risesin.agent.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * comOrderCheckResult实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_com_order_check_result")
@Data
public class ComOrderCheckResult implements Serializable {


    /**
     * 主键
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 审核意见
     */
    @Column(name = "reason")
    private String reason;
    /**
     * 通用用户ID
     */
    @Column(name = "fk_com_user_id")
    private String fkComUserId;
    /**
     * 审核结果 0:拒绝；1:通过
     */
    @Column(name = "is_pass")
    private Boolean isPass;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;
    /**
     * 收款订单id
     */
    @Column(name = "fk_com_order_id")
    private Integer fkComOrderId;


}
