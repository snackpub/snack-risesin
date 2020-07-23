package com.risesin.systemuserservice.service.bank;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.utils.Func;
import com.risesin.system.service.dict.SysBankCardService;
import com.risesin.systemapi.dict.entity.SysBankCard;
import com.risesin.systemapi.dict.vo.IdAndStatusVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * comBankCard控制器层
 *
 * @author Administrator
 */
@RestController
@AllArgsConstructor
@Api(tags = "银行卡管理")
@RequestMapping("/bankCard")
public class BankCardController {

    private SysBankCardService bankCardService;


    /**
     * 详情
     *
     * @param id ID
     * @return
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入ID ")
    @PostMapping(value = "/{id}")
    public R<SysBankCard> findById(@PathVariable Integer id) {
        return R.data(bankCardService.findById(id));
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
        Page<SysBankCard> pageList = bankCardService.findSearch(searchMap, query);
        return R.data(new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }


    /**
     * 增加
     *
     * @param bankCard
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "增加", notes = "传入SysBankCard")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public R<String> add(@RequestBody SysBankCard bankCard) {
        bankCardService.add(bankCard);
        return R.success(ResultCode.SUCCESS);
    }

    /**
     * 修改
     *
     * @param bankCard
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "更新银行卡", notes = "更新银行卡")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R<String> update(@RequestBody SysBankCard bankCard) {
        bankCardService.update(bankCard);
        return R.data("修改成功");
    }

    /**
     * 删除
     *
     * @param ids
     */
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "根据id删除银行卡", notes = "传入集合ids")
    @GetMapping("/remove")
    public R<String> delete(@ApiParam("传入集合ids") @RequestParam String ids) {
        bankCardService.removeBatch(Func.toIntList(ids));
        return R.data("删除成功");
    }

    /**
     * 修改状态
     *
     * @param idAndStatusVO
     * @return
     */
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "更新状态", notes = "传入id和状态")
    @PostMapping("/updateStatusById")
    public R<String> updateStatusById(@RequestBody IdAndStatusVO idAndStatusVO) {
        bankCardService.updateStatus(Func.toInt(idAndStatusVO.getId()), idAndStatusVO.getStatus());
        return R.data("修改成功");
    }
}
