package com.risesin.systemapi.plan.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 法人代表信息实体类
 *
 * @author Darling
 */
@Data
@ApiModel("法人代表信息")
public class LegalRepresentativeBO implements Serializable {

    /**
     * 法人代表姓名
     */
    @ApiModelProperty("法人代表姓名")
    private String name;

    /**
     * 法人代表年龄
     */
    @ApiModelProperty("法人代表年龄")
    private Byte age;

    /**
     * 国籍
     */
    @ApiModelProperty("国籍")
    private String nationality;

    /**
     * 股份所占比例
     */
    @ApiModelProperty("股份所占比例")
    private BigDecimal sharerate;

    /**
     * 法人代表职业：1 公务员或企事业单位 2 世界五百强企业 3 其他
     */
    @ApiModelProperty("法人代表职业：1 公务员或企事业单位 2 世界五百强企业 3 其他")
    private Byte occupation;

}
