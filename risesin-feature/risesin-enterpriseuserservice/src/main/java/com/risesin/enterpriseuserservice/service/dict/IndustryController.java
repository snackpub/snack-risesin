package com.risesin.enterpriseuserservice.service.dict;

import com.risesin.core.tool.api.R;
import com.risesin.enterprise.feign.dict.IIndustryClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @AUTHOR Darling
 * @CREATE 2019-11-29
 * @DESCRIPTION 行业枚举
 * @since 1.0.0
 */

@Api(tags = "行业枚举")
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/industry")
public class IndustryController {

    private IIndustryClient industryClient;

    /**
     * 根据父code查询
     *
     * @param parentCode 父code
     * @return json
     */
    @ApiOperation(value = "根据parentCode查询信息", notes = "传入pcode")
    @GetMapping("/listByPcode/{parentCode}")
    public R listByPid(@PathVariable @ApiParam("父code") String parentCode) {
        return industryClient.listByPid(parentCode);
    }

    /**
     * 获取行业树形结构
     *
     * @return
     */
    @GetMapping("/tree")
    @ApiOperation(value = "行业树形结构", notes = "行业树形结构")
    public R tree(@RequestParam(required = false) Byte status) {
        return industryClient.tree(status);
    }
}
