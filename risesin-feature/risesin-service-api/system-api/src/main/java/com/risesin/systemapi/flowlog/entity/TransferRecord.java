package com.risesin.systemapi.flowlog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 录方案或子方案整体执行,各个经手人流转过程
 *
 * @author Administrator
 */
@Data
@Entity
@Builder
@ApiModel("方案流程记录实体")
@Table(name = "t_transfer_record")
@EntityListeners(AuditingEntityListener.class)
public class TransferRecord implements Serializable {

    /**
     * 主键
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 经手人(平台加前缀sys_，助贷端加前缀loan_)
     */
    @Column(name = "handle_by")
    @CreatedBy
    private String handleBy;
    /**
     * 经手人姓名
     */
    @Column(name = "handle_by_name")
    private String handleByName;
    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "create_time")
    private LocalDateTime createTime;
    /**
     * 当前状态：1 标识为当前经手人节点  0 标识为已流转过的经手人节点
     */
    @Column(name = "is_current")
    private Byte isCurrent;

    /**
     * 方案ID或者子方案ID(方案前缀:plan_，子方案前缀:child_)
     */
    @Column(name = "plan_code")
    private String planCode;

    @Column(name = "flow")
    @ApiModelProperty("经手流转状态")
    private Integer flow;

    @Column(name = "content")
    @ApiModelProperty("流转内容")
    private String content;


}
