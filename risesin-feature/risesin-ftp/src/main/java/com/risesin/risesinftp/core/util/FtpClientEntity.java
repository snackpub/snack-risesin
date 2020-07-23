package com.risesin.risesinftp.core.util;

import org.apache.commons.net.ftp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件上传至FTP服务器
 * @author Baby
 */
@Component
public class FtpClientEntity {

    private static final Logger logger = LoggerFactory.getLogger(FtpClientEntity.class);

    /**
     * 连接FTP服务器
     * @param hostName
     * @param port
     * @param userName
     * @param passWord
     * @return
     */
    public FTPClient getConnectionFTP(String hostName, int port, String userName, String passWord) {
        //创建FTPClient对象
        FTPClient ftp = new FTPClient();
        try {
            //连接FTP服务器
            ftp.connect(hostName, port);
            //下面三行代码必须要，而且不能改变编码格式，否则不能正确下载中文文件
            ftp.setControlEncoding("GBK");
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
            conf.setServerLanguageCode("zh");
            //登录ftp
            ftp.login(userName, passWord);
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                ftp.disconnect();
                logger.info("连接服务器失败");
            }
            logger.info("登陆服务器成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftp;
    }

    /**
     * 关闭ftp
     * @param ftp
     * @return
     */
    public boolean closeFTP(FTPClient ftp) {
        if (ftp.isConnected()) {
            try {
                ftp.disconnect();
                logger.info("ftp已经关闭");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

   /**
     *  上传文件FTP方式
     * @param ftp FTPClient对象
     * @param path FTP服务器上传地址
     * @param fileName 本地文件路径
     * @param inputStream 输入流
     * @return boolean
     */
    public boolean uploadFile(FTPClient ftp, String path, String fileName, InputStream inputStream) {
        boolean success = false;
        try {
            //检查上传路径是否存在 如果不存在返回false
            boolean flag = ftp.changeWorkingDirectory(path);
            if(!flag){
                //创建上传的路径  该方法只能创建一级目录
                ftp.makeDirectory(path);
            }
            //改变工作空间 指定上传路径
            ftp.changeWorkingDirectory(path);
            //如果缺省该句 传输txt正常 但图片和其他格式的文件传输出现乱码
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传
            ftp.storeFile(fileName, inputStream);
            //关闭输入流
            inputStream.close();
            //表示上传成功
            success = true;
            logger.info("上传成功。。。。。。");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

}
