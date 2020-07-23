package com.risesin.systemapi.product.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * productTemplate实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_product_template")
@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class ProductTemplate implements Serializable {

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
    private Integer fkProductId;
    /**
     * 资料模板ID
     */
    @Column(name = "fk_data_template_id")
    private Integer fkDataTemplateId;


}
