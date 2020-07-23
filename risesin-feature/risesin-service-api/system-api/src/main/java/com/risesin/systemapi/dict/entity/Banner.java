package com.risesin.systemapi.dict.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * banner实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_banner")
@Data
public class Banner implements Serializable {


    /**
     * 主键ID
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 名称
     */
    @Column(name = "branner_name")
    private String brannerName;
    /**
     * 位置标识
     */
    @Column(name = "branner_flag")
    private String brannerFlag;
    /**
     * 图片集合
     */
    @Column(name = "img_list")
    private String imgList;
    /**
     * 图片路由
     */
    @Column(name = "img_router")
    private String imgRouter;
    /**
     * 图片跳转全路径
     */
    @Column(name = "img_http")
    private String imgHttp;
    /**
     * banner长
     */
    @Column(name = "banner_len")
    private Integer bannerLen;
    /**
     * banner宽
     */
    @Column(name = "banner_width")
    private Integer bannerWidth;
    /**
     * 状态：-1 下架 0 上架
     */
    @Column(name = "status")
    private Byte status;
    /**
     * 创建人
     */
    @Column(name = "create_user")
    private String createUser;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    /**
     * 1表示删除，0表示未删除
     */
    @Column(name = "del_flag")
    private Boolean delFlag;


}
