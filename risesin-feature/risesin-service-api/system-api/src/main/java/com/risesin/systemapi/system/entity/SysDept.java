package com.risesin.systemapi.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * sysDept实体类
 *
 * @author Administrator
 */
@Data
@Entity
@ApiModel("SysDept对象")
@Table(name = "t_sys_dept")
@EntityListeners(AuditingEntityListener.class)
public class SysDept implements Serializable {


    /**
     * 主键
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("父主键")
    @Column(name = "parent_id")
    private Integer parentId;


    /**
     * 部门名称
     */
    @Column(name = "dept_name")
    @ApiModelProperty("部门名称")
    private String deptName;
    /**
     * 排序
     */
    @Column(name = "sort")
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 创建时间
     */
    @CreatedDate
    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @LastModifiedDate
    @ApiModelProperty("修改时间")
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    /**
     * 描述
     */
    @ApiModelProperty("描述")
    @Column(name = "description")
    private String description;
    /**
     * 状态  -1 不启用 0 启用 1 加入黑名单 2注销（删除）
     */
    @Column(name = "status")
    @ApiModelProperty("状态: -1 不启用 0 启用 1 加入黑名单 2注销（删除）")
    private Byte status;
    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    @Column(name = "create_user")
    private String createUser;


}
