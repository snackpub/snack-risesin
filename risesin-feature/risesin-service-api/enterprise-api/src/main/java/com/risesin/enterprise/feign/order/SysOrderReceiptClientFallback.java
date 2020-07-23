package com.risesin.enterprise.feign.order;

import com.risesin.core.tool.api.R;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-24
 * @DESCRIPTION TODO
 * @since 1.0.0
 */
@Component
public class SysOrderReceiptClientFallback implements ISysOrderReceiptClient{


    @Override
    public R<String> paymentReceipt2(Map sysOrderReceipt) {
        return R.fail("上传失败");
    }

    @Override
    public R receiptDetail(String id) {
        return R.fail("查询失败");
    }

}
