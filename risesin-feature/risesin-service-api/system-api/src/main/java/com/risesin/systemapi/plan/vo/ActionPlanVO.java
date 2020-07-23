package com.risesin.systemapi.plan.vo;

import com.risesin.systemapi.plan.entity.ActionPlan;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * actionPlan视图实体类
 *
 * @author honey
 * @date 2019/12/5
 */
@Data
@ApiModel("执行方案视图实体对象")
@EqualsAndHashCode(callSuper = true)
public class ActionPlanVO extends ActionPlan implements Serializable {

    /**
     * 企业用户
     */
    private String entUserName;

    /**
     * 创建人
     */
    private String createUserName;

    /**
     * 流程状态
     */
    private String statusName;

}
