package com.risesin.systemuserservice.service.dict;

import com.risesin.core.boot.ctrl.RisesinController;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.utils.Func;
import com.risesin.core.tool.utils.StringUtil;
import com.risesin.system.service.dict.FinancingTypeService;
import com.risesin.systemapi.dict.entity.FinancingType;
import com.risesin.systemapi.dict.vo.IdAndStatusVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 融资类型管理
 *
 * @author : honey
 * @date : 2019-11-28 14:28
 **/
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/financingType")
@Api(value = "融资类型管理", tags = "融资类型管理")
public class FinancingTypeController extends RisesinController {

    private FinancingTypeService service;


    /**
     * 根据父code查询
     *
     * @param parentCode 父code
     * @return json
     */
    @ApiOperation(value = "根据parentCode查询信息", notes = "传入pcode")
    @GetMapping("/listByPcode/{parentCode}")
    public R listByPid(@PathVariable @ApiParam("父code") String parentCode) {
        List<FinancingType> areaList = null;
        if (StringUtil.isEmpty(parentCode)) {
            areaList = service.listByPid("0");
        } else {
            areaList = service.listByPid(parentCode);
        }
        return R.data(areaList);
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
                  @ApiIgnore @ApiParam(value = "查询对象") @RequestBody Query query) {
        Page<FinancingType> pageList = service.findSearch(searchMap, QueryUtil.queryIfNullForNew(query).getPageNo(), QueryUtil.queryIfNullForNew(query).getPageSize());
        return R.data(new PageResult(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 新增融资类型
     *
     * @param financingType
     * @return
     */
    @ApiOperation(value = "新增数据", notes = "传入financingType")
    @PostMapping("/add")
    public R addFinancingType(@RequestBody @ApiParam("financingType对象") FinancingType financingType) {
        if (service.findByCode(financingType.getCode()) == null) {
            service.add(financingType);
            return R.success(ResultCode.SUCCESS);
        }
        return R.fail("code已存在");
    }

    /**
     * 更新省市区
     *
     * @param financingType
     * @return json
     */
    @ApiOperation(value = "更新融资类型", notes = "传入financingType")
    @PostMapping("/update")
    public R updateFinancingType(@Valid @RequestBody @ApiParam("financingType对象") FinancingType financingType) {
        Integer id = financingType.getId();
        if (id == null || id < 1) {
            //400 参数校验失败
            return R.fail(ResultCode.PARAM_VALID_ERROR.getCode(), ResultCode.PARAM_VALID_ERROR.getMessage());
        }
        service.update(financingType);
        //查询不到数据,id有误
        return R.success("修改成功");
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
        service.updateStatus(Func.toInt(idAndStatusVO.getId()), idAndStatusVO.getStatus());
        return R.data("修改成功");
    }

    /**
     * 获取融资类型树形结构
     *
     * @return
     */
    @GetMapping("/tree")
    @ApiOperation(value = "融资类型树形结构", notes = "融资类型树形结构")
    public R tree(@RequestParam(required = false) Byte status) {
        List<Map<String, Object>> tree = service.tree(status);
        return R.data(tree);
    }

    @PostMapping("/remove")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "批量删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(service.removeBatch(Func.toIntList(ids)));
    }

}
