package com.risesin.systemapi.dict.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @AUTHOR Baby
 * @CREATE 2019/12/2
 * @DESCRIPTION id和状态类
 * @since 1.0.0
 */
@Data
@ApiModel(value = "主键与状态VO")
public class IdAndStatusVO {

    @ApiModelProperty("id")
    private Object id;
    @ApiModelProperty("status")
    private Byte status;
}
