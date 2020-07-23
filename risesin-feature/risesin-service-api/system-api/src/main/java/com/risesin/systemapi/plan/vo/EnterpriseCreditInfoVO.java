package com.risesin.systemapi.plan.vo;

import com.risesin.systemapi.plan.entity.EnterpriseCreditInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-13
 * @DESCRIPTION 企业征信VO
 * @since 1.0.0
 */
@Data
@ApiModel("企业征信VO")
public class EnterpriseCreditInfoVO extends EnterpriseCreditInfo {

    @ApiModelProperty("是否有以下贷款分类 1 关注 2 次贷 3 可疑 4 损失(多选框)")
    private List<String> loanClassificationList;

    @ApiModelProperty("征信报告是否有以下状态 1 担保人代还 2 以资抵债 3 展期 4 还款情况未知(多选框)")
    private List<String> creditReportStatusList;

    @ApiModelProperty("信用卡是否有以下状态: 1 冻结 2 止付 3 呆账 4 代偿(多选框)")
    private List<String> creditCardStatusList;

}
