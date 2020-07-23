package com.risesin.risesinftp.core.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("properties.ftp-upload")
@Component
@Data
public class FtpProperties {

    private long delayTime;
    private int saveNumberOnce;
    private int stopSaveTime;
    private String machineId;
    private String username;
    private String password;
    private String hostname;
    private int port;
    private String path_ftp;
//    private String imgPath_suf_jpg;
//    private String pdfPath_suf;
    private boolean isPassive;
    public boolean isPassive() {
        return false;
    }
}
