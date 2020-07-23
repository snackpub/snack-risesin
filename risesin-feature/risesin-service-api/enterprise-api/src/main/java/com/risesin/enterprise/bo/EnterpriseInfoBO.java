package com.risesin.enterprise.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 企业基本信息实体类
 *
 * @author Darling
 */

@Data
@ApiModel("企业基本信息")
public class EnterpriseInfoBO implements Serializable {

    /**
     * 融资主体名称
     */
    @ApiModelProperty("融资主体名称")
    private String name;

    /**
     * 公司注册地 1 中国大陆 2 中国香港 3 中国台湾 4 境外
     */
    @ApiModelProperty("公司注册地 1 中国大陆 2 中国香港 3 中国台湾 4 境外 ")
    private Byte regAddress;

    /**
     * 注册时间
     */
    @ApiModelProperty("注册时间")
    private LocalDateTime regTime;
    /**
     * 注册资本
     */
    @ApiModelProperty("注册资本")
    private BigDecimal regCapital;

    /**
     * 行业ID
     */
    @ApiModelProperty("行业ID")
    private Integer industryId;

    /**
     * 所属区域
     */
    @ApiModelProperty("所属区域")
    private String areaId;

    /**
     * 法人代表近期是否有变更 单位：月
     */
    @ApiModelProperty("法人代表变更:近(99)个月法人代表有变更。单位：月")
    private Short legalReprChange;
}
