package com.risesin.enterpriseuserservice.service.dict;

import com.risesin.core.tool.api.R;
import com.risesin.enterprise.feign.dict.IAreaClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @AUTHOR Darling
 * @CREATE 2019-11-29
 * @DESCRIPTION 地区枚举
 * @since 1.0.0
 */

@Api(tags = "地区枚举")
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/area")
public class AreaController {

    private IAreaClient areaClient;

    /**
     * 根据父code查询
     *
     * @param parentCode 父code
     * @return json
     */
    @ApiOperation(value = "根据parentCode查询信息", notes = "传入pcode")
    @GetMapping("/listByPcode/{parentCode}")
    public R listByPid(@PathVariable @ApiParam("父code") String parentCode) {
       return areaClient.listByPid(parentCode);
    }

    /**
     * 获取融资类型树形结构
     *
     * @return
     */
    @GetMapping("/tree")
    @ApiOperation(value = "区域树形结构", notes = "区域树形结构")
    public R tree(@RequestParam(required = false) Byte status) {
        return areaClient.tree(status);
    }



}
