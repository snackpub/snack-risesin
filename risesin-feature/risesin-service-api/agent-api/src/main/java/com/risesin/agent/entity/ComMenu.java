package com.risesin.agent.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

/**
 * comMenu实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_com_menu")
@Data
@DynamicInsert
@ApiModel(value = "ComMenu菜单对象", description = "ComMenu菜单对象")
public class ComMenu implements Serializable {

    /**
     * 主键ID：自动增长
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 父菜单ID，一级菜单为0
     */
    @Column(name = "parent_id")
    @ApiModelProperty(value = "父菜单ID，一级菜单为0")
    private Integer parentId;
    /**
     * 菜单别名
     */
    @Column(name = "alias")
    @ApiModelProperty(value = "菜单别名")
    private String alias;
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    @Column(name = "name")
    private String name;
    /**
     * 菜单路径
     */
    @ApiModelProperty(value = "菜单路径")
    @Column(name = "path")
    private String path;
    /**
     * 菜单资源Icon
     */
    @ApiModelProperty(value = "菜单资源Icon")
    @Column(name = "source")
    private String source;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @Column(name = "sort")
    private Integer sort;
    /**
     * 菜单类型   0：目录   1：菜单   2：按钮
     */
    @ApiModelProperty(value = "菜单类型   0：目录   1：菜单   2：按钮")
    @Column(name = "category")
    private Byte category;
    /**
     * 是否打开新页面
     */
    @ApiModelProperty(value = "是否打开新页面")
    @Column(name = "is_open")
    private Integer isOpen;

    /**
     * 操作按钮类型
     */
    @ApiModelProperty(value = "操作按钮类型")
    @Column(name = "action")
    private Integer action;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;
    /**
     * 是否启用   -1：不启用  0：启用
     */
    @ApiModelProperty(value = "是否启用   -1：不启用  0：正常 ")
    @Column(name = "status")
    private Byte status;
    /**
     * 创建用户
     */
    @ApiModelProperty(value = "创建用户")
    @Column(name = "create_user")
    private String createUser;

}
