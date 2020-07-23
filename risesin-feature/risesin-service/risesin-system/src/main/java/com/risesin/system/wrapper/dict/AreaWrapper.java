package com.risesin.system.wrapper.dict;

import com.risesin.core.jpaplus.support.BaseEntityWrapper;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.systemapi.dict.bo.AreaBO;
import com.risesin.systemapi.dict.entity.Area;
import com.risesin.systemapi.dict.vo.AreaVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @AUTHOR Darling
 * @CREATE 2019-11-08
 * @DESCRIPTION 区域包装类
 * @since 1.0.0
 */
public class AreaWrapper extends BaseEntityWrapper<Area, AreaVO> {

    public static AreaWrapper build() {
        return new AreaWrapper();
    }

    /**
     * District转DistrictVO
     * @param district District
     * @return
     */

    /**
     * Area转AreaVO
     *
     * @param area
     * @return
     */

    public AreaVO entityVO(Area area) {
        AreaVO districtVO = BeanUtil.copy(area, AreaVO.class);
        return districtVO;
    }


    public Area boToPo(AreaBO districtBO) {
        Area district = BeanUtil.copy(districtBO, Area.class);
        return district;
    }


    public List<AreaVO> listVO(List<Area> list) {
        List<AreaVO> collect = list.stream().map(area -> BeanUtil.copy(area, AreaVO.class)).collect(Collectors.toList());
        return collect;
    }
}
