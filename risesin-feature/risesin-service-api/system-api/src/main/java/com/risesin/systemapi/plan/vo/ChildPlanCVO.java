package com.risesin.systemapi.plan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-01
 * @DESCRIPTION 子方案信息（C端展示）
 * @since 1.0.0
 */
@Data
@ApiModel("子方案信息（C端展示）")
public class ChildPlanCVO {


    /**
     * 编号
     */
    @ApiModelProperty("编号")
    private String childPlanCode;

    /**
     * 所属公司
     */
    @ApiModelProperty("所属公司")
    private String companyName;

    /**
     * 执行子方案名称
     */
    @ApiModelProperty("执行子方案名称")
    private String childPlanName;

    /**
     * 融资金额
     */
    @ApiModelProperty("子方案融资金额")
    private BigDecimal financingAmount;

    /**
     * 状态：申请(1);创建子方案(2);完善资料(3);资料审核(4);风控审核(5);预交费中(6);资方审批额度(7);额度确认中(8);签订合同(9);放款(10);尾款缴纳中(11);结束(12);关闭(13)
     */
    @ApiModelProperty("状态：申请(1);创建子方案(2);完善资料(3);资料审核(4);风控审核(5);预交费中(6);资方审批额度(7);额度确认中(8);签订合同(9);放款(10);尾款缴纳中(11);结束(12);关闭(13)")
    private byte state;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}
