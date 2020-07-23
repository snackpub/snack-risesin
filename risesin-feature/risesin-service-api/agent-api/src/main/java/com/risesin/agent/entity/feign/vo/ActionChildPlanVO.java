package com.risesin.agent.entity.feign.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * @author honey
 * @date 2019/12/5 14:00
 **/
@EqualsAndHashCode(callSuper = true)
@ApiModel("子执行方案VO")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActionChildPlanVO extends ActionChildPlan {

    @ApiModelProperty("其他收费")
    private List<Map<String,String>> otherChargesVo;

    @ApiModelProperty("利息和本金")
    private Map<String,Number> calculateInterestMap;
}
