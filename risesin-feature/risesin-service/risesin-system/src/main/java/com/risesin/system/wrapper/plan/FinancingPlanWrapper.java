package com.risesin.system.wrapper.plan;

import com.risesin.core.jpaplus.support.BaseEntityWrapper;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.core.tool.utils.SpringUtil;
import com.risesin.system.service.plan.impl.ActionPlanServiceImpl;
import com.risesin.systemapi.feign.IDictClient;
import com.risesin.systemapi.plan.bo.AdditionalCustomerMaterialsBO;
import com.risesin.systemapi.plan.entity.FinancingPlan;
import com.risesin.systemapi.plan.vo.FinancingPlanVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 视图包装类
 *
 * @author honey
 * @date 2019/12/10
 */
public class FinancingPlanWrapper extends BaseEntityWrapper<FinancingPlan, FinancingPlanVO> {


    private static IDictClient dictClient;
    private static ActionPlanServiceImpl actionPlanService;

    static {
        dictClient = SpringUtil.getBean(IDictClient.class);
        actionPlanService = SpringUtil.getBean(ActionPlanServiceImpl.class);
    }

    public static FinancingPlanWrapper build() {
        return new FinancingPlanWrapper();
    }


    @Override
    public FinancingPlanVO entityVO(FinancingPlan entity) {
        FinancingPlanVO planVO = BeanUtil.copy(entity, FinancingPlanVO.class);
        R<String> d1 = dictClient.getValue("is_advisory", entity.getStatus().intValue());
        if (d1.isSuccess()) {
            planVO.setStatusName(d1.getData());
        }
        AdditionalCustomerMaterialsBO customerMaterials = actionPlanService.additionalCustomerMaterials(entity.getFinancingCode());
        planVO.setCustomerMaterialsBO(customerMaterials);
        return planVO;
    }


    @Override
    public List<FinancingPlanVO> listVO(List<FinancingPlan> list) {
        return list.stream().map(this::entityVO).collect(Collectors.toList());
    }
}
