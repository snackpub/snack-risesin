package com.risesin.systemapi.dict.vo;

import java.time.LocalDateTime;

/**
 * 暂放这里(明天移除)
 */
public interface INode {
    String getCode();

    String getName();

    String getParentCode();

    LocalDateTime getCreateTime();

    Byte getStatus();

}
