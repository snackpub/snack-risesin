package com.risesin.systemapi.dict.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @AUTHOR Darling
 * @CREATE 2019-10-22
 * @DESCRIPTION AreaVO
 * @since 1.0.0
 */
@Data
@ApiModel("省市区VO")
public class AreaVO implements Serializable {

    /**
     * parentCode
     */
    @ApiModelProperty("code")
    private String areaCode;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;
}
