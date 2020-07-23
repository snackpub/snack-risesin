/**
 * Copyright (c) 2018-2028, Chill Zhuang (smallchill@163.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.risesin.auth.provider;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;
import java.util.Set;


/**
 * 客户端详情
 *
 * @author Chill
 */
@Data
public class ApplyClientDetails implements BaseIClientDetails {

    @ApiModelProperty("客户端id")
    private String clientId;
    @ApiModelProperty("客户端密钥")
    private String clientSecret;
    @ApiModelProperty("令牌过期秒数")
    private Integer accessTokenValidity;
    @ApiModelProperty("刷新令牌过期秒数")
    private Integer refreshTokenValidity;


    private Set<String> resourceIds;

    private boolean secretRequired;

    private boolean scoped;

    private Set<String> scope;

    private Set<String> authorizedGrantTypes;

    private Set<String> registeredRedirectUri;

    private Collection<GrantedAuthority> authorities;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private boolean autoApprove;

    private Map<String, Object> additionalInformation;

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }
}
