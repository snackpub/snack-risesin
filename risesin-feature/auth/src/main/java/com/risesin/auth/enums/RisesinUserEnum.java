package com.risesin.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Administrator
 */

@Getter
@AllArgsConstructor
public enum RisesinUserEnum {

    /**
     * web
     */
    WEB("web", 1),

    /**
     * app
     */
    APP("app", 2),
    ;

    final String name;
    final int category;
}
