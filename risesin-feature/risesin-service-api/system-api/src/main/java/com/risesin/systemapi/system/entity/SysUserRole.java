package com.risesin.systemapi.system.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * sysUserRole实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_sys_user_role")
@Data
public class SysUserRole implements Serializable {


    /**
     * 主键ID：自动增长
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 用户ID
     */
    @Column(name = "fk_user_id")
    private String userId;
    /**
     * 角色ID
     */
    @Column(name = "fk_role_id")
    private Integer roleId;


}
