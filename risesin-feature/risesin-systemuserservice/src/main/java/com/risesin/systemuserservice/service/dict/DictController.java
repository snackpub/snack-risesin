package com.risesin.systemuserservice.service.dict;

import com.risesin.core.boot.ctrl.RisesinController;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.log.annotation.ApiLog;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.utils.Func;
import com.risesin.system.service.dict.DictServiceImpl;
import com.risesin.system.wrapper.dict.DictWrapper;
import com.risesin.systemapi.dict.entity.Dict;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

import static com.risesin.common.cache.CacheNames.DICT_LIST;
import static com.risesin.common.cache.CacheNames.DICT_VALUE;


/**
 * 字典管理
 *
 * @author : honey
 * @date : 2019-10-11 10:30
 **/
@RestController
@AllArgsConstructor
@RequestMapping("/dict")
@Api(value = "字典管理", tags = "字典管理")
public class DictController extends RisesinController {


    private DictServiceImpl dictService;


    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入ID")
    public R detail(@ApiParam(value = "主键ID", required = true) @RequestParam String id) {
        Dict detail = dictService.findById(Func.toInt(id));
        return R.data(DictWrapper.build().entityVO(detail));
    }

    /**
     * 新增或修改
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "新增或修改", notes = "传入dict")
    public R submit(@RequestBody Dict dict) {
        dictService.add(dict);
        return R.success(ResultCode.SUCCESS);
    }

    /**
     * 修改状态
     */
    @ApiLog("修改状态")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "更新状态", notes = "传入id和状态")
    @PostMapping(value = "/updateStatusById")
    public R<String> updateStatusById(@RequestBody Dict dict) {
        dictService.updateStatus(dict.getId(), dict.getStatus());
        return R.success(ResultCode.SUCCESS);
    }

    /**
     * 列表
     */
    @ApiLog("列表")
    @PostMapping("/list")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "列表", notes = "传入dict")
    public R list(@RequestBody Map<String, Object> params,
                  @ApiIgnore @RequestBody Query query) {
        Page<Dict> pages = dictService.findSearch(params, query);
        return R.data(DictWrapper.build().pageVO(pages));
    }

    /**
     * 获取字典树形结构
     */
    @GetMapping("/tree")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "树形结构", notes = "树形结构")
    public R<List<Dict>> tree() {
        List<Dict> tree = dictService.tree();
        return R.data(tree);
    }


    /**
     * 获取字典
     */
    @GetMapping("/dictionary")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "获取字典", notes = "获取字典")
    public R<List<Dict>> dictionary(@NotBlank(message = "code不能为空") @ApiParam(value = "字典码", required = true) @RequestParam String code) {
        List<Dict> tree = dictService.getList(code);
        return R.data(tree);
    }


    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "批量删除", notes = "传入ids")
    @CacheEvict(cacheNames = {DICT_LIST, DICT_VALUE}, allEntries = true)
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(dictService.removeByIds(Func.toIntList(ids)));
    }


}
