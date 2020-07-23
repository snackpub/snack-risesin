package com.risesin.agent.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.risesin.agent.entity.ComMenu;
import com.risesin.core.tool.node.INode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR Baby
 * @CREATE 2019/12/1
 * @DESCRIPTION 菜单管理Vo
 * @since 1.0.0
 */
@Data
@ApiModel(value = "菜单管理VO", description = "菜单管理VO")
public class ComMenuVO extends ComMenu implements INode {


    /**
     * 子孙节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(value = "子孙节点")
    private List<INode> children;

    @Override
    public List<INode> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        return this.children;
    }

    /**
     * 上级名称
     */
    @ApiModelProperty(value = "上级名称")
    private String parentName;

    /**
     * 菜单类型
     */
    @ApiModelProperty(value = "菜单类型")
    private String categoryName;

    /**
     * 按钮功能
     */
    @ApiModelProperty(value = "按钮功能")
    private String actionName;

    /**
     * 是否新窗口打开
     */
    @ApiModelProperty(value = "是否新窗口打开")
    private String isOpenName;
}
