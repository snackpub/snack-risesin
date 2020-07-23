package com.risesin.systemapi.ext.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * extFileData实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_ext_file_data")
@Data
@ApiModel("文件资料拓展")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class ExtFileData implements Serializable {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("资料拓展表id")
    private Integer id;

    /**
     * 子方案code
     */
    @Column(name = "fk_child_plan_code")
    @ApiModelProperty("子方案code")
    private String childPlanCode;
    /**
     * 资料模板ID
     */
    @Column(name = "fk_data_template_id")
    @ApiModelProperty("资料模板ID")
    private Integer fkDataTemplateId;
    /**
     * 资料模板名称
     */
    @Column(name = "data_template_name")
    @ApiModelProperty("资料模板名称")
    private String dataTemplateName;

    /**
     * 文件id
     */
    @Column(name = "file_id")
    @ApiModelProperty("文件id")
    private String fileId;

    /**
     * 文件名
     */
    @Column(name = "file_name")
    @ApiModelProperty("文件名")
    private String fileName;

    /**
     * 文件大小
     */
    @Column(name = "file_size")
    @ApiModelProperty("文件大小")
    private double fileSize;
    /**
     * 文件后缀
     */
    @Column(name = "file_suffix")
    @ApiModelProperty("文件后缀")
    private String fileSuffix;
    /**
     * 文件路径
     */
    @Column(name = "path")
    @ApiModelProperty("文件路径")
    private String path;
    /**
     * 审核流程 10 审核中  11 审核通过 12 审核拒绝
     */
    @Column(name = "flow")
    @ApiModelProperty("审核流程 10 审核中  11 审核通过 12 审核拒绝")
    private Byte flow;
    /**
     * 审核拒绝原因
     */
    @Column(name = "reason")
    @ApiModelProperty("审核拒绝原因")
    private String reason;

    /**
     * 状态：0 正常 2删除
     */
    @Column(name = "status")
    @ApiModelProperty("状态：0 正常 2删除")
    private Byte status;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty("创建时间")
    @CreatedDate
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @ApiModelProperty("更新时间")
    @LastModifiedDate
    private LocalDateTime updateTime;

}
