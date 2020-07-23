package com.risesin.systemapi.dict.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.risesin.core.tool.node.INode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * dict实体类
 *
 * @author Administrator
 */
@Data
@Entity
@ApiModel("dict对象")
@Table(name = "risesin_dict")
@EntityListeners(AuditingEntityListener.class)
public class Dict implements Serializable, INode {


    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 父主键
     */
    @ApiModelProperty("父主键")
    @Column(name = "parent_id")
    private Integer parentId;
    /**
     * 字典码
     */
    @Column(name = "code")
    @ApiModelProperty("字典码")
    private String code;
    /**
     * 字典值
     */
    @ApiModelProperty("字典值")
    @Column(name = "dict_key")
    private Integer dictKey;
    /**
     * 字典名称
     */
    @ApiModelProperty("字典名称")
    @Column(name = "dict_value")
    private String dictValue;
    /**
     * 排序
     */
    @Column(name = "sort")
    @ApiModelProperty("排序")
    private Integer sort;
    /**
     * 描述
     */
    @ApiModelProperty("字典description")
    @Column(name = "description")
    private String description;
    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    @Column(name = "create_user")
    private String createUser;
    /**
     * 状态： -1 不启用 0 启用 1 加入黑名单 2注销（删除）
     */
    @Column(name = "status")
    @ApiModelProperty("状态： -1 不启用 0 启用 1 加入黑名单 2注销（删除）")
    private Byte status;

    @CreatedDate
    @Column(name = "create_time")
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;


    /**
     * 子孙节点
     */
    @Transient
    @ApiModelProperty(value = "子孙节点", hidden = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<INode> children;

    @Override
    public List<INode> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        return this.children;
    }
}
