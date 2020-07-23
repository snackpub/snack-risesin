package com.risesin.agent.service.bank.controller;

import com.risesin.agent.entity.ComBankCard;
import com.risesin.agent.entity.vo.IdAndStatusVO;
import com.risesin.agent.service.ComBankCardService;
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
 * comBankCard控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/comBankCard")
@Api(tags = "助贷端--银行卡管理")
public class ComBankCardController {

    @Autowired
    private ComBankCardService comBankCardService;

    /**
     * 查询全部数据
     *
     * @return
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查找所有银行卡", notes = "查找所有银行卡")
    @RequestMapping(method = RequestMethod.GET)
    public R<List<ComBankCard>> findAll() {
        return R.data(comBankCardService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param pkId ID
     * @return
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "根据id查询银行卡", notes = "根据id查询银行卡")
    @RequestMapping(value = "/{pkId}", method = RequestMethod.GET)
    public R<ComBankCard> findById(@PathVariable Integer pkId) {
        return R.data(comBankCardService.findById(pkId));
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
                                    @ApiParam(value = "第几页") @RequestBody Query query) {
        Page<ComBankCard> pageList = comBankCardService.findSearch(searchMap, QueryUtil.queryIfNullForNew(query).getPageNo(),
                QueryUtil.queryIfNullForNew(query).getPageSize());
        return R.data(new PageResult<ComBankCard>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "根据条件查询银行卡", notes = "根据条件查询银行卡")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public R<List<ComBankCard>> findSearch(@RequestBody Map searchMap) {
        return R.data(comBankCardService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param comBankCard
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "增加银行卡", notes = "增加银行卡")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public R<String> add(@RequestBody ComBankCard comBankCard) {
        comBankCardService.add(comBankCard);
        return R.data("增加成功");
    }

    /**
     * 修改
     *
     * @param comBankCard
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "更新银行卡", notes = "更新银行卡")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R<String> update(@RequestBody ComBankCard comBankCard) {
        comBankCardService.update(comBankCard);
        return R.data("修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "根据id删除银行卡", notes = "根据id删除银行卡")
    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
    public R<String> delete(@PathVariable Integer id) {
        comBankCardService.deleteById(id);
        return R.data("删除成功");
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestBody RemoveIds ids) {
        return R.status(comBankCardService.removeByIds(Func.toIntList(ids.getIds())));
    }

    /**
     * 修改状态
     *
     * @param idAndStatusVO
     * @return
     */
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "更新状态", notes = "传入id和状态")
    @RequestMapping(value = "/updateStatusById", method = RequestMethod.POST)
    public R<String> updateStatusById(@RequestBody IdAndStatusVO idAndStatusVO) {
        comBankCardService.updateStatus(Func.toInt(idAndStatusVO.getId()), idAndStatusVO.getStatus());
        return R.data("修改成功");
    }
}
