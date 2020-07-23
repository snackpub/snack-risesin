package com.risesin.agent.entity.feign;

import com.risesin.agent.entity.feign.vo.ActionChildPlan;
import com.risesin.agent.entity.feign.vo.ActionChildPlanVO;
import com.risesin.agent.entity.feign.vo.ExtFileData;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.launch.constant.AppConstant;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @AUTHOR Baby
 * @CREATE 2019/11/27
 * @DESCRIPTION 方案管理
 * @since 1.0.0
 */
@FeignClient(
        contextId = "planServiceFeign",
        value = AppConstant.APPLICATION_SYSTEM_NAME,
        fallback = PlanServiceFeignFallback.class
)
public interface IPlanServiceFeign {
     String PREFIX= "/actionChildPlan";

    /**
     * 查询子执行方案
      * @param searchMap
     * @param query
     * @return
     */
     @PostMapping(PREFIX + "/findPageSearch")
     R<PageResult> findSearch(@RequestBody Map searchMap, @RequestParam("query") Query query);

    /**
     * 根据code查询
      * @param childPlanCode
     * @return
     */
     @RequestMapping(value = PREFIX + "/{childPlanCode}", method = RequestMethod.GET)
     R<ActionChildPlanVO> findByChildPlanCode(@PathVariable String childPlanCode);

    /**
     * 查询资料模板
      * @param code
     * @return
     */
     @GetMapping(PREFIX + "/dataVerification/{code}")
     R<List<ExtFileData>> dataVerification( @PathVariable String code);

     /**
      * 更新流程
      * @param actionChildPlan
      * @return
      */
     @PostMapping(PREFIX + "/updateFlowByCode")
     R<String> updateFlowByCode(@RequestBody ActionChildPlan actionChildPlan);

    /**
     * 更新
      * @param actionChildPlanVO
     * @return
     */
     @RequestMapping(value=PREFIX +"/update",method= RequestMethod.POST)
     R<String> update(@RequestBody ActionChildPlanVO actionChildPlanVO);

}
