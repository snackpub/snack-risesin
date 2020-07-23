package com.risesin.systemapi.dict.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @AUTHOR Darling
 * @CREATE 2019-10-22
 * @DESCRIPTION DistrictBO
 * @since 1.0.0
 */
@Data
@ApiModel("省市区BO")
public class AreaBO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty("id")
    @NotBlank(message = "id不能为空")
    private String areaId;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    @NotBlank(message = "名称不能为空")
    private String areaName;

    /**
     * 父id
     */
    @ApiModelProperty("父节点id")
    @NotBlank(message = "父节点不能为空")
    private String areaPid;

}
