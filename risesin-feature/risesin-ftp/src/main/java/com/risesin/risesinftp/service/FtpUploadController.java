package com.risesin.risesinftp.service;

import com.risesin.core.log.exception.ServiceException;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.risesinftp.service.entity.File;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @AUTHOR Baby
 * @CREATE 2019/12/23
 * @DESCRIPTION 文件上传
 * @since 1.0.0
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ftp")
@Api(tags = "文件上传服务")
public class FtpUploadController {

    private FtpUploadService ftpUploadService;

    @PostMapping("/uploadFile")
    @ApiOperation(value = "文件上传", notes = "传入文件  ")
    public R<File> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        File add = ftpUploadService.storeFile(file);
        return  R.data(add);
    }

    @PostMapping("/uploadMultipleFiles")
    @ApiOperation(value = "多文件上传", notes = "传入多个文件  ")
    public List<File> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> {
                    try {
                        return ftpUploadService.storeFile(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new ServiceException(ResultCode.FAILURE,e);
                    }
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/download/{fileId}")
    @ApiOperation(value = "下载文件", notes = "下载文件  ")
    public ResponseEntity download(@PathVariable String fileId) throws MalformedURLException {
        File download = ftpUploadService.download(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + download.getFileName()+download.getFileSuffix() + "\"")
                .body(new InputStreamResource(new ByteArrayInputStream(download.getContent())));
    }

    /**
     * 查看文件
     * @param fileId
     * @return
     */
    @GetMapping("/preview/{fileId}")
    @ApiOperation(value = "查看文件", notes = "查看文件")
    public org.springframework.http.ResponseEntity<byte[]> preview(@PathVariable String fileId){
        File fileHasContent = ftpUploadService.preview(fileId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(fileHasContent.getContentType()));
        return new org.springframework.http.ResponseEntity<>(fileHasContent.getContent(), headers, HttpStatus.OK);
    }

}
