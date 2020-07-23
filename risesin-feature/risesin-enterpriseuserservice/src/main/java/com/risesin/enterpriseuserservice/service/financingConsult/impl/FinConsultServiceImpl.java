package com.risesin.enterpriseuserservice.service.financingConsult.impl;

import com.risesin.core.tool.utils.UUIDByTimeUtils;
import com.risesin.enterpriseuserservice.service.financingConsult.FinConsultService;
import com.risesin.enterpriseuserservice.service.properties.YunxinProperties;
import com.risesin.enterpriseuserservice.util.httpclient.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * @AUTHOR Baby
 * @CREATE 2019/9/24
 * @DESCRIPTION 网易云信服务
 * @since 1.0.0
 */
@Service
public class FinConsultServiceImpl implements FinConsultService {
    @Autowired
    private YunxinProperties yxPro;

    /**
     * 调用云信接口
     */
    @Override
    public void callYunxin() {
        try {
            // 设置http head头部
            Map<String, String> headerParams = new HashMap<>();
            headerParams.put("AppKey", yxPro.getAppKey());
            headerParams.put("Nonce", UUIDByTimeUtils.getUUIDString());
            headerParams.put("CurTime", String.valueOf(Instant.now().toEpochMilli() / 1000));
            headerParams.put("CheckSum", yxPro.getCheckSum());
            headerParams.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            // 设置参数
            Map<String, String> params = new HashMap<>();
            params.put("accid", "longtime");
            params.put("name", "longtime");

            // 发送请求
            String response = HttpRequestUtil.post(yxPro.getUrl(), headerParams, params);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
