/**
 *
 */
package com.risesin.systemapi.feign;


import com.risesin.core.launch.constant.AppConstant;
import com.risesin.core.tool.api.R;
import com.risesin.systemapi.dict.entity.Dict;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Feign接口类
 *
 * @author honey
 */
@FeignClient(
        value = AppConstant.APPLICATION_SYSTEM_NAME,
        fallback = IDictClientFallback.class
)
public interface IDictClient {

    String API_PREFIX = "/dict";

    /**
     * 获取字典表对应值
     *
     * @param code    字典编号
     * @param dictKey 字典序号
     * @return
     */
    @GetMapping(API_PREFIX + "/getValue")
    R<String> getValue(@RequestParam("code") String code, @RequestParam("dictKey") Integer dictKey);

    /**
     * 获取字典表
     *
     * @param code 字典编号
     * @return
     */
    @GetMapping(API_PREFIX + "/getList")
    R<List<Dict>> getList(@RequestParam("code") String code);

}
