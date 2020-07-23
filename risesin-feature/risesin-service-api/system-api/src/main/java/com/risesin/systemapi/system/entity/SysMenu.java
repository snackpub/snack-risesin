package com.risesin.systemapi.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * sysMenu实体类
 *
 * @author Administrator
 */
@Data
@Entity
@ApiModel("Menu对象")
@Table(name = "t_sys_menu")
@EntityListeners(AuditingEntityListener.class)
public class SysMenu implements Serializable {


    /**
     * 主键ID：自动增长
     */
    @Id
    @Column(name = "pk_id")
    @ApiModelProperty("主键ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 父菜单ID，一级菜单为0
     */
    @ApiModelProperty("父菜单ID，一级菜单为0")
    @Column(name = "parent_id")
    private Integer parentId;
    /**
     * 菜单别名
     */
    @ApiModelProperty("菜单别名")
    @Column(name = "alias")
    private String alias;
    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    @Column(name = "name")
    private String name;
    /**
     * 菜单路径
     */
    @ApiModelProperty("菜单路径")
    @Column(name = "path")
    private String path;
    /**
     * 菜单资源Icon
     */
    @ApiModelProperty("菜单资源Icon")
    @Column(name = "source")
    private String source;
    /**
     * 排序
     */
    @ApiModelProperty("排序")
    @Column(name = "sort")
    private Integer sort;
    /**
     * 菜单类型   0：目录   1：菜单   2：按钮
     */
    @Column(name = "category")
    @ApiModelProperty("菜单类型   0：目录   1：菜单   2：按钮")
    private Byte category;
    /**
     * 是否打开新页面
     */
    @ApiModelProperty("是否打开新页面")
    @Column(name = "is_open")
    private Integer isOpen;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    @Column(name = "remark")
    private String remark;
    /**
     * 状态： -1 不启用 0 启用 1 加入黑名单 2注销（删除）
     */
    @ApiModelProperty("状态： -1 不启用 0 启用 1 加入黑名单 2注销（删除）")
    @Column(name = "status")
    private Byte status;
    /**
     * 创建用户
     */
    @ApiModelProperty("创建用户")
    @Column(name = "create_user")
    private String createUser;
    /**
     * 创建时间
     */
    @CreatedDate
    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @ApiModelProperty("修改时间")
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("菜单所属 1 咨询服务端 2 助贷端")
    @Column(name = "menu_belongs")
    private Integer menuBelongs;


}
