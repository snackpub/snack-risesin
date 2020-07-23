package com.risesin.systemapi.system.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 应用实体类
 *
 * @author honey
 * @date 2019-12-22
 */
@Data
@Entity
@DynamicInsert
@Table(name = "risesin_client")
@EntityListeners(AuditingEntityListener.class)
public class RisesinClient implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 客户端id
     */
    @Column(name = "client_id")
    @ApiModelProperty(value = "应用id")
    private String clientId;
    /**
     * 客户端密钥
     */
    @Column(name = "client_secret")
    @ApiModelProperty(value = "应用端密钥")
    private String clientSecret;
    /**
     * 资源集合
     */
    @Column(name = "resource_ids")
    @ApiModelProperty(value = "资源集合")
    private String resourceIds;
    /**
     * 授权范围
     */
    @Column(name = "scope")
    @ApiModelProperty(value = "授权范围")
    private String scope;
    /**
     * 授权类型
     */
    @Column(name = "authorized_grant_types")
    @ApiModelProperty(value = "授权类型")
    private String authorizedGrantTypes;
    /**
     * 回调地址
     */
    @Column(name = "web_server_redirect_uri")
    @ApiModelProperty(value = "回调地址")
    private String webServerRedirectUri;
    /**
     * 权限
     */
    @Column(name = "authorities")
    @ApiModelProperty(value = "权限")
    private String authorities;
    /**
     * 令牌过期秒数
     */
    @Column(name = "access_token_validity")
    @ApiModelProperty(value = "令牌过期秒数")
    private Integer accessTokenValidity;
    /**
     * 刷新令牌过期秒数
     */
    @Column(name = "refresh_token_validity")
    @ApiModelProperty(value = "刷新令牌过期秒数")
    private Integer refreshTokenValidity;
    /**
     * 附加说明
     */
    @Column(name = "additional_information")
    @ApiModelProperty(value = "附加说明")
    private String additionalInformation;
    /**
     * 自动授权
     */
    @Column(name = "autoapprove")
    @ApiModelProperty(value = "自动授权")
    private String autoapprove;
    /**
     * 创建人
     */
    @Column(name = "create_user")
    @ApiModelProperty(value = "创建人")
    private String createUser;
    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 修改人
     */
    @Column(name = "update_user")
    @ApiModelProperty(value = "修改人")
    private String updateUser;
    /**
     * 修改时间
     */
    @Column(name = "update_time")
    @LastModifiedDate
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;
    /**
     * 状态
     */
    @Column(name = "status")
    @ApiModelProperty(value = "状态")
    private Byte status;
}
