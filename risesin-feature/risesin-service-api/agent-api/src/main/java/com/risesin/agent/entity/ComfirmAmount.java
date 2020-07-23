package com.risesin.agent.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * comfirmAmount实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_comfirm_amount")
@Data
@DynamicInsert
@ApiModel(value = "额度确认", description = "额度确认")
public class ComfirmAmount implements Serializable {

    /**
     * 主键
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 批准额度
     */
    @Column(name = "final_amout")
    @ApiModelProperty(value = "批准额度")
    private BigDecimal finalAmout;
    /**
     * 客户是否接受：1 不接受 0 接受
     */
    @Column(name = "is_accept")
    @ApiModelProperty(value = "客户是否接受：1 不接受 0 接受")
    private Boolean isAccept;
    /**
     * 审核人ID
     */
    @Column(name = "com_user")
    @ApiModelProperty(value = "审核人ID")
    private String comUser;
    /**
     * 创建时间（助贷审核时间）
     */
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间（助贷审核时间）")
    private LocalDateTime createTime;
    /**
     * 客户确认时间
     */
    @Column(name = "comfirm_time")
    @ApiModelProperty(value = "客户确认时间")
    private LocalDateTime comfirmTime;
    /**
     * 审核意见
     */
    @Column(name = "comment")
    @ApiModelProperty(value = "审核意见")
    private String comment;
    /**
     * 子方案ID
     */
    @Column(name = "fk_child_plan_id")
    @ApiModelProperty(value = "子方案ID")
    private Integer fkChildPlanId;


}
