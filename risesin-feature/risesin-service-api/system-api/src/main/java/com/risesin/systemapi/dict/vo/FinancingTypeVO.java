package com.risesin.systemapi.dict.vo;

import com.risesin.systemapi.dict.entity.FinancingType;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-09
 * @DESCRIPTION 融资类型视图对象
 * @since 1.0.0
 */
@Data
@ApiModel(value = "融资类型视图对象", description = "融资类型视图对象")
public class FinancingTypeVO extends FinancingType {
    private List<FinancingTypeVO> childList = new ArrayList<>();
}
