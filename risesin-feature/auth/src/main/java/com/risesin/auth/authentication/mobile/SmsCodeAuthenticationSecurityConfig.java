package com.risesin.auth.authentication.mobile;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * * 自定义验证密码或者验证码
 * https://blog.csdn.net/tzdwsy/article/details/50738043
 * https://blog.csdn.net/xiejx618/article/details/42609497
 * https://blog.csdn.net/jiangshanwe/article/details/73842143
 * https://longfeizheng.github.io/2018/01/14/Spring-Security%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90%E4%BA%94-Spring-Security%E7%9F%AD%E4%BF%A1%E7%99%BB%E5%BD%95/
 * https://cloud.tencent.com/developer/article/1040105
 *
 * @author Administrator
 */
@Component
@AllArgsConstructor
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private AuthenticationSuccessHandler risesinAuthenticationSuccessHandler;
    private AuthenticationFailureHandler risesinAuthenticationFailureHandler;
    private UserDetailsService userDetailsService;
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void configure(HttpSecurity http) {

        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(risesinAuthenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(risesinAuthenticationFailureHandler);

        SmsCodeAuthenticationProvider smsCodeDaoAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeDaoAuthenticationProvider.setUserDetailsService(userDetailsService);
        smsCodeDaoAuthenticationProvider.setRedisTemplate(redisTemplate);
        http.authenticationProvider(smsCodeDaoAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
