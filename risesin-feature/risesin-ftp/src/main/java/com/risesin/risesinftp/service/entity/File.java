package com.risesin.risesinftp.service.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @AUTHOR Baby
 * @CREATE 2019/12/23
 * @DESCRIPTION ftpBean
 * @since 1.0.0
 */
@Data
@Entity
@DynamicInsert
@ApiModel("文件信息")
@Table(name = "t_file")
@EntityListeners(AuditingEntityListener.class)
public class File {

    /**
     * pk_id
     */
    @Id
    @Column(name = "id")
    @ApiModelProperty("文件id,唯一标识")
    private String fileId;
    /**
     * / 上传文件名称
     */
    @ApiModelProperty("上传文件名称,必传")
    @Column(name = "file_name")
    private String fileName;
    /**
     * / 文件后缀
     */
    @ApiModelProperty("文件后缀")
    @Column(name = "file_suffix")
    private String fileSuffix;
    /**
     * / 文件后缀
     */
    @ApiModelProperty("文件内容")
    @Column(name = "content")
    private byte[] content;
    /**
     * / 文件大小
     */
    @ApiModelProperty("文件大小")
    @Column(name = "file_size")
    private long fileSize;
    /**
     * / 保存路径
     */
    @ApiModelProperty("保存路径")
    @Column(name = "path")
    private String path;
    /**
     * / 文件类型
     */
    @ApiModelProperty("文件类型")
    @Column(name = "content_type")
    private String contentType;

    /**
     * 创建时间
     */
    @CreatedDate
    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @ApiModelProperty("更新时间")
    @Column(name = "update_time")
    private LocalDateTime updateTime;


}