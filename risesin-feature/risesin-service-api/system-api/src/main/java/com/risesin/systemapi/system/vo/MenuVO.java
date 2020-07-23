package com.risesin.systemapi.system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.risesin.core.tool.node.INode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 视图实体类
 *
 * @author honey
 */
@Data
@ApiModel(value = "MenuVO对象", description = "MenuVO对象")
public class MenuVO implements INode {
    private static final long serialVersionUID = 1L;


    public MenuVO() {
    }

    public MenuVO(Integer id, Integer parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public MenuVO(Integer id, Integer parentId, String name, String alias) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.alias = alias;
    }

    public MenuVO(Integer id, Integer parentId, String name, String alias, String path, String source, Integer sort, Byte category, Integer isOpen, String remark) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.alias = alias;
        this.path = path;
        this.source = source;
        this.sort = sort;
        this.category = category;
        this.isOpen = isOpen;
        this.remark = remark;
    }

    public MenuVO(Integer id, Integer parentId, String name, String alias, String path, String source, Integer sort) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.alias = alias;
        this.path = path;
        this.source = source;
        this.sort = sort;
    }

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Integer id;


    /**
     * 菜单父主键
     */
    @ApiModelProperty(value = "菜单父主键")
    private Integer parentId;


    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String name;

    /**
     * 菜单别名
     */
    @ApiModelProperty(value = "菜单别名")
    private String alias;

    /**
     * 请求地址
     */
    @ApiModelProperty(value = "请求地址")
    private String path;

    /**
     * 菜单资源
     */
    @ApiModelProperty(value = "菜单资源")
    private String source;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 菜单类型
     */
    @ApiModelProperty(value = "菜单类型")
    private Byte category;

    /**
     * 是否打开新页面
     */
    @ApiModelProperty(value = "是否打开新页面")
    private Integer isOpen;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 状态： -1 不启用 0 启用 1 加入黑名单 2注销（删除）
     */
    @ApiModelProperty(value = "状态： -1 不启用 0 启用 1 加入黑名单 2注销（删除）")
    private Byte status;
    /**
     * 创建用户
     */
    @ApiModelProperty(value = "创建用户")
    private Integer createUser;

    /**
     * 子孙节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<INode> children;

    @Override
    public List<INode> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        return this.children;
    }

    /**
     * 上级菜单
     */
    @ApiModelProperty(value = "上级菜单")
    private String parentName;

    /**
     * 菜单类型
     */
    @ApiModelProperty(value = "菜单类型")
    private String categoryName;

    /**
     * 是否新窗口打开
     */
    @ApiModelProperty(value = "是否新窗口打开")
    private String isOpenName;

    /**
     * 创建用户
     */
    @ApiModelProperty(value = "创建用户")
    private String createUserName;


    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
