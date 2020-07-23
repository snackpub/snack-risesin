package com.risesin.enterpriseuserservice.service.properties;

import com.risesin.core.tool.utils.UUIDByTimeUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.time.Instant;


/**
 * @AUTHOR Baby
 * @CREATE 2019/9/24
 * @DESCRIPTION 网易云信配置
 * @since 1.0.0
 */
@ConfigurationProperties("risesin-properties.yunxin")
@Component
@Data
public class YunxinProperties {
    private String appKey; // 网易云信key
    private String appSecret; // 网易云信 秘钥
    private String url; // 网易云信访问地址
    private final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    // 计算并获取CheckSum
    public String getCheckSum() {
        return encode("sha1", appSecret + UUIDByTimeUtils.getUUIDString() + String.valueOf(Instant.now().toEpochMilli() / 1000));
    }

    // 计算并获取md5值
    public String getMD5(String requestBody) {
        return encode("md5", requestBody);
    }

    private String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

}
