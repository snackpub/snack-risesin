package com.risesin.agent.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

/**
 * comUserRole实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_com_user_role")
@Data
@DynamicInsert
public class ComUserRole implements Serializable {


    /**
     * 主键ID
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 用户ID
     */
    @Column(name = "fk_com_user_id")
    private String fkComUserId;
    /**
     * 角色ID
     */
    @Column(name = "fk_role_id")
    private Integer fkRoleId;


}
