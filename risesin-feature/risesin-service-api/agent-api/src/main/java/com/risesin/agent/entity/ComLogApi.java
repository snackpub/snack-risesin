package com.risesin.agent.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * comLogApi实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_com_log_api")
@Data
@DynamicInsert
@ApiModel(value = "日志", description = "日志")
public class ComLogApi implements Serializable {

    /**
     * 编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 服务ID
     */
    @Column(name = "service_id")
    @ApiModelProperty(value = "服务ID")
    private String serviceId;
    /**
     * 服务器名
     */
    @Column(name = "server_host")
    @ApiModelProperty(value = "服务器名")
    private String serverHost;
    /**
     * 服务器IP地址
     */
    @Column(name = "server_ip")
    @ApiModelProperty(value = "服务器IP地址")
    private String serverIp;
    /**
     * 服务器环境
     */
    @ApiModelProperty(value = "服务器环境")
    @Column(name = "env")
    private String env;
    /**
     * 日志类型
     */
    @ApiModelProperty(value = "日志类型")
    @Column(name = "type")
    private Character type;
    /**
     * 日志标题
     */
    @ApiModelProperty(value = "日志标题")
    @Column(name = "title")
    private String title;
    /**
     * 操作方式
     */
    @ApiModelProperty(value = "操作方式")
    @Column(name = "method")
    private String method;
    /**
     * 请求URI
     */
    @ApiModelProperty(value = "请求URI")
    @Column(name = "request_uri")
    private String requestUri;
    /**
     * 用户代理
     */
    @ApiModelProperty(value = "用户代理")
    @Column(name = "user_agent")
    private String userAgent;
    /**
     * 操作IP地址
     */
    @ApiModelProperty(value = "操作IP地址")
    @Column(name = "remote_ip")
    private String remoteIp;
    /**
     * 方法类
     */
    @ApiModelProperty(value = "方法类")
    @Column(name = "method_class")
    private String methodClass;
    /**
     * 方法名
     */
    @ApiModelProperty(value = "方法名")
    @Column(name = "method_name")
    private String methodName;
    /**
     * 操作提交的数据
     */
    @ApiModelProperty(value = "操作提交的数据")
    @Column(name = "params", columnDefinition = "text")
    private String params;
    /**
     * 执行时间
     */
    @ApiModelProperty(value = "执行时间")
    @Column(name = "time")
    private String time;
    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    @Column(name = "create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private LocalDateTime createTime;


}
