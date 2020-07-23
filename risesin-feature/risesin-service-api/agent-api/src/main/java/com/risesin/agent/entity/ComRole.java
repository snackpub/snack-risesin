package com.risesin.agent.entity;

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
 * comRole实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_com_role")
@Data
@ApiModel(value = "角色", description = "角色")
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class ComRole implements Serializable {

    /**
     * 主键ID：自动增长
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    /**
     * 角色标识
     */
    @Column(name = "sign")
    @ApiModelProperty(value = "角色标识")
    private String sign;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    @CreatedDate
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @Column(name = "update_time")
    @ApiModelProperty(value = "修改时间")
    @LastModifiedDate
    private LocalDateTime updateTime;
    /**
     * 创建用户id
     */
    @Column(name = "fk_user_id_create")
    @ApiModelProperty(value = "创建用户id")
    private String fkUserIdCreate;
    /**
     * 排序
     */
    @Column(name = "sort")
    @ApiModelProperty(value = "排序")
    private String sort;

    /**
     * 父主键
     */
    @Column(name = "parent_id")
    @ApiModelProperty(value = "父主键")
    private Integer parentId;

    /**
     * 状态：-1 不启用 0 启用 1 黑名单 2 注销
     */
    @Column(name = "status")
    @ApiModelProperty(value = "状态：-1 不启用 0 启用 1 黑名单 2 注销")
    private Byte status;
}
