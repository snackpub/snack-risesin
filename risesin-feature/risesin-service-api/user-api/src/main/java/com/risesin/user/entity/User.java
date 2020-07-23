package com.risesin.user.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
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
 * User实体类
 *
 * @author Administrator
 */
@Data
@Entity
@ApiModel("用户对象")
@Table(name = "risesin_user")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User implements Serializable {

    /**
     * 主键ID：雪花分布式id
     */
    @Id
    @Column(name = "pk_id")
    @ApiModelProperty("主键id")
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @Column(name = "user_name")
    private String username;

    /**
     * 用户账户
     */
    @ApiModelProperty("用户账户")
    @Column(name = "user_account")
    private String account;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    @Column(name = "phone")
    private String phone;

    /**
     * 状态： -1 不启用 0 启用 1 加入黑名单 2注销（删除）
     */
    @ApiModelProperty("状态： -1 不启用 0 启用 1 加入黑名单 2注销（删除）")
    @Column(name = "status")
    private Byte status = 0;

    /**
     * 部门ID
     */
    @ApiModelProperty("部门ID")
    @Column(name = "dept_id")
    private String deptId;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    @ApiModelProperty("角色ID")
    private String roleId;

    /**
     * 排序
     */
    @Column(name = "sort")
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 用户所属 1 系统用户 2 助贷用户 3 企业用户
     */
    @ApiModelProperty("用户所属 1 系统用户 2 助贷用户 3 企业用户")
    @Column(name = "user_type")
    private Integer userType;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "create_time")
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(name = "update_time")
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

}
