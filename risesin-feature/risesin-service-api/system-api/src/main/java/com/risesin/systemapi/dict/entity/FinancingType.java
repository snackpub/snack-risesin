package com.risesin.systemapi.dict.entity;

import com.risesin.systemapi.dict.vo.INode;
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
 * 融资类型实体类
 *
 * @author Administrator
 */
@Data
@Entity
@ApiModel("融资类型对象")
@Table(name = "t_financing_type")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class FinancingType implements INode, Serializable {


    /**
     * 主键ID
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "code")
    @ApiModelProperty("融资类型标识")
    private String code;

    @Column(name = "name")
    @ApiModelProperty("融资融资名称")
    private String name;

    @ApiModelProperty("描述")
    @Column(name = "description")
    private String description;

    @ApiModelProperty("父节点")
    @Column(name = "parent_code")
    private String parentCode;

    @ApiModelProperty("创建人")
    @Column(name = "fk_sys_user_id")
    private String sysUserId;

    @Column(name = "status")
    @ApiModelProperty("状态： -1 不启用 0 启用 1 加入黑名单 2注销（删除）")
    private Byte status;

    @CreatedDate
    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @LastModifiedDate
    @ApiModelProperty("修改时间")
    @Column(name = "update_time")
    private LocalDateTime updateTime;


}
