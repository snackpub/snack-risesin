package com.risesin.systemapi.dict.vo;

import com.risesin.systemapi.dict.entity.Industry;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-09
 * @DESCRIPTION 行业视图对象
 * @since 1.0.0
 */
@Data
@ApiModel(value = "行业视图对象", description = "行业视图对象")
public class IndustryVO extends Industry {
    private List<IndustryVO> childList = new ArrayList<>();
}
