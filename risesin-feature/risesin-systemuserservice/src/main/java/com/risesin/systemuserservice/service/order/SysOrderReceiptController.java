package com.risesin.systemuserservice.service.order;

import com.risesin.core.jpaplus.generator.SnowflakeIDGenerator;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.jackson.JsonUtil;
import com.risesin.system.service.order.impl.SysOrderReceiptServiceImpl;
import com.risesin.systemapi.order.entity.SysOrderReceipt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-08
 * @DESCRIPTION 订单回执
 * @since 1.0.0
 */
@Api(tags = "订单回执")
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/receipt")
public class SysOrderReceiptController {

    private SysOrderReceiptServiceImpl sysOrderReceiptService;

    /**
     * 填写付款回执(C端调用)
     *
     * @param sysOrderReceiptMap
     * @return
     */
    @ApiOperation(value = "上传付款回执(C端调用)", notes = "回执信息")
    @PostMapping("/paymentReceipt")
    public R<String> paymentReceipt(@RequestBody Map sysOrderReceiptMap) {
        SysOrderReceipt sysOrderReceipt = JsonUtil.toPojo(sysOrderReceiptMap, SysOrderReceipt.class);
        //赋值code
        sysOrderReceipt.setReceiptCode(SnowflakeIDGenerator.idGenerator());
        sysOrderReceiptService.paymentReceipt(sysOrderReceipt);
        return R.data("操作成功");
    }

    /**
     * 查看付款回执详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查看付款回执详情", notes = "订单id")
    @PostMapping("/receiptDetail/{id}")
    public R<SysOrderReceipt> receiptDetail(@ApiParam("订单id") @PathVariable String id) {
        if (StringUtils.isBlank(id)) {
            return R.fail("id不能为空。");
        }
        return R.data(sysOrderReceiptService.findByOrderCode(id));
    }

}
