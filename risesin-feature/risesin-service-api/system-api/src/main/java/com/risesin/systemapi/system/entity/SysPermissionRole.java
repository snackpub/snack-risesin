package com.risesin.systemapi.system.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * sysPermissionRole实体类
 *
 * @author Administrator
 */
@Data
@Entity
@Table(name = "t_sys_permission_role")
public class SysPermissionRole implements Serializable {


    /**
     * 主键ID：自动增长
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 角色ID
     */
    @Column(name = "fk_role_id")
    private Integer roleId;
    /**
     * 权限ID
     */
    @Column(name = "fk_permission_id")
    private Integer permissionId;


}
