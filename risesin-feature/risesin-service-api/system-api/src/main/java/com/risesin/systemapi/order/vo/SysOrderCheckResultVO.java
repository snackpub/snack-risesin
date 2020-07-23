package com.risesin.systemapi.order.vo;

import com.risesin.systemapi.order.entity.SysOrderCheckResult;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;

/**
 * 视图返回类
 *
 * @author honey
 * @Date 2019/12/8 20:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysOrderCheckResultVO extends SysOrderCheckResult {

    @ApiModelProperty("收款方")
    private String payee;

    @ApiModelProperty("收款方银行开户行")
    private String payeeBankName;

    @ApiModelProperty("收款方银行卡号")
    private String payeeBankCardCode;

}
