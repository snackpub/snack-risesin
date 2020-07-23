package com.risesin.systemapi.plan.vo;

import com.risesin.systemapi.plan.entity.EnterpriseAssetInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-13
 * @DESCRIPTION 企业资产VO
 * @since 1.0.0
 */
@Data
@ApiModel("企业资产VO")
public class EnterpriseAssetInfoVO extends EnterpriseAssetInfo {

    @ApiModelProperty("项目用地List")
    private List<String> projectSiteList;

}
