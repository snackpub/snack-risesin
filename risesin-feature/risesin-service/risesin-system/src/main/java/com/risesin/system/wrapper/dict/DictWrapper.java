package com.risesin.system.wrapper.dict;

import com.risesin.common.constant.CommonConstant;
import com.risesin.core.jpaplus.support.BaseEntityWrapper;
import com.risesin.core.tool.node.ForestNodeMerger;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.core.tool.utils.Func;
import com.risesin.core.tool.utils.SpringUtil;
import com.risesin.system.service.dict.DictServiceImpl;
import com.risesin.systemapi.dict.entity.Dict;
import com.risesin.systemapi.dict.vo.DictVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author honey
 */
public class DictWrapper extends BaseEntityWrapper<Dict, DictVO> {

    private static DictServiceImpl dictService;

    static {
        dictService = SpringUtil.getBean(DictServiceImpl.class);
    }

    public static DictWrapper build() {
        return new DictWrapper();
    }

    @Override
    public DictVO entityVO(Dict dict) {
        DictVO dictVO = BeanUtil.copy(dict, DictVO.class);
        if (Func.equals(dict.getParentId(), CommonConstant.TOP_PARENT_ID)) {
            dictVO.setParentName(CommonConstant.TOP_PARENT_NAME);
        } else {
            Dict parent = dictService.findById(dict.getParentId());
            dictVO.setParentName(parent.getDictValue());
        }
        return dictVO;
    }

    public List<DictVO> listNodeVO(List<Dict> list) {
        List<DictVO> collect = list.stream().map(this::entityVO).collect(Collectors.toList());
        return ForestNodeMerger.merge(collect);
    }

}
