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
package com.risesin.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


/**
 * 用户信息信息详情
 *
 * @author honey
 * @date 2019/12/25 11:42
 */
@Data
@AllArgsConstructor
public class RisesinUserDetails implements UserDetails {

    @ApiModelProperty(hidden = true)
    private String userId;

    /**
     * 角色ID
     */
    @ApiModelProperty(hidden = true)
    private String roleId;

    private String roleIds;

    private String defaultAvatar;

    private String username;
    private String password;


    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private Collection<? extends GrantedAuthority> authorities;

    private String account;
}
