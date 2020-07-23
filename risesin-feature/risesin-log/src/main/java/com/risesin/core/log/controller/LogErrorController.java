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
import com.risesin.core.log.model.LogError;
import com.risesin.core.log.model.LogErrorVo;
import com.risesin.core.log.service.ILogErrorService;
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
 * @since 2018-09-26
 */
@RestController
@AllArgsConstructor
@RequestMapping("/error")
@Api(tags = "ERROR日志管理")
public class LogErrorController {

    private ILogErrorService errorLogService;

    /**
     * 查询单条
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入logId")
    public R<LogError> detail(@ApiIgnore("日志ID") @RequestParam String logId) {
        return R.data(errorLogService.findById(Func.toInt(logId)));
    }

    /**
     * 查询多条(分页)
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "查询多条(分页)", notes = "传入params,query")
    public R<List<LogErrorVo>> list(@ApiIgnore @RequestParam Map<String, Object> params, Query query) {
        List<LogError> search = errorLogService.findSearch(params, query).getContent();
        List<LogErrorVo> result = search.stream().map(logError -> {
            LogErrorVo vo = BeanUtil.copy(logError, LogErrorVo.class);
            vo.setStrId(Func.toStr(logError.getId()));
            return vo;
        }).collect(Collectors.toList());
        return R.data(result);
    }


}
