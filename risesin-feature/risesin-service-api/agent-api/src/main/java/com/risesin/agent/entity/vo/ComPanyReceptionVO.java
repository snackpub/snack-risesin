package com.risesin.agent.entity.vo;

import com.risesin.agent.entity.ComPanyReception;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

/**
 * @AUTHOR Baby
 * @CREATE 2019/12/4
 * @DESCRIPTION 回执详情VO
 * @since 1.0.0
 */
@Data
@ApiModel("回执详情VO")
public class ComPanyReceptionVO extends ComPanyReception {
    /**
     * 方案名称
     */
    @Column(name = "child_plan_name")
    @ApiModelProperty(value = "方案编号")
    private String childPlanName;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "未定")
    private String payMethodName;
}
