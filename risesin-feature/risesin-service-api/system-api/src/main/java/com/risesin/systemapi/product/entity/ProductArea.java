package com.risesin.systemapi.product.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * productArea实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_product_area")
@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class ProductArea implements Serializable {

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
     * 地区ID
     */
    @Column(name = "fk_area_id")
    private Integer areaId;


}
