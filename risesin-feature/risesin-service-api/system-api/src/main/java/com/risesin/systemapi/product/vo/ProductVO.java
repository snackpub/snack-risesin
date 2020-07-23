package com.risesin.systemapi.product.vo;

import com.risesin.systemapi.dict.entity.DataTemplate;
import com.risesin.systemapi.product.entity.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @AUTHOR Baby
 * @CREATE 2019/11/29
 * @DESCRIPTION 产品vo
 * @since 1.0.0
 */
@Data
@ApiModel("产品VO")
public class ProductVO extends Product {
    @ApiModelProperty("准入条件VO")
    private ProductApplyConditionVO productApplyConditionVo;
    @ApiModelProperty("收费项list")
    private List<String> chargeItemList;
    @ApiModelProperty("资料模板List")
    private List<DataTemplate> dataTemplateList;
    /**
     * 融资方式
     */
    @ApiModelProperty("融资方式")
    private List<String> financingMethod;
}
