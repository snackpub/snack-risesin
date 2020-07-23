package com.risesin.systemapi.flowlog.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * todotask实体类
 *
 * @author Administrator
 */
@Data
@Entity
@ApiModel("任务流转对象")
@Table(name = "t_todo_task")
public class TodoTask implements Serializable {


    /**
     * 主键
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 子方案ID
     */
    @Column(name = "fk_child_plan_id")
    private Integer childPlanId;
    /**
     * 事项类型：1 支付前置收费项 2 支付咨询费 3 完善资料
     */
    @Column(name = "task_type")
    private Byte taskType;
    /**
     * 处理状态：0：未处理；1 已处理
     */
    @Column(name = "state")
    private Byte state;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;
    /**
     * 处理时间
     */
    @Column(name = "handle_time")
    private LocalDateTime handleTime;


}
