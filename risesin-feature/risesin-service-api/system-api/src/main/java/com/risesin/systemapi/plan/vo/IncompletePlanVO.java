package com.risesin.systemapi.plan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @AUTHOR Darling
 * @CREATE 2019-11-30
 * @DESCRIPTION 未完成的融资方案(C端展示)
 * @since 1.0.0
 */
@Data
@ApiModel("未完成的融资方案(C端展示)")
public class IncompletePlanVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Integer id;

    /**
     * 所属公司
     */
    @ApiModelProperty("所属公司")
    private String companyName;

    /**
     * 执行方案名称
     */
    @ApiModelProperty("执行方案名称")
    private String planName;

    /**
     * 融资金额
     */
    @ApiModelProperty("融资金额")
    private BigDecimal financingAmount;

    /**
     * 状态：申请(0);进行中(1);已完成(2);已关闭(3)
     */
    @ApiModelProperty("状态：申请(0);进行中(1);已完成(2);已关闭(3)")
    private byte flow;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
