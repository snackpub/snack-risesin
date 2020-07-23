package com.risesin.agent.service.message.controller;

import com.risesin.agent.entity.ComMsgCenter;
import com.risesin.agent.entity.vo.IdAndStatusVO;
import com.risesin.agent.service.ComMsgCenterService;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.log.annotation.ApiLog;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.constant.RisesinConstant;
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
 * comMsgCenter控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/comMsgCenter")
@Api(tags = "助贷端--消息中心")
public class ComMsgCenterController {

    @Autowired
    private ComMsgCenterService comMsgCenterService;

    /**
     * 查询全部数据
     *
     * @return
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查找所有消息", notes = "查找所有消息")
    @RequestMapping(method = RequestMethod.GET)
    @ApiLog(type = RisesinConstant.SERVICE_TYPE_AGENT)
    public R<List<ComMsgCenter>> findAll() {
        return R.data(comMsgCenterService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @ApiLog(type = "1")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "根据id查询消息", notes = "根据id查询消息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public R<ComMsgCenter> findById(@PathVariable Integer id) {
        return R.data(comMsgCenterService.findById(id));
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param query     查询对象
     * @return 分页结果
     */
    @ApiLog(type = "1")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "根据条件分页查询", notes = "根据条件分页查询")
    @RequestMapping(value = "/findPageSearch", method = RequestMethod.POST)
    public R<PageResult> findSearch(@ApiParam(value = "搜索集合map") @RequestBody Map searchMap,
                                    @ApiParam(value = "第几页") @RequestBody Query query) {
        Page<ComMsgCenter> pageList = comMsgCenterService.findSearch(searchMap, QueryUtil.queryIfNullForNew(query).getPageNo(), QueryUtil.queryIfNullForNew(query).getPageSize());
        return R.data(new PageResult<ComMsgCenter>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @ApiLog(type = "1")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "根据条件查询消息", notes = "根据条件查询消息")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public R<List<ComMsgCenter>> findSearch(@RequestBody Map searchMap) {
        return R.data(comMsgCenterService.findSearch(searchMap));
    }

//    /**
//     * 增加
//     * @param comMsgCenter
//     */
//    @ApiOperationSupport(order = 5)
//    @ApiOperation(value = "增加消息", notes = "增加消息")
//    @RequestMapping(method=RequestMethod.POST)
//    public R<String> add(@RequestBody ComMsgCenter comMsgCenter  ){
//        comMsgCenterService.add(comMsgCenter);
//        return R.data("增加成功");
//    }

    /**
     * 修改
     *
     * @param comMsgCenter
     */
    @ApiLog(type = "1")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "更新消息", notes = "更新消息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R<String> update(@RequestBody ComMsgCenter comMsgCenter) {
        comMsgCenterService.update(comMsgCenter);
        return R.data("修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @ApiLog(type = "1")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "根据id删除消息", notes = "根据id删除消息")
    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
    public R<String> delete(@PathVariable Integer id) {
        comMsgCenterService.deleteById(id);
        return R.data("删除成功");
    }

    /**
     * 删除
     */
    @ApiLog(type = "1")
    @PostMapping("/remove")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(comMsgCenterService.removeByIds(Func.toIntList(ids)));
    }

    /**
     * 修改状态
     *
     * @param idAndStatusVO
     * @return
     */
    @ApiLog(type = "1")
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "更新状态", notes = "传入id和状态")
    @RequestMapping(value = "/updateStatusById", method = RequestMethod.POST)
    public R<String> updateStatusById(@RequestBody IdAndStatusVO idAndStatusVO) {
        comMsgCenterService.updateStatus(Func.toInt(idAndStatusVO.getId()), idAndStatusVO.getStatus());
        return R.data("修改成功");
    }
}
