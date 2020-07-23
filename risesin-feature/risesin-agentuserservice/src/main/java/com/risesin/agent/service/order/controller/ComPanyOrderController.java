package com.risesin.agent.service.order.controller;

import com.risesin.agent.entity.ComPanyOrder;
import com.risesin.agent.entity.ComPanyReception;
import com.risesin.agent.entity.vo.ComPanyOrderVO;
import com.risesin.agent.entity.vo.ComPanyReceptionVO;
import com.risesin.agent.entity.vo.IdAndStatusVO;
import com.risesin.agent.service.ComPanyOrderService;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.jpaplus.support.RemoveIds;
import com.risesin.core.log.annotation.ApiLog;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.utils.Func;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * comPanyOrder控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/comPanyOrder")
@Api(tags = "助贷端--订单管理")
@AllArgsConstructor
public class ComPanyOrderController {

    private ComPanyOrderService comPanyOrderService;

    /**
     * 查询全部数据
     *
     * @return
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查找所有订单", notes = "查找所有订单")
    @RequestMapping(method = RequestMethod.GET)
    @ApiLog(type = "1")
    public R<List<ComPanyOrder>> findAll() {
        return R.data(comPanyOrderService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "根据id查询订单", notes = "根据id查询订单")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiLog(type = "1")
    public R<ComPanyOrderVO> findById(@ApiParam(value = "订单id") @PathVariable String id) {
        return R.data(comPanyOrderService.findById(id));
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
    @ApiLog(type = "1")
    public R<PageResult> findSearch(@ApiParam(value = "搜索集合map") @RequestBody Map searchMap,
                                    @ApiParam(value = "第几页") @RequestBody Query query) {
        Page<ComPanyOrder> pageList = comPanyOrderService.findSearch(searchMap, QueryUtil.queryIfNullForNew(query).getPageNo(), QueryUtil.queryIfNullForNew(query).getPageSize());
        PageResult<ComPanyOrder> pageResult = new PageResult<>(pageList.getTotalElements(), pageList.getContent());
        return R.data(pageResult);
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @ApiLog(type = "1")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "根据条件查询订单", notes = "根据条件查询订单")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public R<List<ComPanyOrder>> findSearch(@ApiParam(value = "搜索条件map") @RequestBody Map searchMap) {
        return R.data(comPanyOrderService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param planCode
     */
    @ApiLog(type = "1")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "增加订单", notes = "增加订单")
    @RequestMapping(value = "/add/{planCode}", method = RequestMethod.POST)
    public R<String> add(@ApiParam(value = "助贷bean") @PathVariable String planCode) {
        comPanyOrderService.add(planCode);
        return R.success("success");
    }

    /**
     * 修改
     *
     * @param comPanyOrder
     */
    @ApiLog(type = "1")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "更新订单", notes = "更新订单")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R<String> update(@ApiParam(value = "助贷订单") @RequestBody ComPanyOrder comPanyOrder) {
        comPanyOrderService.update(comPanyOrder);
        return R.success("success");
    }

    /**
     * 删除
     *
     * @param id
     */
    @ApiLog(type = "1")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "根据id删除订单", notes = "根据id删除订单")
    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
    public R<String> delete(@ApiParam(value = "id") @PathVariable String id) {
        comPanyOrderService.deleteById(id);
        return R.success("success");
    }

    /**
     * 删除
     */
    @ApiLog(type = "1")
    @PostMapping("/remove")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "删除", notes = "传入ids")
    public R<String> remove(@ApiParam(value = "主键集合", required = true) @RequestBody RemoveIds ids) {
        return R.status(comPanyOrderService.removeByIds(Func.toStrList(ids.getIds())));
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
        comPanyOrderService.updateStatus((String) idAndStatusVO.getId(), idAndStatusVO.getStatus());
        return R.data("修改成功");
    }

    /**
     * 审核
     *
     * @param comPanyOrder
     * @return
     */
    @ApiLog(type = "1")
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "审核", notes = "传入id和状态")
    @RequestMapping(value = "/verifier", method = RequestMethod.POST)
    public R<String> verifier(@RequestBody ComPanyOrder comPanyOrder) {
        comPanyOrderService.verifier(comPanyOrder);
        return R.data("操作成功");
    }

    /**
     * 付款回执
     *
     * @param comPanyReception
     * @return
     */
    @ApiLog(type = "1")
    @ApiOperationSupport(order = 11)
    @ApiOperation(value = "付款回执", notes = "回执信息")
    @RequestMapping(value = "/paymentReceipt", method = RequestMethod.POST)
    public R<String> paymentReceipt(@RequestBody ComPanyReception comPanyReception) {
        comPanyOrderService.paymentReceipt(comPanyReception);
        return R.data("操作成功");
    }

    /**
     * 回执详情
     *
     * @param orderCode
     * @return
     */
    @ApiLog(type = "1")
    @ApiOperationSupport(order = 11)
    @ApiOperation(value = "回执详情", notes = "回执信息")
    @RequestMapping(value = "/receiptDetails/{orderCode}", method = RequestMethod.POST)
    public R<ComPanyReceptionVO> receiptDetails(@PathVariable String orderCode) {
        ComPanyReceptionVO comPanyReceptionVO = comPanyOrderService.receiptDetails(orderCode);
        return R.data(comPanyReceptionVO);
    }

    /**
     * 修改流程状态(C端调用)
     * C端确认收款，将订单状态改为确认收款(6)
     * @param params id,flow
     * @return
     */
    @ApiLog(type = "1")
    @ApiOperationSupport(order = 12)
    @ApiOperation(value = "修改流程状态", notes = "修改流程状态")
    @RequestMapping(value = "/updateFlow", method = RequestMethod.POST)
    public R updateFlow(@ApiParam(value = "修改流程状态") @RequestBody Map<String,Object> params) {
        ComPanyOrder comPanyOrder = new ComPanyOrder();
        comPanyOrder.setId((String) params.get("id"));
        comPanyOrder.setFlow((byte) ((Integer) params.get("flow")).intValue());
        comPanyOrderService.update(comPanyOrder);
        return R.success("success");
    }

}
