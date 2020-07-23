package com.risesin.systemapi.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * comLoanAgency实体类
 *
 * @author Administrator
 */
@Data
@Entity
@DynamicInsert
@Table(name = "t_sys_loan_agency")
@EntityListeners(AuditingEntityListener.class)
@ApiModel(value = "助贷机构实体", description = "助贷机构实体")
public class SysLoanAgency implements Serializable {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 公司代码
     */
    @Column(name = "org_code")
    @ApiModelProperty(value = "公司代码")
    private String orgCode;
    /**
     * 公司名称
     */
    @Column(name = "name")
    @ApiModelProperty(value = "公司名称 ")
    private String name;
    /**
     * 区域
     */
    @Column(name = "area")
    @ApiModelProperty(value = "区域")
    private String area;
    /**
     * 注册资本
     */
    @Column(name = "reg_capital")
    @ApiModelProperty(value = "注册资本")
    private BigDecimal regCapital;
    /**
     * 公司注册时间
     */
    @Column(name = "reg_time")
    @ApiModelProperty(value = "公司注册时间")
    private String regTime;
    /**
     * 公司地址
     */
    @Column(name = "address")
    @ApiModelProperty(value = "公司地址")
    private String address;
    /**
     * 排序
     */
    @Column(name = "sort")
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @CreatedDate
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @Column(name = "update_time")
    @LastModifiedDate
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;
    /**
     * 负责人
     */
    @Column(name = "leader")
    @ApiModelProperty(value = "负责人")
    private String leader;
    /**
     * 联系电话
     */
    @Column(name = "phone")
    @ApiModelProperty(value = "联系电话")
    private String phone;
    /**
     * 创建人
     */
    @Column(name = "create_user")
    @ApiModelProperty(value = "创建人")
    private String createUser;
    /**
     * 状态
     */
    @Column(name = "status")
    @ApiModelProperty(value = "状态： -1 不启用 0 启用 1 加入黑名单 2注销（删除）")
    private Byte status;
    /**
     * 描述
     */
    @Column(name = "description")
    @ApiModelProperty(value = "描述")
    private String description;


}
