package com.risesin.agent.entity.feign.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * msgCenter实体类
 * @author Administrator
 *
 */
@ApiModel("msgCenter实体类")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MsgCenter implements Serializable{

	@ApiModelProperty("主键")
	private Integer id;

	@ApiModelProperty("消息类型:完善资料(1);资料审核失败(2);资料审核成功(3);风控审核成功(4);风控审核失败(5);支付(6);确认额度(7);放款(8);确认收款(9);")
	private Byte msgType;
	
	@ApiModelProperty("消息标题")
	private String msgTitle;
	
	@ApiModelProperty("消息内容")
	private String msgContent;
	
	@ApiModelProperty("创建时间")
	private LocalDateTime createTime;
	
	@ApiModelProperty("企业用户id")
	private Integer fkEntUserId;
	
	@ApiModelProperty("子方案编号")
	private String childPlanCode;

}
