package com.risesin.enterprise.feign.notice;

import com.risesin.core.launch.constant.AppConstant;
import com.risesin.core.tool.api.R;
import com.risesin.enterprise.infoBar.vo.IncompleteCountVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @AUTHOR Darling
 * @CREATE 2019-11-29
 * @DESCRIPTION 首页信息栏（融资预案、公告、日志）feign接口类
 * @since 1.0.0
 */
@FeignClient(
        value = AppConstant.APPLICATION_SYSTEM_NAME,
        fallback = InfoBarClientFallback.class
)
public interface IInfoBarClient {

    String API_PREFIX = "/infobar";

    @ApiOperation(value = "首页公告、日志、预案", notes = "传入enterpriseUserId")
    @GetMapping(API_PREFIX + "/show/{enterpriseUserId}")
    R getInfoBar(@PathVariable String enterpriseUserId);

    @ApiOperation(value = "首页统计方案、订单、支付", notes = "传入enterpriseUserId")
    @GetMapping(API_PREFIX + "/count/{enterpriseUserId}")
    R<IncompleteCountVO> getTopBar(@PathVariable String enterpriseUserId);

}
