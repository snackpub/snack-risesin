package com.risesin.enterpriseuserservice.service.userLogin;

import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.utils.StringUtil;
import com.risesin.enterprise.service.EnterpriseUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @AUTHOR Darling
 * @CREATE 2019-10-14
 * @DESCRIPTION 企业用户登录
 * @since 1.0.0
 */

@Api(tags = "企业用户登录和注册")
@RestController
@CrossOrigin //解决跨域问题
@AllArgsConstructor
public class UserLoginController {

    private EnterpriseUserService enterpriseUserService;

    /**
     * 发送短信验证码
     *
     * @param mobile 11位手机号
     * @return json
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "获取短信验证码", notes = "传入11位手机号")
    @GetMapping("/sendsms/{mobile}")
    public R sendSms(@PathVariable @ApiParam("11位手机号") String mobile) {
        enterpriseUserService.sendSms(mobile);
        return R.success(ResultCode.SUCCESS);
    }

    /**
     * 用户登录与注册
     *
     * @param mobile    11位手机号
     * @param checkCode 验证码
     * @return R
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "企业用户登录与注册", notes = "传入11位手机号和验证码")
    @PostMapping("/login")
    public R login(@RequestParam @ApiParam("11位手机号") String mobile, @RequestParam @ApiParam("验证码") String checkCode) {
        if (StringUtil.isNotBlank(mobile) && StringUtil.isNotBlank(checkCode)) {
            if (enterpriseUserService.loginOrResgiter(mobile, checkCode) != null) {
                //redis缓存中存在该验证码,该验证码有效
                return R.success(ResultCode.SUCCESS);
            }
            //redis缓存中不存在该验证码,该验证码无效
            return R.fail(603, "验证码无效,请重新获取验证码!");
        }
        return R.fail("请求失败");
    }
}
