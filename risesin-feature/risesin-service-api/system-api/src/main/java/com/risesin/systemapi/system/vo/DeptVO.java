package com.risesin.systemapi.system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.risesin.core.tool.node.INode;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "DeptVO对象", description = "DeptVO对象")
public class DeptVO implements INode {

    public DeptVO() {

    }

    public DeptVO(Integer id, Integer parentId, String deptName, Integer sort, LocalDateTime createTime, LocalDateTime updateTime, String description, Byte status, String createUser) {
        this.id = id;
        this.parentId = parentId;
        this.deptName = deptName;
        this.sort = sort;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.description = description;
        this.status = status;
        this.createUser = createUser;
    }

    private Integer id;

    private Integer parentId;


    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
    /**
     * 描述
     */
    private String description;
    /**
     * -1 不启用 0 启用 1 加入黑名单 2注销（删除）
     */
    private Byte status;
    /**
     * 创建人
     */
    private String createUser;


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
     * 创建人
     */
    private String createUserName;
}
