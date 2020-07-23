package com.risesin.systemapi.ext.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * extValueData实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_ext_value_data")
@Data
public class ExtValueData implements Serializable {


    /**
     * 主键ID
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 子方案ID
     */
    @Column(name = "fk_child_plan_id")
    private Integer fkChildPlanId;
    /**
     * 资料模板ID
     */
    @Column(name = "fk_data_template_id")
    private Integer fkDataTemplateId;
    /**
     * 输入框，单选框，复选框等等的值，全部转化为varchar类型进行存储。
     */
    @Column(name = "value")
    private String value;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;
    /**
     * 状态：审核中；初审未通过；初审通过。
     */
    @Column(name = "status")
    private Byte status;


}
