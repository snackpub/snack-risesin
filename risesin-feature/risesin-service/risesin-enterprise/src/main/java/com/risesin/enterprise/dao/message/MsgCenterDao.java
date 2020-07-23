package com.risesin.enterprise.dao.message;

import com.risesin.core.base.BaseDao;
import com.risesin.enterprise.message.entity.MsgCenter;

import java.util.List;

/**
 * msgCenter数据访问接口1
 *
 * @author Administrator
 */
public interface MsgCenterDao extends BaseDao<MsgCenter, Integer> {

    /**
     * 查询最新5条消息
     *
     * @param id 企业用户id
     * @return
     */
    List<MsgCenter> findTop5ByEntUserIdOrderByCreateTimeDesc(String id);


    /**
     * 查询待处理的6条最新消息
     *
     * @param id     企业用户id
     * @param status 事项处理状态：-1未处理，0已处理，1无需处理(仅通知)
     * @return
     */
    List<MsgCenter> findTop6ByEntUserIdAndStatusOrderByCreateTimeDesc(String id, Byte status);

    /**
     * 查询未处理的事项总数
     * @param id 企业用户id
     * @param status 事项处理状态：-1未处理，0已处理，1无需处理(仅通知)
     * @return
     */
    int countByEntUserIdAndStatus(String id, Byte status);

}
