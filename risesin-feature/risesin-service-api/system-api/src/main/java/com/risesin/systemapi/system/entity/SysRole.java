package com.risesin.systemapi.system.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * sysRole实体类
 *
 * @author Administrator
 */
@Data
@Entity
@Table(name = "t_sys_role")
@EntityListeners(AuditingEntityListener.class)
public class SysRole implements Serializable {

    @Id
    @Column(name = "pk_id")
    @ApiModelProperty("主键")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "parentId")
    @ApiModelProperty("父主键")
    private Integer parentId = 0;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    @ApiModelProperty("角色名称")
    private String roleName;

    /**
     * 排序
     */
    @Column(name = "sort")
    @ApiModelProperty("排序")
    private Integer sort;
    /**
     * 角色别名
     */
    @ApiModelProperty("角色别名")
    @Column(name = "role_alias")
    private String roleAlias;
    /**
     * 状态： -1 不启用 0 启用 1 加入黑名单 2注销（删除）
     */
    @Column(name = "status")
    @ApiModelProperty("状态： -1 不启用 0 启用 1 加入黑名单 2注销（删除）")
    private Byte status = -1;
    /**
     * 创建用户
     */
    @CreatedBy
    @ApiModelProperty("创建用户")
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @CreatedDate
    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createTime;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @ApiModelProperty("修改时间")
    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime updateTime;

    @ApiModelProperty("描述")
    @Column(name = "description")
    protected String description;


}
