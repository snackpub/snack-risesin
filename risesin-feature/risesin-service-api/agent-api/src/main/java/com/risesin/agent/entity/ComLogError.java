package com.risesin.agent.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * comLogError实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_com_log_error")
@Data
@DynamicInsert
@ApiModel(value = "错误日志", description = "错误日志")
public class ComLogError implements Serializable {

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
     * 用户代理
     */
    @Column(name = "user_agent")
    private String userAgent;
    /**
     * 堆栈
     */
    @Column(name = "stack_trace", columnDefinition = "text")
    private String stackTrace;
    /**
     * 异常名
     */
    @Column(name = "exception_name")
    private String exceptionName;
    /**
     * 异常信息
     */
    @Column(name = "message", columnDefinition = "text")
    private String message;
    /**
     * 错误行数
     */
    @Column(name = "line_number")
    private Integer lineNumber;
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
     * 文件名
     */
    @Column(name = "file_name")
    private String fileName;
    /**
     * 方法名
     */
    @Column(name = "method_name")
    private String methodName;
    /**
     * 操作提交的数据
     */
    @Column(name = "params", columnDefinition = "text")
    private String params;
    /**
     * 执行时间
     */
    @Column(name = "time")
    private String time;
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
