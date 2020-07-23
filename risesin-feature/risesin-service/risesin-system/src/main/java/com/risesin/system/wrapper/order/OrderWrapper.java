package com.risesin.system.wrapper.order;

import com.risesin.core.jpaplus.support.BaseEntityWrapper;
import com.risesin.core.tool.jackson.JsonUtil;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.core.tool.utils.Func;
import com.risesin.core.tool.utils.SpringUtil;
import com.risesin.system.service.dict.DictServiceImpl;
import com.risesin.system.service.order.impl.SysOrderReceiptServiceImpl;
import com.risesin.systemapi.order.entity.SysOrder;
import com.risesin.systemapi.order.vo.SysOrderVO;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author honey
 */
public class OrderWrapper extends BaseEntityWrapper<SysOrder, SysOrderVO> {

    private static SysOrderReceiptServiceImpl orderReceiptService;
    private static DictServiceImpl dictService;

    static {
        orderReceiptService = SpringUtil.getBean(SysOrderReceiptServiceImpl.class);
        dictService = SpringUtil.getBean(DictServiceImpl.class);
    }


    public static OrderWrapper build() {
        return new OrderWrapper();
    }

    @Override
    public SysOrderVO entityVO(@NotNull SysOrder order) {
        List<Map<String, Object>> feeItemList = JsonUtil.parse(order.getOtherCharges(), List.class);
        SysOrderVO orderVO = BeanUtil.copy(order, SysOrderVO.class);
        List<Map<String, Object>> feeItems = new ArrayList<>();
        //遍历费用项id查询字典value
        feeItemList.forEach(feeItem -> {
            feeItem.keySet().forEach(id -> {
                HashMap<String, Object> feeItemMap = new HashMap<>(16);
                String dictValue = dictService.findById(Func.toInt(id)).getDictValue();
                feeItemMap.put(dictValue, feeItem.get(id));
                feeItems.add(feeItemMap);
            });
        });
        orderVO.setFeeItems(feeItems);
        //查询流水号
        orderVO.setSequenceNumber(orderReceiptService.findByOrderCode(order.getId()).getSequenceNumber());
        return orderVO;
    }

    public List<SysOrderVO> listNodeVO(List<SysOrder> list) {
        return list.stream().map(dept -> BeanUtil.copy(dept, SysOrderVO.class)).collect(Collectors.toList());
    }

}
