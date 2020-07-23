package com.risesin.agent.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * comPermission实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_com_permission")
@Data
@DynamicInsert
@ApiModel(value = "权限", description = "权限")
public class ComPermission implements Serializable {

    /**
     * 主键ID：自动增长
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 权限别名
     */
    @Column(name = "alias")
    private String alias;
    /**
     * 所属菜单ID
     */
    @Column(name = "menu_id")
    private Integer menuId;
    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;
    /**
     * 创建用户
     */
    @Column(name = "create_user")
    private String createUser;
    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;
    /**
     * 描述
     */
    @Column(name = "description")
    private String description;
    /**
     * 状态：0 启用 -1 不启用
     */
    @Column(name = "status")
    private Byte status;


}
