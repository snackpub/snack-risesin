package com.risesin.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * enterpriseUser实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_enterprise_user")
@Data
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@ApiModel("企业用户")
public class EnterpriseUser implements Serializable {

    @Id
    @Column(name = "pk_id")
    @ApiModelProperty("主键")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name")
    @ApiModelProperty("真实姓名")
    private String fullName;

    @Column(name = "phone")
    @ApiModelProperty("手机号")
    private String phone;

    @Column(name = "create_time")
    @ApiModelProperty("创建时间")
    @CreatedDate
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @ApiModelProperty("修改时间")
    @LastModifiedDate
    private LocalDateTime updateTime;

    @Column(name = "industry_code")
    @ApiModelProperty("行业code")
    private String industry;

    @Column(name = "source")
    @ApiModelProperty("客户来源")
    private String source;

    @Column(name = "company_name")
    @ApiModelProperty("工作单位")
    private String companyName;

    @Column(name = "contact_number")
    @ApiModelProperty("联系电话")
    private String contactNumber;

    @Column(name = "area")
    @ApiModelProperty("地区code")
    private String area;

    @Column(name = "address")
    @ApiModelProperty("街道地址")
    private String address;

    @Column(name = "create_user")
    @ApiModelProperty("创建人")
    private String createUser;

    @Column(name = "status")
    @ApiModelProperty("状态： -1 不启用 0 启用 1 加入黑名单 2注销（删除）")
    private Byte status;

    @Column(name = "sort")
    @ApiModelProperty("排序")
    private Integer sort;

    @Column(name = "fax")
    @ApiModelProperty("传真")
    private String fax;

}
