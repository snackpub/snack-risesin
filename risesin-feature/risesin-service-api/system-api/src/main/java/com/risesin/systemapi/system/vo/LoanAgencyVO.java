package com.risesin.systemapi.system.vo;

import com.risesin.systemapi.system.entity.SysLoanAgency;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 视图实体类
 *
 * @author honey
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "LoanAgencyVO对象", description = "LoanAgencyVO对象")
public class LoanAgencyVO extends SysLoanAgency {


}
