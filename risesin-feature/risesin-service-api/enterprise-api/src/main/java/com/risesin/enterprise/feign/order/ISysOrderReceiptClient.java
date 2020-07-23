package com.risesin.enterprise.feign.order;

import com.risesin.core.launch.constant.AppConstant;
import com.risesin.core.tool.api.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(
        value = AppConstant.APPLICATION_SYSTEM_NAME,
        fallback = SysOrderReceiptClientFallback.class
)
public interface ISysOrderReceiptClient {

    String API_PREFIX = "/receipt";

    /**
     * 填写付款回执2(C端调用)
     *
     * @param sysOrderReceipt
     * @return
     */
    @ApiOperation(value = "上传付款回执(C端调用)", notes = "回执信息")
    @PostMapping(API_PREFIX + "/paymentReceipt2")
    public R<String> paymentReceipt2(@RequestBody Map sysOrderReceipt);

    /**
     * 查看付款回执详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查看付款回执详情", notes = "订单id")
    @PostMapping(API_PREFIX + "/receiptDetail/{id}")
    R receiptDetail(@ApiParam("订单id") @PathVariable String id);

}
