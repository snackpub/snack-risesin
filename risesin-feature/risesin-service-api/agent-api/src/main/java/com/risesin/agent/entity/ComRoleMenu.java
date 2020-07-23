package com.risesin.agent.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

/**
 * comRoleMenu实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_com_role_menu")
@Data
@DynamicInsert
public class ComRoleMenu implements Serializable {


    /**
     * 主键ID
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
     * 菜单ID
     */
    @Column(name = "fk_menu_id")
    private Integer fkMenuId;


}
