package com.risesin.agent.service.system.controller;

import com.risesin.agent.entity.ComPermission;
import com.risesin.agent.service.ComPermissionService;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
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
 * comPermission控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/comPermission")
@Api(tags = "助贷端--系统管理--权限管理(暂不完善)")
public class ComPermissionController {

    @Autowired
    private ComPermissionService comPermissionService;

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public R<ComPermission> findById(@PathVariable Integer id) {
        return R.data(comPermissionService.findById(id));
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
        Page<ComPermission> pageList = comPermissionService.findSearch(searchMap, QueryUtil.queryIfNullForNew(query).getPageNo(),
                QueryUtil.queryIfNullForNew(query).getPageSize());
        return R.data(new PageResult<ComPermission>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public R<List<ComPermission>> findSearch(@RequestBody Map searchMap) {
        return R.data(comPermissionService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param comPermission
     */
    @RequestMapping(method = RequestMethod.POST)
    public R add(@RequestBody ComPermission comPermission) {
        comPermissionService.add(comPermission);
        return R.data("增加成功");
    }

    /**
     * 修改
     *
     * @param comPermission
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.PUT)
    public R update(@RequestBody ComPermission comPermission) {
        comPermissionService.update(comPermission);
        return R.data("修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public R delete(@PathVariable Integer id) {
        comPermissionService.deleteById(id);
        return R.data("删除成功");
    }

}
