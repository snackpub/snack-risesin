package com.risesin.agent.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.risesin.agent.entity.ComDept;
import com.risesin.core.tool.node.INode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR Baby
 * @CREATE 2019/12/1
 * @DESCRIPTION 部门视图对象
 * @since 1.0.0
 */
@Data
@ApiModel(value = "部门视图对象", description = "部门视图对象")
public class ComDeptVO extends ComDept implements INode {

    /**
     * 上级部门
     */
    @ApiModelProperty(value = "上级部门名称")
    private String parentName;

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


}
