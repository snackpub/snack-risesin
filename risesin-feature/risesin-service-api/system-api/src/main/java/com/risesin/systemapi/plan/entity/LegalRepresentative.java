package com.risesin.systemapi.plan.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 法人代表信息实体
 *
 * @author Administrator
 */
@Data
@Entity
@ApiModel("法人代表信息实体")
@Table(name = "t_legal_representative")
@DynamicInsert
public class LegalRepresentative implements Serializable {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @ApiModelProperty("法人代表姓名")
    private String name;

    @ApiModelProperty("国籍")
    @Column(name = "nationality")
    private String nationality;

    @Column(name = "id_number")
    @ApiModelProperty("法人代表身份证号")
    private String idNumber;

    @Column(name = "sharerate")
    @ApiModelProperty("股份所占比例")
    private BigDecimal sharerate;

    @Column(name = "occupation")
    @ApiModelProperty("法人代表职业：1 公务员或企事业单位 2 世界五百强企业 3 其他")
    private String occupation;

    @Column(name = "age")
    @ApiModelProperty("年龄")
    private Byte age;

    @ApiModelProperty("配偶姓名")
    @Column(name = "spouse_name")
    private String spouseName;

    @ApiModelProperty("配偶身份证号")
    @Column(name = "spouse_id_number")
    private String spouseIdNumber;

}
