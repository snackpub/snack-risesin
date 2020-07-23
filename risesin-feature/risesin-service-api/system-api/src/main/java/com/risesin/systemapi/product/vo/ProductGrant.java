package com.risesin.systemapi.product.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @AUTHOR Baby
 * @CREATE 2019/12/11
 * @DESCRIPTION 产品授权bean
 * @since 1.0.0
 */
@Data
@ApiModel("产品与授权VO")
public class ProductGrant {
    @ApiModelProperty("产品ids字符串")
    private String productIds;
    @ApiModelProperty("机构ids字符串")
    private String loanAgencyIds;
}
