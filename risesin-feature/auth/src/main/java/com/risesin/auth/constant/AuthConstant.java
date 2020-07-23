package com.risesin.auth.constant;

/**
 * 授权常量
 *
 * @author honey
 * @date 2019/12/25
 */
public interface AuthConstant {

    String ENCRYPT = "";

    /**
     * 前缀
     */
    String PROJECT_PREFIX = "risesin_";

    /**
     * oauth 相关前缀
     */
    String OAUTH_PREFIX = "oauth:";

    /**
     * 手机号登录URL
     */
    String MOBILE_TOKEN_URL = "/mobile/token";

    /**
     * 默认登录URL
     */
    String OAUTH_TOKEN_URL = "/oauth/token";

    /**
     * grant_type
     */
    String REFRESH_TOKEN = "refresh_token";

    /**
     * oauth 客户端信息
     */
    String CLIENT_DETAILS_KEY = PROJECT_PREFIX + OAUTH_PREFIX + "client:details";

    /**
     * sys_oauth_client_details 表的字段，不包括client_id、client_secret
     */
    String CLIENT_FIELDS = "client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    /**
     * JdbcClientDetailsService 查询语句
     */
    String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS
            + " from risesin_client";

    /**
     * 默认的查询语句
     */
    String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

    /**
     * 按条件client_id 查询
     */
    String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";











    /**
     * 用户ID字段
     */
    String DETAILS_USER_ID = "user_id";

    /**
     * 用户名字段
     */
    String DETAILS_USERNAME = "username";

    /**
     * 用户部门字段
     */
    String DETAILS_DEPT_ID = "dept_id";

    /**
     * 协议字段
     */
    String DETAILS_LICENSE = "license";
}
