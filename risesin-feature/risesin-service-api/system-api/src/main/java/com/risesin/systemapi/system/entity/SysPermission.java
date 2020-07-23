package com.risesin.systemapi.system.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * t_sys_permission实体类
 *
 * @author honey
 */
@Data
@Entity
@DynamicInsert
@Table(name = "t_sys_permission")
public class SysPermission implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID")
    private Integer id;
    /**
     * 权限名称
     */
    @Column(name = "name")
    @ApiModelProperty(value = "权限名称")
    private String name;
    /**
     * 权限别名
     */
    @Column(name = "alias")
    @ApiModelProperty(value = "权限别名")
    private String alias;
    /**
     * 父主键
     */
    @Column(name = "parent_id")
    @ApiModelProperty(value = "父主键")
    private Integer parentId;
    /**
     * 所属菜单ID
     */
    @Column(name = "menu_id")
    @ApiModelProperty(value = "所属菜单ID")
    private String menuId;
    /**
     * 创建用户
     */
    @Column(name = "create_user")
    @ApiModelProperty(value = "创建用户")
    private String createUser;
    /**
     * 描述
     */
    @Column(name = "description")
    @ApiModelProperty(value = "描述")
    private String description;
    /**
     * 状态： -1 不启用 0 启用 2注销（删除）
     */
    @Column(name = "status")
    @ApiModelProperty(value = "状态： -1 不启用 0 启用 2注销（删除）")
    private Byte status;

    @Column(name = "path")
    @ApiModelProperty(value = "权限路径")
    private String path;

    @Column(name = "per_number")
    @ApiModelProperty(value = "权限编号")
    private String perNumber;

    @Column(name = "per_type")
    @ApiModelProperty(value = "接口类型 1 业务接口 2 系统接口")
    private Integer interfaceType;


    /**
     * 排序
     */
    @Column(name = "sort")
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 修改时间
     */
    @Column(name = "update_time")
    @ApiModelProperty(value = "修改时间")
    private String updateTime;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private String createTime;
}
