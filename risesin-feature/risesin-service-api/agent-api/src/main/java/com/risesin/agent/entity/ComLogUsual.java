package com.risesin.agent.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * comLogUsual实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_com_log_usual")
@Data
@DynamicInsert
@ApiModel(value = "普通日志", description = "普通日志")
public class ComLogUsual implements Serializable {

    /**
     * 编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 服务ID
     */
    @Column(name = "service_id")
    private String serviceId;
    /**
     * 服务器名
     */
    @Column(name = "server_host")
    private String serverHost;
    /**
     * 服务器IP地址
     */
    @Column(name = "server_ip")
    private String serverIp;
    /**
     * 系统环境
     */
    @Column(name = "env")
    private String env;
    /**
     * 日志级别
     */
    @Column(name = "log_level")
    private String logLevel;
    /**
     * 日志业务id
     */
    @Column(name = "log_id")
    private String logId;
    /**
     * 日志数据
     */
    @Column(name = "log_data", columnDefinition = "text")
    private String logData;
    /**
     * 操作方式
     */
    @Column(name = "method")
    private String method;
    /**
     * 请求URI
     */
    @Column(name = "request_uri")
    private String requestUri;
    /**
     * 操作IP地址
     */
    @Column(name = "remote_ip")
    private String remoteIp;
    /**
     * 方法类
     */
    @Column(name = "method_class")
    private String methodClass;
    /**
     * 方法名
     */
    @Column(name = "method_name")
    private String methodName;
    /**
     * 用户代理
     */
    @Column(name = "user_agent")
    private String userAgent;
    /**
     * 操作提交的数据
     */
    @Column(name = "params", columnDefinition = "text")
    private String params;
    /**
     * 执行时间
     */
    @Column(name = "time")
    private LocalDateTime time;
    /**
     * 创建者
     */
    @Column(name = "create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;


}
