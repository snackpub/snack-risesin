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
package com.risesin.core.log.feign;

import com.risesin.core.log.model.LogApi;
import com.risesin.core.log.model.LogError;
import com.risesin.core.log.model.LogUsual;
import com.risesin.core.log.service.ILogApiService;
import com.risesin.core.log.service.ILogErrorService;
import com.risesin.core.log.service.ILogUsualService;
import com.risesin.core.tool.api.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 日志服务Feign实现类
 *
 * @author honey
 */
@ApiIgnore()
@RestController
@AllArgsConstructor
@RequestMapping("/log")
public class LogClient implements ILogClient {

    ILogUsualService usualLogService;

    ILogApiService apiLogService;

    ILogErrorService errorLogService;

    @Override
    @PostMapping("/saveUsualLog")
    public R<Boolean> saveUsualLog(@RequestBody LogUsual log) {
        log.setParams(log.getParams().replace("&amp;", "&"));
        usualLogService.add(log);
        return R.data(true);
    }

    @Override
    @PostMapping("/saveApiLog")
    public R<Boolean> saveApiLog(@RequestBody LogApi log) {
        log.setParams(log.getParams().replace("&amp;", "&"));
        apiLogService.add(log);
        return R.data(true);
    }

    @Override
    @PostMapping("/saveErrorLog")
    public R<Boolean> saveErrorLog(@RequestBody LogError log) {
        log.setParams(log.getParams().replace("&amp;", "&"));
        errorLogService.add(log);
        return R.data(true);
    }
}
