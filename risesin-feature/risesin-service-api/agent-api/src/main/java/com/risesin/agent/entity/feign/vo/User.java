package com.risesin.agent.entity.feign.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * User实体类
 */
@Data
@ApiModel("用户对象")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User implements Serializable {

    /**
     * 主键ID：雪花分布式id
     */
    @Id
    @ApiModelProperty("主键id")
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 用户账户
     */
    @ApiModelProperty("用户账户")
    private String userAccount;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 状态： -1 不启用 0 启用 1 加入黑名单 2注销（删除）
     */
    @ApiModelProperty("状态： -1 不启用 0 启用 1 加入黑名单 2注销（删除）")
    private Byte status;

    /**
     * 部门ID
     */
    @ApiModelProperty("部门ID")
    private String deptId;

    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    private String roleId;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 用户所属 1 系统用户 2 助贷用户 3 企业用户
     */
    @ApiModelProperty("用户所属 1 系统用户 2 助贷用户 3 企业用户")
    private Integer userType;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

}
