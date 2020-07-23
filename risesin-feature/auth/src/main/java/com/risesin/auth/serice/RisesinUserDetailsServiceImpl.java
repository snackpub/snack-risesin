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
package com.risesin.auth.serice;

import com.risesin.auth.RisesinUserDetails;
import com.risesin.auth.utils.TokenUtil;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.utils.DigestUtil;
import com.risesin.core.tool.utils.Func;
import com.risesin.user.entity.User;
import com.risesin.user.entity.UserInfo;
import com.risesin.user.feign.IUserClient;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 用户信息
 *
 * @author honey
 * @date 2019/12/24 15:44
 */
@Service
@AllArgsConstructor
public class RisesinUserDetailsServiceImpl implements UserDetailsService {

    private IUserClient userClient;

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        R<UserInfo> result = userClient.userInfo(DigestUtil.encrypt(phone));
        if (result.isSuccess()) {
            User user = result.getData().getUser();
            if (user == null) {
                throw new UsernameNotFoundException(TokenUtil.PHONE_NOT_FOUND);
            }
            return new RisesinUserDetails(user.getId(), user.getRoleId(), Func.join(result.getData().getRoles()),
                    TokenUtil.DEFAULT_AVATAR, user.getUsername(), phone, true, true, true, true,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(Func.join(result.getData().getRoles())), user.getAccount());
        } else {
            throw new UsernameNotFoundException(result.getMsg());
        }

    }
}
