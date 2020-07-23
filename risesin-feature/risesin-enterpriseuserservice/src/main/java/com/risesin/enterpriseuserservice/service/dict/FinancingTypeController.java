package com.risesin.enterpriseuserservice.service.dict;

import com.risesin.core.tool.api.R;
import com.risesin.enterprise.feign.dict.IFinancingTypeClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @AUTHOR Darling
 * @CREATE 2019-11-29
 * @DESCRIPTION 融资类型
 * @since 1.0.0
 */
@Api(tags = "融资类型")
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/financingType")
public class FinancingTypeController {

    private IFinancingTypeClient financingTypeClient;

    /**
     * 根据父code查询
     *
     * @param parentCode 父code
     * @return json
     */
    @ApiOperation(value = "根据parentCode查询信息", notes = "传入pcode")
    @GetMapping("/listByPcode/{parentCode}")
    public R listByPid(@PathVariable @ApiParam("父code") String parentCode) {
        return financingTypeClient.listByPid(parentCode);
    }

    /**
     * 获取融资类型树形结构
     *
     * @return
     */
    @GetMapping("/tree")
    @ApiOperation(value = "融资类型树形结构", notes = "融资类型树形结构")
    public R tree(@RequestParam(required = false) Byte status) {
        return financingTypeClient.tree(status);
    }

}
