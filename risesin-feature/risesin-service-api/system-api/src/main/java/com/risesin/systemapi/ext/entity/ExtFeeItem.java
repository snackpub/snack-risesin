package com.risesin.systemapi.ext.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * extFeeItem实体类
 *
 * @author Administrator
 */
@Data
@Entity
@ApiModel("收费项拓展实体")
@Table(name = "t_ext_fee_item")
public class ExtFeeItem implements Serializable {


    /**
     * 主键
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 费用项金额
     */
    @Column(name = "item_money")
    private BigDecimal itemMoney;
    /**
     * 子方案CODE
     */
    @Column(name = "child_plan_code")
    private String childPlanCode;
    /**
     * 收费项ID（来自字典表）
     */
    @Column(name = "fk_dict_id")
    private Integer fkDictId;


}
