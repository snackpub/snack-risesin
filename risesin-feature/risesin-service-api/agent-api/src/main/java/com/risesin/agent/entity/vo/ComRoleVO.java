package com.risesin.agent.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.risesin.agent.entity.ComRole;
import com.risesin.core.tool.node.INode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR Baby
 * @CREATE 2019/12/1
 * @DESCRIPTION 角色管理
 * @since 1.0.0
 */
@Data
@ApiModel(value = "菜单管理VO", description = "菜单管理VO")
public class ComRoleVO extends ComRole implements INode {

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
     * 上级角色
     */
    @ApiModelProperty(value = "上级角色")
    private String parentName;
}
