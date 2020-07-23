package com.risesin.agent.service.system.controller;

import com.risesin.agent.entity.ComMenu;
import com.risesin.agent.entity.vo.ComMenuVO;
import com.risesin.agent.entity.vo.IdAndStatusVO;
import com.risesin.agent.service.ComMenuService;
import com.risesin.agent.wrapper.ComMenuWrapper;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.utils.Func;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * comMenu控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/comMenu")
@Api(tags = "助贷端--系统管理--菜单管理")
public class ComMenuController {

    @Autowired
    private ComMenuService comMenuService;

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public R<ComMenuVO> findById(@PathVariable Integer id) {
        ComMenu comMenuServiceById = comMenuService.findById(id);
        return R.data(ComMenuWrapper.build().entityVO(comMenuServiceById));
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param query     查询对象
     * @return 分页结果
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "根据条件分页查询", notes = "根据条件分页查询")
    @RequestMapping(value = "/findPageSearch", method = RequestMethod.POST)
    public R<PageResult> findSearch(@ApiParam(value = "搜索集合map") @RequestBody Map searchMap,
                                    @ApiParam(value = "查询对象") @RequestBody Query query) {
        Page<ComMenu> pageList = comMenuService.findSearch(searchMap, QueryUtil.queryIfNullForNew(query).getPageNo(),
                QueryUtil.queryIfNullForNew(query).getPageSize());
        return R.data(new PageResult<ComMenu>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "根据条件查询", notes = "map集合")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public R<List<ComMenuVO>> findSearch(@RequestBody Map searchMap) {
        List<ComMenu> search = comMenuService.findSearch(searchMap);
        return R.data(ComMenuWrapper.build().listNodeVO(search));
    }

    /**
     * 增加
     *
     * @param comMenu
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "添加菜单", notes = "添加菜单")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public R<String> add(@RequestBody ComMenu comMenu) {
        comMenuService.add(comMenu);
        return R.data("增加成功");
    }

    /**
     * 修改
     *
     * @param comMenu
     */

    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "更新菜单", notes = "更新菜单")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R<String> update(@RequestBody ComMenu comMenu) {
        comMenuService.update(comMenu);
        return R.data("修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "删除菜单", notes = "根据id删除")
    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.POST)
    public R<String> delete(@PathVariable Integer id) {
        comMenuService.deleteById(id);
        return R.data("删除成功");
    }

    /**
     * 修改状态
     *
     * @param idAndStatusVO
     * @return
     */
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "更新状态", notes = "传入id和状态")
    @RequestMapping(value = "/updateStatusById", method = RequestMethod.POST)
    public R<String> updateStatusById(@RequestBody IdAndStatusVO idAndStatusVO) {
        comMenuService.updateStatus(Func.toInt(idAndStatusVO.getId()), idAndStatusVO.getStatus());
        return R.data("修改成功");
    }

    /**
     * 获取部门树形结构
     *
     * @return
     */
    @GetMapping("/tree")
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "树形结构", notes = "树形结构")
    public R<List<ComMenuVO>> tree() {
        List<ComMenuVO> tree = comMenuService.tree();
        return R.data(tree);
    }
}
