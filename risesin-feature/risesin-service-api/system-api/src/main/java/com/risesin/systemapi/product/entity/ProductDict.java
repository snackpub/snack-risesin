package com.risesin.systemapi.product.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * productDict实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_product_dict")
@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class ProductDict implements Serializable {

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
     * 收费项ID（字典数据ID）
     */
    @Column(name = "fk_dict_id")
    private Integer dictId;


}
