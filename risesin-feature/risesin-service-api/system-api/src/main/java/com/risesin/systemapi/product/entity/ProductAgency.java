package com.risesin.systemapi.product.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * productAgency实体类
 *
 * @author Administrator
 */
@Data
@Entity
@DynamicInsert
@Table(name = "t_product_agency")
@EntityListeners(AuditingEntityListener.class)
public class ProductAgency implements Serializable {

    /**
     * 主键
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 产品ID
     */
    @Column(name = "fk_product_id")
    private Integer productId;
    /**
     * 助贷机构ID
     */
    @Column(name = "fk_loan_agency_id")
    private Integer loanAgencyId;

    /**
     * 授权人
     */
    @Column(name = "authorizer")
    private String authorizer;
    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "create_time")
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @LastModifiedDate
    @Column(name = "update_time")
    private LocalDateTime updateTime;


}
