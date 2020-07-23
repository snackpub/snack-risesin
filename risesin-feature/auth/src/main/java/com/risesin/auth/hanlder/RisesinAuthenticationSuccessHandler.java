/*
 *  Copyright (c) 2020-2022, Qiuhaijun (3524422399@qq.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.risesin.auth.hanlder;

import com.risesin.auth.RisesinUserDetails;
import com.risesin.auth.utils.TokenUtil;
import com.risesin.core.secure.AuthInfo;
import com.risesin.core.tool.constant.RisesinConstant;
import com.risesin.core.tool.jackson.JsonUtil;
import com.risesin.core.tool.utils.*;
import com.risesin.user.entity.User;
import com.risesin.user.entity.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 身份认证成功处理
 *
 * @author honey
 * @date 2019/12/26 16:35
 */
@Component("risesinAuthenticationSuccessHandler")
public class RisesinAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserInfo userInfo = createUserInfo(authentication);
        AuthInfo authInfo = TokenUtil.createAuthInfo(userInfo);
        response.setContentType(RisesinConstant.CONTENT_TYPE);
        response.getWriter().write(JsonUtil.toJson(authInfo));
    }

    private UserInfo createUserInfo(Authentication authentication) {
        RisesinUserDetails principal = (RisesinUserDetails) authentication.getPrincipal();
        String roleIds = principal.getRoleIds();
        User user = BeanUtil.copy(principal, User.class);
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);
        userInfo.setRoles(Func.toStrList(roleIds));
        return userInfo;
    }
}
