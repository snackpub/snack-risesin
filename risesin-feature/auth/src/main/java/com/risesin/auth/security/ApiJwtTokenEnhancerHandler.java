package com.risesin.auth.security;

import com.risesin.auth.jwt.JwtTokenEnhancerHandler;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * 拓展jwt token里面的信息
 * @author Administrator
 */
@Service
public class ApiJwtTokenEnhancerHandler implements JwtTokenEnhancerHandler {

    @Override
    public HashMap<String, Object> getInfoToToken() {
        HashMap<String, Object> info = new HashMap<String, Object>();
        info.put("author", "张威");
        info.put("company", "awbeci-copy");
        return info;
    }
}
