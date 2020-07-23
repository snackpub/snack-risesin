package com.risesin.risesinftp.service;

import com.risesin.core.jpaplus.generator.SnowflakeIDGenerator;
import com.risesin.core.log.exception.ServiceException;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.risesinftp.core.properties.FtpProperties;
import com.risesin.risesinftp.core.util.FtpClientEntity;
import com.risesin.risesinftp.service.dao.FileDao;
import com.risesin.risesinftp.service.entity.File;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.*;
import java.util.Base64;
import java.util.Calendar;
import java.util.Objects;
import java.util.function.Function;

/**
 * @AUTHOR Baby
 * @CREATE 2019/12/23
 * @DESCRIPTION 文件上传service
 * @since 1.0.0
 */
@Service
@AllArgsConstructor
public class FtpUploadService {

    private static final Logger log = LoggerFactory.getLogger(FtpUploadService.class);

    private FileDao fileDao;

    /**
     * 上传ftp属性设置
     */
    @Resource
    private FtpProperties ftpProperties;

    /**
     * 存储文件
     * @param file
     * @return
     * @throws IOException
     */
    @Transactional(rollbackOn = Exception.class)
    public File storeFile(MultipartFile file) throws IOException {
        // 生成fileId
        String fileId = SnowflakeIDGenerator.idGenerator();
        // 设置路径存放位置fileId
        String fileDirectory = DateFormatUtils.format(Calendar.getInstance(),"yyyyMMdd");
        String filePath = fileDirectory+ java.io.File.separator+fileId;

        File fileBean = new File();
        fileBean.setFileId(fileId);
        fileBean.setPath(filePath);
        fileBean.setContentType(file.getContentType());
        fileBean.setFileSize(file.getSize());
        // 设置后缀
        int indexOf = file.getOriginalFilename().lastIndexOf(".");
        fileBean.setFileSuffix(file.getOriginalFilename().substring(indexOf));
        fileBean.setFileName(file.getOriginalFilename().substring(0,indexOf));

        // 保存到数据库
        fileDao.save(fileBean);

        // 上传到ftp
        uploadToFtp(filePath,file.getBytes());
        return fileBean;
    }

    /**
     * 查看文件
     * @param fileId
     * @return
     */
    public File preview(String fileId) {
        return this.download(fileId);
    }

    /**
     * 根据id下载文件
     * @param fileId
     * @return
     */
    public File download(String fileId) {
        if(StringUtils.isEmpty(fileId)){
            throw new ServiceException(ResultCode.PARAM_MISS);
        }
        File file = fileDao.findById(File.class, fileId);
        byte[] bytes = this.queryFileByFilePath(file.getPath());
        if(Objects.isNull(bytes) || bytes.length <= 0){
            throw new ServiceException("没有找到此文件");
        }
        // 设置值
        file.setContent(bytes);
        return file;
    }

//    /**
//     * 上传ftp
//     * @param file
//     * @return
//     */
//    public File uploadFtp(File file){
//
//        String filePath = null;
//        if(StringUtils.isEmpty(file.getFileId())){
//            // 生成fileId
//            String fileId = SnowflakeIDGenerator.idGenerator();
//            // 设置路径存放位置fileId
//            String fileDirectory = DateFormatUtils.format(Calendar.getInstance(),"yyyyMMdd");
//            filePath = fileDirectory+ java.io.File.separator+fileId;
//            // 设置值
//            file.setFileId(fileId);
//            file.setPath(filePath);
//        }
//
//        // 上传到ftp
//        uploadToFtp(filePath, file.getContent());
//
//        // 使fileBean中文件流置空,返回去不需要
//        file.setContent(null);
//        return file;
//    }

