package com.risesin.agent.entity.feign.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * extFileData实体类
 * @author Administrator
 *
 */
@Data
@ApiModel("文件资料拓展")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExtFileData implements Serializable{

	/**
	 * 主键ID
	 */
	@ApiModelProperty("id")
	private Integer id;

	/**
	 * 子方案code
	 */
	@ApiModelProperty("子方案code")
	private String childPlanCode;
	/**
	 * 资料模板ID
	 */
	@ApiModelProperty("资料模板ID")
	private Integer fkDataTemplateId;
	/**
	 * 资料模板名称
	 */
	@ApiModelProperty("资料模板名称")
	private String dataTemplateName;

	/**
	 * 文件id
	 */
	@ApiModelProperty("文件id")
	private String fileId;

	/**
	 * 文件名
	 */
	@ApiModelProperty("文件名")
	private String fileName;
	/**
	 * 文件后缀
	 */
	@ApiModelProperty("文件后缀")
	private String fileSuffix;
	/**
	 * 文件路径
	 */
	@ApiModelProperty("文件路径")
	private String path;
	/**
	 * 审核流程 10 审核中  11 审核通过 12 审核拒绝
	 */
	@ApiModelProperty("审核流程 10 审核中  11 审核通过 12 审核拒绝")
	private Byte flow;
	/**
	 * 审核拒绝原因
	 */
	@ApiModelProperty("审核拒绝原因")
	private String reason;

	/**
	 * 状态：0 正常 2删除
	 */
	@ApiModelProperty("状态：0 正常 2删除")
	private Byte status;
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	private LocalDateTime updateTime;

}
