package com.risesin.auth.jwt;

import java.util.HashMap;

/**
 * 提供给外部实现调用的接口
 * @author Administrator
 */
public interface JwtTokenEnhancerHandler {

    HashMap<String,Object> getInfoToToken();
}
