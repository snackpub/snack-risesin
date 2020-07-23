package com.risesin.systemapi.product.vo;

import com.risesin.systemapi.product.entity.ProductApplyCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @AUTHOR Baby
 * @CREATE 2019/11/29
 * @DESCRIPTION 申请条件vo类
 * @since 1.0.0
 */
@Data
@ApiModel("准入条件VO")
public class ProductApplyConditionVO extends ProductApplyCondition {

    @ApiModelProperty("排除行业List")
    private List<String> excludeIndustryList;
    @ApiModelProperty("可选地区List")
    private List<String> areaList;
    @ApiModelProperty("使用职业List")
    private List<String> applicableCareerList;
    /**
     * 1年纳税评级：A级 B级 C级 D级 M级 以上
     */
    @ApiModelProperty(value = "1年纳税评级：A级 B级 C级 D级 M级 以上")
    private List<String> nearlyOneYearTaxRatingList;
    /**
     * 排除贷款五级分类 ：1 正常 2关注 3次贷 4 可疑 5 损失
     */
    @ApiModelProperty(value = "排除贷款五级分类 ：1 正常 2关注 3次贷 4 可疑 5 损失")
    private List<String> excludeLoanCategoryList;
    /**
     * 排除征信报告状态 担保人代还 1 以资抵债 2 展期 3 结清 4 结束 5 还款情况未知
     */
    @ApiModelProperty(value = "排除征信报告状态 担保人代还 1 以资抵债 2 展期 3 结清 4 结束 5 还款情况未知")
    private List<String> excludeCreditStatusList;
    /**
     * 排除信用卡状态： 1 正常 2 冻结 3 止付 4 挂失 5 收卡 6 作废 7 呆账 8代偿
     */
    @ApiModelProperty(value = "排除信用卡状态： 1 正常 2 冻结 3 止付 4 挂失 5 收卡 6 作废 7 呆账 8代偿")
    private List<String> excludeCreditCardStatusList;

    /**
     * 限制项目用地： 1 工业用地 2 物流仓储用地 3 科教用地
     */
    @ApiModelProperty(value = "限制项目用地： 1 工业用地 2 物流仓储用地 3 科教用地")
    private List<String> restrictedLandList;
    /**
     * 企业注册地
     */
    @ApiModelProperty(value = "企业注册地")
    private List<String> businessRegistrationPlaceList;
    /**
     * 公民国籍
     */
    @ApiModelProperty(value = "公民国籍")
    private List<String> citizenInternationalList;


}
