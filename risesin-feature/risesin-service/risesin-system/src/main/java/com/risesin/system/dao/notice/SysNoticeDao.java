package com.risesin.system.dao.notice;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.notice.entity.SysNotice;

import java.util.List;

/**
 * sysNotice数据访问接口
 *
 * @author Administrator
 */
public interface SysNoticeDao extends BaseDao<SysNotice, Integer> {

    /**
     * 查询前最新前5条公告
     * ... where status = 0 order by create_time desc
     *
     * @param status
     * @return
     */
    List<SysNotice> findByStatusOrderByCreateTimeDesc(Byte status);

}
