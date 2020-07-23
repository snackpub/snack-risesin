package com.risesin.systemapi.order.vo;

import com.risesin.systemapi.order.entity.SysOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;


/**
 * 视图返回类
 *
 * @author honey
 * @Date 2019/12/8 17:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysOrderVO extends SysOrder {

    @ApiModelProperty("缴费类型名称")
    private String expenseTypeName;

    @ApiModelProperty("产品收费项")
    private List<Map<String, Object>> feeItems;

    @ApiModelProperty("流水号")
    private String sequenceNumber;

}
