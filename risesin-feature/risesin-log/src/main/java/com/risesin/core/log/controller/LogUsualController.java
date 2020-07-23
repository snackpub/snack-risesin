package com.risesin.core.log.controller;


import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.log.model.LogUsual;
import com.risesin.core.log.model.LogUsualVo;
import com.risesin.core.log.service.ILogUsualService;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.core.tool.utils.Func;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 控制器
 *
 * @author Chill
 * @since 2018-10-12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/usual")
@Api(tags = "Usual日志管理")
public class LogUsualController {

    private ILogUsualService logService;

    /**
     * 查询单条
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入logId")
    public R<LogUsual> detail(@ApiIgnore("日志ID") @RequestParam String logId) {
        return R.data(logService.findById(Func.toInt(logId)));
    }

    /**
     * 查询多条(分页)
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "查询多条(分页)", notes = "传入params,query")
    public R<List<LogUsualVo>> list(@ApiIgnore @RequestParam Map<String, Object> params, Query query) {
        List<LogUsual> search = logService.findSearch(params, query).getContent();
        List<LogUsualVo> result = search.stream().map(logUsual -> {
            LogUsualVo vo = BeanUtil.copy(logUsual, LogUsualVo.class);
            vo.setStrId(Func.toStr(logUsual.getId()));
            return vo;
        }).collect(Collectors.toList());
        return R.data(result);
    }

}
