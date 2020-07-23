package com.risesin.systemapi.plan.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 排除融资方式实体类
 *
 * @author Administrator
 */
@Data
@Entity
@Table(name = "t_exclude_financing_type")
public class ExcludeFinancingType implements Serializable {


    /**
     * 主键
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 融资类型ID
     */
    @Column(name = "fk_financing_type_id")
    private Integer financingTypeId;
    /**
     * 融资预案ID
     */
    @Column(name = "fk_financing_plan_id")
    private Integer financingPlanId;


}
