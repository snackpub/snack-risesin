package com.risesin.systemapi.dict.vo;

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
@ApiModel(value = "DictVO对象", description = "DictVO对象")
public class DictVO implements INode {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private Integer id;

    /**
     * 父节点ID
     */
    @ApiModelProperty("父节点ID")
    private Integer parentId;

    /**
     * 子孙节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty("子孙节点")
    private List<INode> children;

    @Override
    public List<INode> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        return this.children;
    }

    private String code;
    /**
     * 字典值
     */
    @ApiModelProperty("字典值")
    private Integer dictKey;
    /**
     * 字典名称
     */
    @ApiModelProperty("字典名称")
    private String dictValue;
    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;
    /**
     * 描述
     */
    @ApiModelProperty("字典description")
    private String description;
    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private Integer createUser;
    /**
     * 是否删除： 1表示删除 0表示未删除
     */
    @ApiModelProperty("是否删除：true：已删除 true：正常")
    private Boolean delFlag;
    /**
     * 是否启用：1 不启用 0 启用
     */
    @ApiModelProperty("是否启用：1 不启用 0 启用")
    private Byte status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    /**
     * 上级字典
     */
    @ApiModelProperty("上级字典")
    private String parentName;

}
