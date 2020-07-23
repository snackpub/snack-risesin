package com.risesin.agent.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * comBankCard实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_com_bank_card")
@Data
@DynamicInsert
@ApiModel(value = "银行卡对象", description = "银行卡对象")
public class ComBankCard implements Serializable {
    /**
     * pk_id
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 银行卡号
     */
    @Column(name = "card")
    @ApiModelProperty(value = "银行卡号")
    private String card;
    /**
     * 支付名称
     */
    @Column(name = "pay_name")
    @ApiModelProperty(value = "支付名称")
    private String payName;
    /**
     * 收款方
     */
    @Column(name = "beneficiary")
    @ApiModelProperty(value = "收款方")
    private String beneficiary;
    /**
     * 开户行
     */
    @Column(name = "bene_bank")
    @ApiModelProperty(value = "开户行")
    private String beneBank;
    /**
     * 联系电话
     */
    @Column(name = "phone")
    @ApiModelProperty(value = "联系电话")
    private String phone;
    /**
     * 状态：-1 不启用 0 启用
     */
    @Column(name = "status")
    @ApiModelProperty(value = "状态：-1 不启用 0 启用")
    private Byte status;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    @Column(name = "create_user")
    @ApiModelProperty(value = "创建人")
    private String createUser;


}
