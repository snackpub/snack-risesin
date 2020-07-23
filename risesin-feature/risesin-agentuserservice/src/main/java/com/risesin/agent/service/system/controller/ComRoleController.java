package com.risesin.agent.service.system.controller;

import com.risesin.agent.entity.ComRole;
import com.risesin.agent.entity.vo.ComRoleVO;
import com.risesin.agent.entity.vo.IdAndStatusVO;
import com.risesin.agent.service.ComRoleService;
import com.risesin.agent.wrapper.ComRoleWrapper;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.jpaplus.support.RemoveIds;
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
 * comRole控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/comRole")
@Api(tags = "助贷端--系统管理--角色管理")
public class ComRoleController {

    @Autowired
    private ComRoleService comRoleService;

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public R<ComRoleVO> findById(@PathVariable Integer id) {
        ComRole comRole = comRoleService.findById(id);
        return R.data(ComRoleWrapper.build().entityVO(comRole));
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
        Page<ComRole> pageList = comRoleService.findSearch(searchMap, QueryUtil.queryIfNullForNew(query).getPageNo(),
                QueryUtil.queryIfNullForNew(query).getPageSize());
        return R.data(new PageResult<ComRole>(pageList.getTotalElements(), pageList.getContent()));
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
    public R<List<ComRoleVO>> findSearch(@RequestBody Map searchMap) {
        List<ComRole> search = comRoleService.findSearch(searchMap);
        return R.data(ComRoleWrapper.build().listNodeVO(search));
    }

    /**
     * 增加
     *
     * @param comRole
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "添加角色", notes = "添加角色")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public R<String> add(@RequestBody ComRole comRole) {
        comRoleService.add(comRole);
        return R.data("增加成功");
    }

    /**
     * 修改
     *
     * @param comRole
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "更新角色", notes = "更新角色")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R<String> update(@RequestBody ComRole comRole) {
        comRoleService.update(comRole);
        return R.data("修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "删除角色", notes = "根据id删除")
    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.POST)
    public R<Boolean> delete(@PathVariable Integer id) {
        return R.status(comRoleService.deleteById(id));
    }


    /**
     * 获取部门树形结构
     *
     * @return
     */
    @GetMapping("/tree")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "树形结构", notes = "树形结构")
    public R<List<ComRoleVO>> tree() {
        List<ComRoleVO> tree = comRoleService.tree();
        return R.data(tree);
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestBody RemoveIds ids) {
        return R.status(comRoleService.removeByIds(Func.toIntList(ids.getIds())));
    }

    /**
     * 修改状态
     *
     * @param idAndStatusVO
     * @return
     */
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "更新状态", notes = "传入id和状态")
    @RequestMapping(value = "/updateStatusById", method = RequestMethod.POST)
    public R<String> updateStatusById(@RequestBody IdAndStatusVO idAndStatusVO) {
        comRoleService.updateStatus(Func.toInt(idAndStatusVO.getId()), idAndStatusVO.getStatus());
        return R.data("修改成功");
    }
}
