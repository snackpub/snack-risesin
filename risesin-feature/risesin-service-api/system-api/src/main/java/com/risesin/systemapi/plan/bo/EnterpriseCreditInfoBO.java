package com.risesin.systemapi.plan.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * enterpriseCreditInfo实体类
 *
 * @author Administrator
 */

@Data
public class EnterpriseCreditInfoBO implements Serializable {

    /**
     * 是否有以下贷款分类 1 关注 2 次贷 3 可疑 4 损失
     */
    @ApiModelProperty("是否有以下贷款分类 1 关注 2 次贷 3 可疑 4 损失")
    private Byte loanClassification;

    /**
     * 征信报告是否有以下状态：1 担保人代还 2 以资抵债 3 展期 4 还款情况未知
     */
    @ApiModelProperty("征信报告是否有以下状态：1 担保人代还 2 以资抵债 3 展期 4 还款情况未知")
    private Byte creditReportStatus;

    /**
     * 5年内逾期次数
     */
    @ApiModelProperty("5年内逾期次数")
    private Integer creditDueTimes5Years;


    /**
     * 最长征信逾期时长
     */
    @ApiModelProperty("最长征信逾期时长")
    private Integer longestCreditOverdue;


    /**
     * 当前是否逾期：1 逾期 0 未逾期
     */
    @ApiModelProperty("当前是否逾期")
    private Boolean currOver;

    /**
     * 信用卡是否有以下状态: 1 正常 2 冻结 3 止付 4 呆账 5 代偿
     */
    @ApiModelProperty("信用卡是否有以下状态: 1 正常 2 冻结 3 止付 4 呆账 5 代偿")
    private Byte creditCardStatus;
}
