package com.risesin.systemapi.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.risesin.core.tool.node.INode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * RoleVO 视图实体类
 *
 * @author : honey
 * @date : 2019-10-25
 **/
@Data
@ApiModel(value = "RoleVO对象", description = "RoleVO对象")
public class RoleVO implements INode {


    /**
     * 主键ID
     */
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("父主键")
    private Integer parentId;

    /**
     * 角色别名
     */
    @ApiModelProperty("角色别名")
    private String roleAlias;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 状态： -1 不启用 0 启用 1 加入黑名单 2注销（删除）
     */
    @ApiModelProperty("状态： -1 不启用 0 启用 1 加入黑名单 2注销（删除）")
    private Byte status;
    /**
     * 创建用户
     */
    @ApiModelProperty("创建用户")
    private String createUser;


    @ApiModelProperty("上级角色")
    private String parentName;

    @ApiModelProperty("创建用户")
    private String createUserName;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    protected LocalDateTime createTime;

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

    public RoleVO() {
    }

    public RoleVO(Integer id, Integer parentId, String roleAlias, String roleName, Byte status, Integer sort, LocalDateTime createTime, String createUser) {
        this.id = id;
        this.parentId = parentId;
        this.roleAlias = roleAlias;
        this.roleName = roleName;
        this.sort = sort;
        this.status = status;
        this.createTime = createTime;
        this.createUser = createUser;
    }
}
