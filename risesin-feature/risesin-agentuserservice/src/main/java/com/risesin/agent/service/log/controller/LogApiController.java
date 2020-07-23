package com.risesin.agent.service.log.controller;

import com.risesin.agent.entity.feign.IApiLogFeign;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.log.model.LogApi;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @AUTHOR Baby
 * @CREATE 2019/12/17
 * @DESCRIPTION 日志
 * @since 1.0.0
 */
@RestController
@CrossOrigin
@RequestMapping("/log")
@Api(tags = "助贷端--日志")
@AllArgsConstructor
public class LogApiController {

    private IApiLogFeign iApiLogFeign;
    /**
     * 查询单条
     */
    @GetMapping("/detailApi/{logId}")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入logId")
    public R<LogApi> detailApi(@PathVariable String logId) {
        return iApiLogFeign.detailApi(logId);
    }

    /**
     * 查询多条(分页)
     */
    @PostMapping("/listApi")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "查询多条(分页)", notes = "传入params,query")
    public R<PageResult> listApi(@ApiIgnore @RequestBody Map<String, Object> params, @RequestBody Query query) {
        // 注入查询类型 为助贷端
        params.put("type", "1");
        return iApiLogFeign.listApi(params,query);
    }
}