    /**
     * 使用ftpClient执行具体的方法
     * @param executor
     * @return
     */
    private Object execute(Function<FTPClient,Object> executor){
        String host = ftpProperties.getHostname();
        Integer port = ftpProperties.getPort();
        String username = ftpProperties.getUsername();
        String password = ftpProperties.getPassword();

        FTPClient ftpClient = new FTPClient();
        try{


            ftpClient.connect(host,port);
            int reply = ftpClient.getReplyCode();

            if(!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                log.error("ftp connect error,error detail: "+ftpClient.getReplyString());
                throw new ServiceException("ftp连接失败");
            }

            if(!ftpClient.login(username,password)){
                log.error("ftp login error");
                throw new ServiceException("ftp login error");
            }

            // 设置为被动模式
            if(ftpProperties.isPassive()){
                ftpClient.enterLocalPassiveMode();
                if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
                    throw new ServiceException("ftp fail to switch to positive,detail: "+ftpClient.getReplyString());
                }
            }


            if (!ftpClient.setFileType(FTP.BINARY_FILE_TYPE)) {
                throw new ServiceException("ftp设置fileType失败");
            }

            Object result = executor.apply(ftpClient);

            ftpClient.logout();

            return result;
        }catch(IOException e){
            log.error("ftp connect error",e);
            throw new ServiceException("ftp连接失败");
        }finally {
            try{
                ftpClient.disconnect();
            }catch (Exception e){
                log.error("ftp disconnect error",e);
            }
        }
    }

    /**
     * 上传文件至ftp服务器
     * @param filePath 文件上传路径
     * @param bytes 文件
     */
    private void uploadToFtp(String filePath, byte[] bytes) {
        execute((ftpClient)->{

            // 存储文件至ftp
            try {
                //检查上传路径是否存在 如果不存在返回false
                String fileDirectory = filePath.substring(0,filePath.lastIndexOf(java.io.File.separator));
                String fileName = filePath.substring(filePath.lastIndexOf(java.io.File.separator)+1);
                boolean flag = ftpClient.changeWorkingDirectory(fileDirectory);
                if(!flag){
                    //创建上传的路径  该方法只能创建一级目录
                    ftpClient.makeDirectory(fileDirectory);
                    //改变工作空间 指定上传路径
                    ftpClient.changeWorkingDirectory(fileDirectory);
                }

                OutputStream outputStream = ftpClient.storeFileStream(fileName);
                if(outputStream == null){
                    //改变工作空间 指定上传路径
                    ftpClient.changeWorkingDirectory(fileDirectory);
                    outputStream = ftpClient.storeFileStream(fileName);
                }
                if(outputStream == null){
                    throw new ServiceException("上传ftp出现异常，详细信息"+(ftpClient.getReplyString()==null?"为空":ftpClient.getReplyString()));
                }
                outputStream.write(bytes);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                throw new ServiceException(ResultCode.FAILURE,e);
            }

            return null;
        });
    }

    /**
     * 从ftp服务器查找文件
     * @return
     */
    private byte[] queryFileByFilePath(String filePath){
        byte[] bytes = (byte[]) execute((ftpClient -> {
            byte[] files = null;
            try {
                // 获取上传目录
                String fileDirectory = filePath.substring(0,filePath.lastIndexOf(java.io.File.separator));
                // 改变模式
                ftpClient.enterLocalPassiveMode();
                // 改变工作空间
                ftpClient.changeWorkingDirectory(fileDirectory);

                String fileName = filePath.substring(filePath.lastIndexOf(java.io.File.separator)+1);
                String name = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
                // 获取文件流
                InputStream inputStream = ftpClient.retrieveFileStream(name);
                if(Objects.isNull(inputStream)){
                    log.error("查询ftp文件失败,没有查询到此文件,filePath:"+ filePath);
                    return null;
                }
                files = IOUtils.toByteArray(inputStream);
            } catch (IOException e) {
                log.error("查询ftp文件失败",e);
                throw new ServiceException(ResultCode.FAILURE,e);
            }
            return files;
        }));
        return bytes;
    }

    /**
     * base64字符串转化成图片上传到FTP
     * @param token
     * @param file
     * @param suffix
     * @param fileName
     * @return
     */
    @Async
    public String GenerateImageToFTP(String token,byte[] file,String suffix,String fileName) {
        //存放路径
        String filePath ;
        //连接ftp服务器
        FtpClientEntity fTPclientEntity = new FtpClientEntity();
        FTPClient ftp = fTPclientEntity.getConnectionFTP(ftpProperties.getHostname(),ftpProperties.getPort(),ftpProperties.getUsername(), ftpProperties.getPassword());
        //设置上传路径
        String path_ftp = ftpProperties.getPath_ftp() + token;

        //对strs遍历
        //对字节数组字符串进行Base64解码并生成图片
        if (file == null) {
            throw new ServiceException(ResultCode.PARAM_VALID_ERROR);
        }
        // 获取解密对象
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            //创建输入流
            InputStream fis = new ByteArrayInputStream(file);
            //上传文件
            fTPclientEntity.uploadFile(ftp, path_ftp, fileName+suffix, fis);
            filePath = path_ftp + "/" + fileName;
        } catch (Exception e) {
            log.error("文件上传ftp错误,token="+token,e);
            throw new ServiceException(ResultCode.FAILURE);
        }
        //退出ftp
        fTPclientEntity.closeFTP(ftp);

        return filePath;
    }

    /**
     *  判断ftp服务器文件是否存在
     * @param filePath
     * @return
     * @throws IOException
     */
    public boolean existFile(String filePath) throws IOException {

        // 获取上传目录
        String fileDirectory = filePath.substring(0,filePath.lastIndexOf(java.io.File.separator));
        String fileName = filePath.substring(filePath.lastIndexOf(java.io.File.separator)+1);

        boolean execute = (boolean) execute((ftpClient) -> {
            boolean flag = false;
            FTPFile[] ftpFileArr = new FTPFile[0];
            try {
                ftpFileArr = ftpClient.listFiles(fileDirectory+ java.io.File.separator+ new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
            } catch (IOException e) {
                log.error("查询ftp文件失败", e);
            }
            if (ftpFileArr.length > 0) {
                flag = true;
            }
            return flag;
        });
        return execute;
    }

}
