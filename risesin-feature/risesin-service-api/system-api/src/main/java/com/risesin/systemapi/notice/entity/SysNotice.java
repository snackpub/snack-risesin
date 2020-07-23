package com.risesin.systemapi.notice.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * sysNotice实体类
 *
 * @author Administrator
 */
@Data
@Entity
@Table(name = "t_sys_notice")
@EntityListeners(AuditingEntityListener.class)
public class SysNotice implements Serializable {


    /**
     * id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    @ApiModelProperty("标题")
    private String title;

    @Column(name = "category")
    @ApiModelProperty("公告类型: 1 发布通知 2 批转通知 3 转发通知 4 指示通知 5 任免通知 6 事务通知")
    private Integer category;
    /**
     * 内容
     */
    @Column(name = "content")
    private String content;
    /**
     * 创建人
     */

    @Column(name = "create_user")
    private String createUser;
    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "create_time")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(name = "update_time")
    private LocalDateTime updateTime;


    @Column(name = "status")
    @ApiModelProperty("状态：-1 未发布 0 发布 1 未发布 2  注销（删除）")
    private Byte status;

}
