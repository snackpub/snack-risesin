package com.risesin.auth.granter;

import com.risesin.auth.enums.RisesinUserEnum;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.utils.DigestUtil;
import com.risesin.core.tool.utils.Func;
import com.risesin.user.entity.UserInfo;
import com.risesin.user.feign.IUserClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * SmsTokenGranter 短信令牌授权
 *
 * @author Honey
 */
@Component
@AllArgsConstructor
public class SmsTokenGranter implements ITokenGranter {

    public static final String GRANT_TYPE = "sms";

    private static final String SMS_CODE_ERROR = "验证码错误!";

    private IUserClient userClient;

    @Override
    public UserInfo grant(TokenParameter tokenParameter) {

        String phone = tokenParameter.getArgs().getStr("phone");
        String smsCode = tokenParameter.getArgs().getStr("smsCode");
        UserInfo userInfo = null;
        if (Func.isNoneBlank(phone, smsCode)) {
            // 校验验证码 TODO
            if (!smsCode.equals("123456")) {
                throw new SecurityException(String.format("%s", SMS_CODE_ERROR));
            }
            // 获取用户类型
            String userType = tokenParameter.getArgs().getStr("userType");
            R<UserInfo> result;
            // 根据不同用户类型调用对应的接口返回数据，用户可自行拓展
            if (userType.equals(RisesinUserEnum.WEB.getName())) {
                result = userClient.userInfo(DigestUtil.encrypt(phone));
            } else if (userType.equals(RisesinUserEnum.APP.getName())) {
                result = userClient.userInfo(DigestUtil.encrypt(phone));
            } else {
                result = userClient.userInfo(DigestUtil.encrypt(phone));
            }
            userInfo = result.isSuccess() ? result.getData() : null;
        }
        return userInfo;
    }

}
