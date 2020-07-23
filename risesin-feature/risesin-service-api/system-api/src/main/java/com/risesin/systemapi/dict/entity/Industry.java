package com.risesin.systemapi.dict.entity;

import com.risesin.systemapi.dict.vo.INode;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * industry实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_industry")
@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class Industry implements INode, Serializable {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 行业码
     */
    @Column(name = "code")
    private String code;

    /**
     * 行业名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 父主键
     */
    @Column(name = "parent_code")
    private String parentCode;


    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @CreatedDate
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    @LastModifiedDate
    private LocalDateTime updateTime;

    /**
     * 状态：-1 不启用 0 启用 1 加入黑名单 2注销（删除）
     */
    @Column(name = "status")
    private Byte status;


}
