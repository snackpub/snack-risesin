package com.risesin.systemuserservice.service.dict;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-06
 * @DESCRIPTION 行业管理
 * @since 1.0.0
 */

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.utils.Func;
import com.risesin.core.tool.utils.StringUtil;
import com.risesin.system.service.dict.IndustryService;
import com.risesin.systemapi.dict.entity.Industry;
import com.risesin.systemapi.dict.vo.IdAndStatusVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-10-18
 * @DESCRIPTION 行业管理
 * @since 1.0.0
 */
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/industry")
@Api(value = "行业管理", tags = "行业管理管理")
public class IndustryController {

    private IndustryService industryService;

    /**
     * 根据父code查询
     *
     * @param parentCode 父code
     * @return json
     */
    @ApiOperation(value = "根据parentCode查询信息", notes = "传入pcode")
    @GetMapping("/listByPcode/{parentCode}")
    public R listByPid(@PathVariable @ApiParam("父code") String parentCode) {
        List<Industry> industryList = null;
        if (StringUtil.isEmpty(parentCode)) {
            industryList = industryService.listByPid("0");
        } else {
            industryList = industryService.listByPid(parentCode);
        }
        return R.data(industryList);
    }

    /**
     * 分页查询（带模糊查询功能）
     *
     * @param searchMap 查询条件封装 status,code,name
     * @param query     分页信息
     * @return
     */
    @ApiOperation(value = "分页查询（带模糊查询功能）", notes = "传入字段条件，页码，页大小")
    @PostMapping("/findPageSearch")
    public R list(@ApiParam(value = "搜索集合map") @RequestBody Map<String, Object> searchMap,
                  @ApiParam(value = "查询对象") @RequestBody Query query) {
        Page<Industry> pageList = industryService.findSearch(searchMap, QueryUtil.queryIfNullForNew(query).getPageNo(), QueryUtil.queryIfNullForNew(query).getPageSize());
        return R.data(new PageResult(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 新增行业
     *
     * @param industry
     * @return
     */
    @ApiOperation(value = "新增行业", notes = "传入area")
    @PostMapping("/add")
    public R addIndustry(@RequestBody @ApiParam("area对象") Industry industry) {
        if (industryService.findByCode(industry.getCode()) == null) {
            industryService.add(industry);
            return R.success(ResultCode.SUCCESS);
        }
        return R.fail("code已存在");
    }

    /**
     * 更改行业
     *
     * @param industry
     * @return json
     */
    @ApiOperation(value = "更改行业", notes = "传入industry")
    @PostMapping("/update")
    public R updateIndustry(@Valid @RequestBody @ApiParam("industry对象") Industry industry) {
        Integer id = industry.getId();
        if (id == null || id < 1) {
            //400 参数校验失败
            return R.fail(ResultCode.PARAM_VALID_ERROR.getCode(), ResultCode.PARAM_VALID_ERROR.getMessage());
        }
        industryService.update(industry);
        //查询不到数据,id有误
        return R.success("更改成功");
    }

    /**
     * 获取行业树形结构
     *
     * @return
     */
    @GetMapping("/tree")
    @ApiOperation(value = "行业树形结构", notes = "行业树形结构")
    public R tree(@RequestParam(required = false) Byte status) {
        List<Map<String, Object>> tree = industryService.tree(status);
        return R.data(tree);
    }


    /**
     * 修改状态
     *
     * @param idAndStatusVO
     * @return
     */
    @ApiOperation(value = "更新状态", notes = "传入id和状态")
    @PostMapping("/updateStatusById")
    public R<String> updateStatusById(@RequestBody IdAndStatusVO idAndStatusVO) {
        industryService.updateStatus(Func.toInt(idAndStatusVO.getId()), idAndStatusVO.getStatus());
        return R.data("修改成功");
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(industryService.removeByIds(Func.toIntList(ids)));
    }
}
