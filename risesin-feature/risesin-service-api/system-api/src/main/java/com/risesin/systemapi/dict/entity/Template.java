package com.risesin.systemapi.dict.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * template实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_template")
@Data
public class Template implements Serializable {


    /**
     * 主键ID
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 外键：助贷机构ID
     */
    @Column(name = "fk_loan_agen_id")
    private Integer fkLoanAgenId;
    /**
     * 模板封底
     */
    @Column(name = "backcover_url")
    private String backcoverUrl;
    /**
     * 模板结尾
     */
    @Column(name = "end")
    private String end;
    /**
     * 模板开头
     */
    @Column(name = "start")
    private String start;
    /**
     * 主体分析内容
     */
    @Column(name = "main_content", columnDefinition = "Text")
    private String mainContent;
    /**
     * 产品内容
     */
    @Column(name = "product_content", columnDefinition = "Text")
    private String productContent;
    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;
    /**
     * 是否删除 1：已删除  0：正常
     */
    @Column(name = "del_flag")
    private Boolean delFlag;


}
