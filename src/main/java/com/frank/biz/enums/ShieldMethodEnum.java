package com.frank.biz.enums;

import com.frank.biz.shields.*;
import com.frank.biz.util.Shield;

/**
 * 属性屏蔽注解 方法
 *
 * @author wangj
 * @date 2018/11/22 16:57
 * Life is so short,do something to make yourself happy,such as coding
 */

public enum ShieldMethodEnum {

    /**
     * 什么都不输出
     */
    CLEAN("clean", CleanShield.class),

    /**
     * 屏蔽全部
     */
    ALL("all", AllShield.class),

    /**
     * 前3后4屏蔽-主要是手机134****4410
     */
    BEGIN3_END4("begin3_end4", Begin3End4Shield.class),

    /**
     * 自定义脱敏
     */
    CUSTOMIZE("customize", CustomizeShield.class);

    /**
     * 方法
     */
    private String method;

    private Class<? extends Shield> implementClass;

    /**
     * 构造方法
     */
    ShieldMethodEnum(String method, Class<? extends Shield> implementClass) {
        this.method = method;
        this.implementClass = implementClass;
    }

    public String getMethod() {
        return method;
    }

    public Class<? extends Shield> getImplementClass() {
        return implementClass;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}

