package com.risesin.auth.authentication.mobile;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 短信验证码拦截器
 *
 * @author h
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private boolean postOnly = true;

    /**
     * 手机号参数变量
     */
    private String mobileParameter = "mobile";
    /**
     * 验证码
     */
    private String smsCode = "smsCode";

    SmsCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/mobile/token", "POST"));
    }

    /**
     * 添加未认证用户认证信息，然后在provider里面进行正式认证
     *
     * @param httpServletRequest  Request
     * @param httpServletResponse Response
     * @return Authentication
     * @throws AuthenticationException 认证异常
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws AuthenticationException {
        if (postOnly && !httpServletRequest.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + httpServletRequest.getMethod());
        }

        String mobile = obtainMobile(httpServletRequest);
        String smsCode = obtainSmsCode(httpServletRequest);
        if (mobile == null) {
            mobile = "";
        }

        mobile = mobile.trim();

        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile, smsCode);
        // Allow subclasses to set the "details" property
        setDetails(httpServletRequest, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 获取手机号
     */
    private String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }

    private String obtainSmsCode(HttpServletRequest request) {
        return request.getParameter(smsCode);
    }

    private void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setMobileParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.mobileParameter = usernameParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getMobileParameter() {
        return mobileParameter;
    }
}
