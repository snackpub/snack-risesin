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

import com.risesin.auth.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.risesin.auth.exception.PermitAllUrlConfiguration;
import com.risesin.auth.exception.PigUserAuthenticationConverter;
import com.risesin.auth.exception.ResourceAuthExceptionEntryPoint;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.client.RestTemplate;

/**
 * 资源服务
 *
 * @author honey
 * @date 2019/12/26 15:53
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    protected ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;
    @Autowired
    protected RemoteTokenServices remoteTokenServices;
    @Autowired
    private AccessDeniedHandler pigAccessDeniedHandler;
    @Autowired
    private PermitAllUrlConfiguration permitAllUrl;
    @Autowired
    private RestTemplate lbRestTemplate;

    /**
     * 默认的配置，对外暴露
     *
     * @param httpSecurity
     */
    @Override
    @SneakyThrows
    public void configure(HttpSecurity httpSecurity) {
        //允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
        httpSecurity.headers().frameOptions().disable();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>
                .ExpressionInterceptUrlRegistry registry = httpSecurity
                .authorizeRequests();
        permitAllUrl.getUrls()
                .forEach(url -> registry.antMatchers(url).permitAll());
        registry.anyRequest().authenticated()
                .and().csrf().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        UserAuthenticationConverter userTokenConverter = new PigUserAuthenticationConverter();
        accessTokenConverter.setUserTokenConverter(userTokenConverter);

        remoteTokenServices.setRestTemplate(lbRestTemplate);
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
        resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint)
                .accessDeniedHandler(pigAccessDeniedHandler);
    }
}
