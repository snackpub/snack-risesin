/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.risesin.auth.controller;

import com.risesin.auth.granter.ITokenGranter;
import com.risesin.auth.granter.TokenGranterBuilder;
import com.risesin.auth.granter.TokenParameter;
import com.risesin.auth.utils.TokenUtil;
import com.risesin.core.secure.AuthInfo;
import com.risesin.core.secure.TokenMobileInfo;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.utils.Func;
import com.risesin.core.tool.utils.WebUtil;
import com.risesin.user.entity.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 认证模块
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@Api(value = "用户授权认证", tags = "授权接口")
public class AuthController {

    @GetMapping("/token")
    @ApiOperation(value = "获取密码验证认证token", notes = "传入账号:account,手机号:phone")
    public R<AuthInfo> token(@ApiParam(value = "授权类型", required = true) @RequestParam(defaultValue = "custom", required = false) String grantType,
                             @ApiParam(value = "刷新令牌") @RequestParam(required = false) String refreshToken,
                             @ApiParam(value = "账号") @RequestParam(required = false) String account,
                             @ApiParam(value = "手机号") @RequestParam(required = false) String phone) {

        String userType = Func.toStr(WebUtil.getRequest().getHeader(TokenUtil.USER_TYPE_HEADER_KEY), TokenUtil.DEFAULT_USER_TYPE);

        TokenParameter tokenParameter = new TokenParameter();
        tokenParameter.getArgs().set("account", account).set("phone", phone).set("grantType", grantType).set("refreshToken", refreshToken).set("userType", userType);


        ITokenGranter granter = TokenGranterBuilder.getGranter(grantType);
        UserInfo userInfo = granter.grant(tokenParameter);

        if (userInfo == null || userInfo.getUser() == null || userInfo.getUser().getId() == null) {
            return R.fail(TokenUtil.USER_NOT_FOUND);
        }

        return R.data(TokenUtil.createAuthInfo(userInfo));
    }

    @PostMapping("/token/mobile")
    @ApiOperation(value = "获取短信登陆认证token", notes = "传入手机号:phone,验证码:smsCode")
    public R<AuthInfo> tokenMobile(@RequestBody TokenMobileInfo info) {

        String userType = Func.toStr(WebUtil.getRequest().getHeader(TokenUtil.USER_TYPE_HEADER_KEY), TokenUtil.DEFAULT_USER_TYPE);

        TokenParameter tokenParameter = new TokenParameter();
        tokenParameter.getArgs().set("phone", info.getPhone()).set("smsCode", info.getSmsCode()).set("grantType", info.getGrantType()).set("refreshToken", info.getRefreshToken()).set("userType", userType);


        ITokenGranter granter = TokenGranterBuilder.getGranter(info.getGrantType());
        UserInfo userInfo = granter.grant(tokenParameter);

        if (userInfo == null || userInfo.getUser() == null || userInfo.getUser().getId() == null) {
            return R.fail(TokenUtil.USER_NOT_FOUND);
        }

        return R.data(TokenUtil.createAuthInfo(userInfo));
    }

}
