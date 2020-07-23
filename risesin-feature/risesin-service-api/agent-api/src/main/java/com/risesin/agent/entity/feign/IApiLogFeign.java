package com.risesin.agent.entity.feign;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.launch.constant.AppConstant;
import com.risesin.core.log.model.LogApi;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @AUTHOR Baby
 * @CREATE 2019/12/18
 * @DESCRIPTION 日志feign
 * @since 1.0.0
 */
@FeignClient(
        value = AppConstant.APPLICATION_LOG_NAME,
        fallback = ApiLogFeignFallback.class
)
public interface IApiLogFeign {

    String API_PREFIX = "/log/api";

    /**
     * 根据id查询日志
     * @param logId
     * @return
     */
    @GetMapping(API_PREFIX+"/detail/{logId}")
    R<LogApi> detailApi(@PathVariable String logId);

    /**
     * 根据条件分页查询
     * @param params
     * @param query
     * @return
     */
    @PostMapping(API_PREFIX+"/list")
    R<PageResult> listApi(@RequestBody Map<String, Object> params, @RequestParam("query") Query query);
}
