package com.risesin.agent.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

/**
 * comPermissionRole实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_com_permission_role")
@Data
@DynamicInsert
public class ComPermissionRole implements Serializable {


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
    private Integer fkRoleId;
    /**
     * 权限ID
     */
    @Column(name = "fk_permission_id")
    private Integer fkPermissionId;


}
