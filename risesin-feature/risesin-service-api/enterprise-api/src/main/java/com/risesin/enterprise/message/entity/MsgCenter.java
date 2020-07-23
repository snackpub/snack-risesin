package com.risesin.enterprise.message.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * msgCenter实体类
 *
 * @author Administrator
 */
@Entity
@ApiModel("msgCenter实体类")
@Table(name = "t_ent_msg_center")
@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class MsgCenter implements Serializable {

    @ApiModelProperty("主键")
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("消息类型:完善资料(1);资料审核失败(2);资料审核成功(3);风控审核成功(4);风控审核失败(5);支付(6);确认额度(7);放款(8);确认收款(9);")
    @Column(name = "msg_type")
    private Byte msgType;

    @ApiModelProperty("消息标题")
    @Column(name = "msg_title")
    private String msgTitle;

    @ApiModelProperty("消息内容")
    @Column(name = "msg_content")
    private String msgContent;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("企业用户id")
    @Column(name = "fk_ent_user_id")
    private String entUserId;

    @ApiModelProperty("父方案编号")
    @Column(name = "plan_code")
    private String planCode;

    @ApiModelProperty("子方案编号")
    @Column(name = "child_plan_code")
    private String childPlanCode;

    @ApiModelProperty("事项处理状态：-1未处理，0已处理，1无需处理(仅通知)")
    @Column(name = "status")
    private Byte status;

}
