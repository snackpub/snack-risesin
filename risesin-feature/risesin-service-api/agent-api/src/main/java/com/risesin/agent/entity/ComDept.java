package com.risesin.agent.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * comDept实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_com_dept")
@Data
@DynamicInsert
@ApiModel(value = "部门实体", description = "部门实体")
public class ComDept implements Serializable {

    /**
     * 主键
     */
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 部门名称
     */
    @Column(name = "dept_name")
    @ApiModelProperty(value = "部门名称")
    private String deptName;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @Column(name = "sort")
    private Integer sort;
    /**
     * 公司ID
     */
    @ApiModelProperty(value = "公司ID")
    @Column(name = "fk_company_id")
    private Integer fkCompanyId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态 -1启用 0 正常")
    @Column(name = "status")
    private Byte status;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Column(name = "description")
    private String description;

    /**
     * 父主键
     */
    @ApiModelProperty(value = "父主键")
    @Column(name = "parent_id")
    private Integer parentId;

}
