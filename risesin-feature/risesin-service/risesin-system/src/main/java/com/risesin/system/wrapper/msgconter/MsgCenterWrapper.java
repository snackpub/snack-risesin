package com.risesin.system.wrapper.msgconter;

import com.risesin.core.jpaplus.support.BaseEntityWrapper;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.core.tool.utils.SpringUtil;
import com.risesin.systemapi.feign.IDictClient;
import com.risesin.systemapi.message.entity.MsgCenter;
import com.risesin.systemapi.message.vo.MsgCenterVO;

/**
 * 视图返回包装类
 *
 * @author honey
 * @date 2019/12/15 14:24
 */
public class MsgCenterWrapper extends BaseEntityWrapper<MsgCenter, MsgCenterVO> {

    private static IDictClient dictClient;

    static {
        dictClient = SpringUtil.getBean(IDictClient.class);
    }

    public static MsgCenterWrapper build() {
        return new MsgCenterWrapper();
    }

    @Override
    public MsgCenterVO entityVO(MsgCenter entity) {
        return BeanUtil.copy(entity, MsgCenterVO.class);
    }
}
