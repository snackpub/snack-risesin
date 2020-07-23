package com.risesin.auth.config;

import com.risesin.auth.jwt.JwtTokenEnhancer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.StringUtils;

/**
 * @author Administrator
 */
@Configuration
@ConditionalOnProperty(
        prefix = "risesin.security.oauth2",
        name = "storeType",
        havingValue = "jwt",
        matchIfMissing = true)
public class JwtTokenStoreConfig {

    @Value("${risesin.security.jwt.signingKey}")
    private String signingkey;

    @Bean
    public TokenEnhancer jwtTokenEnhancer() {
        return new JwtTokenEnhancer();
    }

    @Bean
    public TokenStore jetTokenStroe() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //设置默认值
        if (StringUtils.isEmpty(signingkey)) {
            signingkey = "risesinSign";
        }
        //密钥，放到配置文件中
        jwtAccessTokenConverter.setSigningKey(signingkey);
        return jwtAccessTokenConverter;
    }
}
