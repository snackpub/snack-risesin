package com.risesin.enterprise.wrapper;

import com.risesin.core.jpaplus.support.BaseEntityWrapper;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.enterprise.message.entity.MsgCenter;
import com.risesin.enterprise.message.vo.MsgCenterVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-17
 * @DESCRIPTION 消息中心包装类
 * @since 1.0.0
 */
public class MsgCenterWrapper extends BaseEntityWrapper<MsgCenter, MsgCenterVO> {


    public static MsgCenterWrapper build() {
        return new MsgCenterWrapper();
    }

    @Override
    public MsgCenterVO entityVO(MsgCenter entity) {
        return null;
    }

    @Override
    public List<MsgCenterVO> listVO(List<MsgCenter> list) {
        List<MsgCenterVO> collect = list.stream().map(message -> {
            MsgCenterVO msgCenterVO = BeanUtil.copy(message, MsgCenterVO.class);
            msgCenterVO.setPath(converToLink(message.getMsgType()));
            return msgCenterVO;
        }).collect(Collectors.toList());
        return collect;
    }


    /**
     * 字典码转换名称
     * 消息类型:完善资料(1);资料审核失败(2);资料审核成功(3);风控审核失败(4);风控审核成功(5);支付(6);确认额度(7);放款(8);确认收款(9);
     *
     * @param code
     * @return
     */
    private String converToLink(byte code) {
        switch (code) {
            //完善资料、资料审核失败到完善资料页面
            case 1:
            case 2:
                return "完善资料页面地址";
            //支付、确认收款  到费用中心
            case 6:
            case 9:
                return "费用中心地址";
            //确认额度的通知到子方案详情
            case 7:
                return "子方案详情地址";
            // 资料审核成功、风控审核成功、风控审核失败、放款 到消息中心
            case 3:
            case 4:
            case 5:
            case 8:
                return "消息中心地址";
            default:
                return "";
        }
    }
}
