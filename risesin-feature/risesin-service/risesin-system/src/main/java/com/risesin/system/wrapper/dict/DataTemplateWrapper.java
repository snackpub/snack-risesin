package com.risesin.system.wrapper.dict;

import com.risesin.core.jpaplus.support.BaseEntityWrapper;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.systemapi.dict.entity.DataTemplate;
import com.risesin.systemapi.dict.vo.DataTemplateVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 视图包装类
 *
 * @author honey
 * @date 2019/12/13 16:08
 */
public class DataTemplateWrapper extends BaseEntityWrapper<DataTemplate, DataTemplateVO> {

    public static DataTemplateWrapper build() {
        return new DataTemplateWrapper();
    }

    @Override
    public DataTemplateVO entityVO(DataTemplate entity) {
        return BeanUtil.copy(entity, DataTemplateVO.class);
    }

    @Override
    public List<DataTemplateVO> listVO(List<DataTemplate> list) {
        return list.stream().map(dataTemplate -> BeanUtil.copy(dataTemplate, DataTemplateVO.class)).collect(Collectors.toList());
    }
}
