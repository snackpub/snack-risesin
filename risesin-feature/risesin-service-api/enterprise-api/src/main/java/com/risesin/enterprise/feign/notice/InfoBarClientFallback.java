package com.risesin.enterprise.feign.notice;

import com.risesin.core.tool.api.R;
import com.risesin.enterprise.infoBar.vo.IncompleteCountVO;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-20
 * @DESCRIPTION 信息栏回调类
 * @since 1.0.0
 */

@Component
public class InfoBarClientFallback implements IInfoBarClient {

    @Override
    public R<IncompleteCountVO> getTopBar(String enterpriseUserId) {
        return R.fail("查询失败");
    }

    @Override
    public R getInfoBar(String enterpriseUserId) {
        return R.fail("查询失败");
    }
}
