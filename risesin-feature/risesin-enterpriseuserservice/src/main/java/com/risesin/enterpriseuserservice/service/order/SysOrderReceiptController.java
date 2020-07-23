package com.risesin.enterpriseuserservice.service.order;

import com.risesin.core.tool.api.R;
import com.risesin.enterprise.feign.order.ISysOrderReceiptClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
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

   private ISysOrderReceiptClient sysOrderReceiptClient;

    /**
     * 填写付款回执(C端调用)
     *
     * @param sysOrderReceipt
     * @return
     */
    @ApiOperation(value = "上传付款回执(C端调用)", notes = "回执信息")
    @PostMapping("/paymentReceipt")
    public R<String> paymentReceipt(@RequestBody Map sysOrderReceipt) {
        return sysOrderReceiptClient.paymentReceipt2(sysOrderReceipt);
    }

    /**
     * 查看付款回执详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查看付款回执详情", notes = "订单id")
    @PostMapping("/receiptDetail/{id}")
    public R paymentReceipt(@ApiParam("订单id") @PathVariable String id) {
        return sysOrderReceiptClient.receiptDetail(id);
    }

}
