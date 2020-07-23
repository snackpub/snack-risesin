package com.risesin.systemapi.feign;


import com.risesin.core.launch.constant.AppConstant;
import com.risesin.core.tool.api.R;
import com.risesin.systemapi.enterprise.dto.EnterpriseUserDTO;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 企业用户Feign接口类
 *
 * @author honey
 * @date 2019/12/2
 */
@FeignClient(
        value = AppConstant.APPLICATION_ENTERPRISE_USER_SERVICE,
        fallback = IEnterpriseUserClientFallback.class
)
public interface IEnterpriseUserClient {

    String API_PREFIX = "/enterprise";

    /**
     * 根据ID查询企业用户
     *
     * @param enterpriseUserId 企业用户ID
     * @return R
     */
    @PostMapping("/user/{enterpriseUserId}")
    R<EnterpriseUserDTO> findEnterpriseById(@PathVariable @ApiParam("企业用户id") String enterpriseUserId);

    /**
     * 修改企业用户
     *
     * @param enterpriseUser 企业用户
     * @return R
     */
    @PostMapping("/user")
    R updateEnterpriseUser(@RequestBody @ApiParam("enterpriseUser对象") EnterpriseUserDTO enterpriseUser);

}
