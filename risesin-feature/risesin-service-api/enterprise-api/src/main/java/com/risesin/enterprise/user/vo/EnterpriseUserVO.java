package com.risesin.enterprise.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @AUTHOR Darling
 * @CREATE 2019-11-25
 * @DESCRIPTION EnterpriseUserVO 企业用户VO
 * @since 1.0.0
 */
@Data
@ApiModel("企业用户VO")
public class EnterpriseUserVO {

    /**
     * 主键ID
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 手机号
     */
    @ApiModelProperty("phone")
    private String phone;

    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    private String fullName;

    /**
     * 行业ID
     */
    @ApiModelProperty("行业ID")
    private Integer industryId;

    /**
     * 工作单位
     */
    @ApiModelProperty("工作单位")
    private String companyName;

    /**
     * 所在地区
     */
    @ApiModelProperty("所在地区")
    private String area;

    /**
     * 街道地址
     */
    @ApiModelProperty("街道地址")
    private String address;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String contactNumber;

    /**
     * 传真
     */
    @ApiModelProperty("传真")
    private Integer fax;


}
