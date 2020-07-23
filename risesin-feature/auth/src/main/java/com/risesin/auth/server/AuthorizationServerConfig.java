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
package com.risesin.auth.server;

import com.risesin.auth.RisesinUserDetails;
import com.risesin.auth.constant.AuthConstant;
import com.risesin.auth.provider.BaseIClientDetailsService;
import com.risesin.auth.serice.RisesinClientDetailsService;
import com.risesin.core.tool.utils.Func;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证服务
 *
 * @author honey
 * @date 2019/12/25 13:01
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final DataSource dataSource;

    private final AuthenticationManager authenticationManager;
    private final RedisConnectionFactory redisConnectionFactory;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .allowFormAuthenticationForClients()
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancer())
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager)
                .reuseRefreshTokens(false);
//                .exceptionTranslator(new PigWebResponseExceptionTranslator());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        RisesinClientDetailsService clientDetailsService = new RisesinClientDetailsService(dataSource);
        clientDetailsService.setFindClientDetailsSql(AuthConstant.DEFAULT_FIND_STATEMENT);
        clientDetailsService.setSelectClientDetailsSql(AuthConstant.DEFAULT_SELECT_STATEMENT);
        clients.withClientDetails(clientDetailsService);
    }


    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(AuthConstant.PROJECT_PREFIX + AuthConstant.OAUTH_PREFIX);
        return tokenStore;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>(4);
            RisesinUserDetails pigUser = (RisesinUserDetails) authentication.getUserAuthentication().getPrincipal();
            additionalInfo.put(AuthConstant.DETAILS_LICENSE, AuthConstant.DETAILS_LICENSE);
            additionalInfo.put(AuthConstant.DETAILS_USER_ID, pigUser.getUserId());
            additionalInfo.put(AuthConstant.DETAILS_USERNAME, pigUser.getUsername());
            additionalInfo.put(AuthConstant.DETAILS_DEPT_ID, "111");
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }

}
