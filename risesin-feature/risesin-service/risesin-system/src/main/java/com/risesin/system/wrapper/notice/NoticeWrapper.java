package com.risesin.system.wrapper.notice;

import com.risesin.core.jpaplus.support.BaseEntityWrapper;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.core.tool.utils.DigestUtil;
import com.risesin.core.tool.utils.SpringUtil;
import com.risesin.systemapi.feign.IDictClient;
import com.risesin.systemapi.notice.entity.SysNotice;
import com.risesin.systemapi.notice.vo.NoticeVO;

/**
 * 视图返回包装类
 *
 * @author honey
 * @date 2019/12/15 14:24
 */
public class NoticeWrapper extends BaseEntityWrapper<SysNotice, NoticeVO> {

    private static IDictClient dictClient;

    static {
        dictClient = SpringUtil.getBean(IDictClient.class);
    }

    public static NoticeWrapper build() {
        return new NoticeWrapper();
    }

    @Override
    public NoticeVO entityVO(SysNotice entity) {
        return BeanUtil.copy(entity, NoticeVO.class);
    }


}
