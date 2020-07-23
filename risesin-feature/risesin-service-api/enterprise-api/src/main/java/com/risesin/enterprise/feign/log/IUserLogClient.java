package com.risesin.enterprise.feign.log;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.launch.constant.AppConstant;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

@FeignClient(
        value = AppConstant.APPLICATION_SYSTEM_NAME,
        fallback = UserLogClientFallback.class
)
public interface IUserLogClient {
    String API_PREFIX = "/userLog";

    /**
     * 分页+多条件查询
     *
     * @param params 查询条件封装
     * @param query  查询对象
     * @return 分页结果
     */
    @PostMapping(API_PREFIX + "/findPageSearch")
    @ApiOperation(value = "分页查询", notes = "根据条件分页查询")
    public R<PageResult> findSearch(@ApiParam(value = "参数集合") @RequestBody Map<String, Object> params,
                                    @ApiParam(value = "分页码") @ApiIgnore @RequestParam Query query);
}
