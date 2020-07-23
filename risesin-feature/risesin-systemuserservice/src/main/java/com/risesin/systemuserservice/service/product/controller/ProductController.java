package com.risesin.systemuserservice.service.product.controller;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.log.annotation.ApiLog;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.utils.Func;
import com.risesin.system.service.product.ProductService;
import com.risesin.systemapi.product.entity.Product;
import com.risesin.systemapi.product.interfaceresult.ProductNames;
import com.risesin.systemapi.product.vo.ProductGrant;
import com.risesin.systemapi.product.vo.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * product控制器层
 *
 * @author Administrator
 */
@RestController
@AllArgsConstructor
@Api(tags = "产品管理")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "根据id获取产品", notes = "传入产品Id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public R<ProductVO> findById(@PathVariable Integer id) {
        return R.data(productService.findById(id));
    }

    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param query     查询对象
     * @return 分页结果
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "根据条件分页查询", notes = "根据条件分页查询")
    @RequestMapping(value = "/findPageSearch", method = RequestMethod.POST)
    public R<PageResult> findSearch(@ApiParam(value = "搜索集合map") @RequestBody Map searchMap,
                                    @ApiParam(value = "第几页") @RequestBody Query query) {
        Page<Product> pageList = productService.findSearch(searchMap, QueryUtil.queryIfNullForNew(query).getPageNo(),
                QueryUtil.queryIfNullForNew(query).getPageSize());
        return R.data(new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "根据条件查询", notes = "传入一个map集合")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public R<List<Product>> findSearch(@ApiParam(value = "集合Map") @RequestBody Map searchMap) {
        return R.data(productService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param productVO
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "添加产品", notes = "传入产品和准入条件")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public R<String> add(@RequestBody ProductVO productVO) {
        productService.add(productVO);
        return R.data("增加成功");
    }

    /**
     * 修改
     *
     * @param productVo
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改", notes = "传入修改条件")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R<String> update(@RequestBody ProductVO productVo) {
        productService.update(productVo);
        return R.data("修改成功");
    }

    /**
     * 删除
     *
     * @param ids
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "批量删除", notes = "传入id字符串")
    @RequestMapping(value = "/removeByIds", method = RequestMethod.DELETE)
    public R<Boolean> removeByIds(@RequestParam("ids") String ids) {
        return R.status(productService.removeByIds(ids));
    }

    /**
     * 修改状态
     *
     * @param product
     * @return
     */
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "更新状态", notes = "传入id和状态")
    @RequestMapping(value = "/updateStatusById", method = RequestMethod.POST)
    public R<String> updateStatusById(@RequestBody Product product) {
        productService.updateStatusById(product.getId(), product.getStatus());
        return R.data("修改成功");
    }

    /**
     * 产品授权
     */
    @ApiLog("产品授权")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "产品授权", notes = "传入productId集合以及loanAgencyId集合")
    @RequestMapping(value = "/grant", method = RequestMethod.POST)
    public R<String> grant(@RequestBody ProductGrant productGrant) {
        boolean bool = productService.grant(Integer.valueOf(productGrant.getProductIds()), Func.toIntList(productGrant.getLoanAgencyIds()));
        return R.status(bool);
    }

    /**
     * 产品名称列表
     */
    @ApiLog("产品名称列表")
    @GetMapping("/getProductNames")
    @ApiOperationSupport(order = 9)
    //@Cacheable(cacheNames = PRODUCT_LIST, key = "#root.method.name")
    @ApiOperation(value = "产品名称列表", notes = "产品名称列表")
    public R<List<ProductNames>> getProductNames() {
        return R.data(productService.getProductNames());
    }

    /**
     * 根据产品ID查询授权机构
     *
     * @param id ID
     * @return
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "根据产品ID查询授权机构", notes = "传入产品Id")
    @RequestMapping(value = "findLoanAgencyIdsById/{id}", method = RequestMethod.GET)
    public R<String> findLoanAgencyIdsById(@PathVariable Integer id) {
        return R.data(productService.findLoanAgencyIdsById(id));
    }
}
