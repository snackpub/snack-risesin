package com.risesin.agent.entity.feign;

import com.risesin.agent.entity.feign.vo.ActionChildPlan;
import com.risesin.agent.entity.feign.vo.ActionChildPlanVO;
import com.risesin.agent.entity.feign.vo.ExtFileData;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @AUTHOR Baby
 * @CREATE 2019/11/27
 * @DESCRIPTION 方案管理回滚
 * @since 1.0.0
 */
@Component
public class PlanServiceFeignFallback implements IPlanServiceFeign {
    @Override
    public R<PageResult> findSearch(Map searchMap, Query query) {
        return R.fail(ResultCode.FAILURE);
    }

    @Override
    public R<ActionChildPlanVO> findByChildPlanCode(String childPlanCode) {
        return R.fail("查询失败");
    }

    @Override
    public R<List<ExtFileData>> dataVerification(String code) {
        return R.fail("查询失败");
    }

    @Override
    public R<String> updateFlowByCode(ActionChildPlan actionChildPlan) {
        return R.fail("更新失败");
    }

    @Override
    public R<String> update(ActionChildPlanVO actionChildPlanVO) {
        return R.fail("更新失败");
    }
}
