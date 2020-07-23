package com.risesin.systemapi.enterprise.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * enterpriseUser实体类
 *
 * @author Administrator
 */
@Data
public class EnterpriseUserDTO implements Serializable {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 真实姓名
     */
    private String fullName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 行业ID
     */
    private Integer industryId;

    /**
     * 客户来源
     */
    private String source;

    /**
     * 工作单位
     */
    private String companyName;

    /**
     * 联系电话
     */
    private String contactNumber;

    /**
     * 所在地区
     */
    private String area;

    /**
     * 街道地址
     */
    private String address;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 状态：0 启用 1不启用 2 黑名单 3注销
     */
    private Byte status;

    /**
     * 是否删除：1表示删除，0表示未删除
     */
    private Boolean delFlag;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 传真
     */
    private String fax;

}
