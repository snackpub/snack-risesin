package com.risesin.systemapi.system.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * SysMenuRole实体类
 *
 * @author Administrator
 */
@Data
@Entity
@Table(name = "t_sys_menu_role")
public class SysMenuRole implements Serializable {


    /**
     * 主键ID：自动增长
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Integer roleId;
    /**
     * 菜单ID
     */
    @Column(name = "menu_id")
    private Integer menuId;


}
