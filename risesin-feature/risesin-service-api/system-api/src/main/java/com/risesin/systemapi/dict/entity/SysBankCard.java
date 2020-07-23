package com.risesin.systemapi.dict.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * sysBankCard实体类
 *
 * @author Administrator
 */
@Data
@Entity
@DynamicInsert
@ApiModel("银行卡实体")
@Table(name = "t_sys_bank_card")
@EntityListeners(AuditingEntityListener.class)
public class SysBankCard implements Serializable {

    /**
     * pk_id
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 银行卡号
     */
    @Column(name = "card")
    @ApiModelProperty("银行卡号")
    private String card;
    /**
     * 支付名称
     */
    @Column(name = "pay_name")
    @ApiModelProperty("支付名称")
    private String payName;
    /**
     * 收款方
     */
    @Column(name = "beneficiary")
    @ApiModelProperty("收款方")
    private String beneficiary;
    /**
     * 开户行
     */
    @ApiModelProperty("开户行")
    @Column(name = "bene_bank")
    private String beneBank;
    /**
     * 联系电话
     */
    @Column(name = "phone")
    @ApiModelProperty("联系电话")
    private String phone;
    /**
     * 状态：0 启用 1 不启用  2 注销(删除)
     */
    @ApiModelProperty("状态：0 启用 1 不启用  2 注销(删除)")
    @Column(name = "status")
    private Byte status;
    /**
     * 创建时间
     */
    @CreatedDate
    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    @Column(name = "create_user")
    private String createUser;


}
