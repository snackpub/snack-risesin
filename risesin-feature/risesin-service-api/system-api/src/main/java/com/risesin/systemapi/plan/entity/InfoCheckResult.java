package com.risesin.systemapi.plan.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * infoCheckResult实体类
 *
 * @author Administrator
 */
@Data
@Entity
@ApiModel("资料信息审核结果对象")
@Table(name = "t_info_check_result")
public class InfoCheckResult implements Serializable {


    /**
     * 主键
     */
    @Id
    @Column(name = "Pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 审核结果 0:拒绝；1:通过
     */
    @Column(name = "is_pass")
    private Byte isPass;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;
    /**
     * 子方案ID
     */
    @Column(name = "fk_child_plan_id")
    private Integer fkChildPlanId;
    /**
     * 资料审核人员（平台用户）
     */
    @Column(name = "fk_sys_user_id")
    private String fkSysUserId;


}
