package com.risesin.auth.authentication.mobile;

import lombok.Setter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.concurrent.TimeUnit;

/**
 * 身份验证提供者
 *
 * @author honey
 * @date 2019
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    @Setter
    private RedisTemplate<Object, Object> redisTemplate;

    @Setter
    private UserDetailsService userDetailsService;


    /**
     * 认证用户信息
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        String mobile = authentication.getName();
        String smsCode = (String) authenticationToken.getCredentials();
        redisTemplate.opsForValue().set(mobile, smsCode, 10, TimeUnit.MINUTES);
        String smsCodeFromRedis = (String) redisTemplate.opsForValue().get(mobile);
        if (!smsCode.equals(smsCodeFromRedis)) {
            throw new InternalAuthenticationServiceException("手机验证码不正确");
        }

        UserDetails user = userDetailsService.loadUserByUsername(mobile);
        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, null, user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
