package com.risesin.systemapi.plan.vo;

import com.risesin.systemapi.plan.bo.AdditionalCustomerMaterialsBO;
import com.risesin.systemapi.plan.entity.ActionPlan;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * actionPlan视图实体类
 *
 * @author honey
 * @date 2019/12/17
 */
@Data
@Builder
@ApiModel("执行方案与客户资料视图实体对象")
public class ActionPlanCustomInfoVO implements Serializable {

    private ActionPlan actionPlan;
    private AdditionalCustomerMaterialsBO additionalCustomerMaterials;


}
