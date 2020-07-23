package com.risesin.system.wrapper.system;

import com.risesin.core.jpaplus.support.BaseEntityWrapper;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.systemapi.system.entity.SysLoanAgency;
import com.risesin.systemapi.system.vo.LoanAgencyVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类, 返回视图层所需的字段
 *
 * @author honey
 * @date 2019/12/3
 **/
public class LoanAgencyWrapper extends BaseEntityWrapper<SysLoanAgency, LoanAgencyVO> {

    public static LoanAgencyWrapper build() {
        return new LoanAgencyWrapper();
    }

    @Override
    public LoanAgencyVO entityVO(SysLoanAgency entity) {
        return BeanUtil.copy(entity, LoanAgencyVO.class);
    }


    @Override
    public List<LoanAgencyVO> listVO(List<SysLoanAgency> list) {
        return list.stream().map(dept -> BeanUtil.copy(dept, LoanAgencyVO.class)).collect(Collectors.toList());
    }


}
