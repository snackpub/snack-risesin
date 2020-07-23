package com.risesin.systemapi.plan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @AUTHOR Baby
 * @CREATE 2019/12/6
 * @DESCRIPTION 利息和本金VO类
 * @since 1.0.0
 */
@Data
@ApiModel("利息和本金VO")
public class InterestAndCapitalVO {
    /**
     * 利息
     */
    @ApiModelProperty("利息")
    private String interest;
    /**
     * 本金
     */
    @ApiModelProperty("本金")
    private String capital;
    /**
     * 利息和本金
     */
    @ApiModelProperty("利息和本金")
    private String intAndCap;
}
