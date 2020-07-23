package com.risesin.systemuserservice.service.feign;


import com.risesin.core.tool.api.R;
import com.risesin.system.service.dict.DictServiceImpl;
import com.risesin.systemapi.dict.entity.Dict;
import com.risesin.systemapi.feign.IDictClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


/**
 * 字典服务Feign实现类
 *
 * @author honey
 */
@ApiIgnore
@RestController
@AllArgsConstructor
public class DictClient implements IDictClient {

    DictServiceImpl service;

    @Override
    @GetMapping(API_PREFIX + "/getValue")
    public R<String> getValue(String code, Integer dictKey) {
        return R.data(service.getValue(code, dictKey));
    }

    @Override
    @GetMapping(API_PREFIX + "/getList")
    public R<List<Dict>> getList(String code) {
        return R.data(service.getList(code));
    }

}
