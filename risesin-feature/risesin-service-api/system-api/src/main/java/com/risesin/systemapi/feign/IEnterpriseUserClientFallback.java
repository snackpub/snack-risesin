package com.risesin.systemapi.feign;

import com.risesin.core.tool.api.R;
import com.risesin.systemapi.enterprise.dto.EnterpriseUserDTO;
import org.springframework.stereotype.Component;

/**
 * 企业用户Feign失败配置
 *
 * @author honey
 * @date 2019/12/2
 */
@Component
public class IEnterpriseUserClientFallback implements IEnterpriseUserClient {
    @Override
    public R<EnterpriseUserDTO> findEnterpriseById(String enterpriseUserId) {
        return R.fail("获取数据失败");
    }

    @Override
    public R updateEnterpriseUser(EnterpriseUserDTO enterpriseUser) {
        return R.fail("获取数据失败");
    }
}
