/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.risesin.core.log.controller;


import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.log.model.LogApi;
import com.risesin.core.log.model.LogApiVo;
import com.risesin.core.log.service.ILogApiService;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.core.tool.utils.Func;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 控制器
 *
 * @author Chill
 * @since 2018-09-26
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Api(tags = "API日志管理")
public class LogApiController {

    private ILogApiService logService;

    /**
     * 查询单条
     */
    @GetMapping("/detail/{logId}")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入logId")
//    @ApiLog("查询单条")
    public R<LogApi> detail(@ApiIgnore("日志ID") @PathVariable String logId) {
        return R.data(logService.findById(Func.toInt(logId)));
    }

    /**
     * 查询多条(分页)
     */
    @PostMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "查询多条(分页)", notes = "传入params,query")
    public R<PageResult> list(@ApiIgnore @RequestBody(required=false) Map<String, Object> params, @RequestBody(required=false) Query query) {
        Page<LogApi> search = logService.findSearch(params, query);
        List<LogApiVo> result = search.stream().map(logApi -> {
            LogApiVo vo = BeanUtil.copy(logApi, LogApiVo.class);
            vo.setStrId(Func.toStr(logApi.getId()));
            return vo;
        }).collect(Collectors.toList());
        return R.data(new PageResult(result.size(),result));
    }

}
