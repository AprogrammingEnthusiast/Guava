package com.frank.biz;

import com.frank.biz.enums.ShieldMethodEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 屏蔽字段
 * @author wangj
 * @date 2018/11/22 16:58
 * Life is so short,do something to make yourself happy,such as coding
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ShieldField {
    /**
     * 屏蔽方式
     * @return
     */
    ShieldMethodEnum method() default ShieldMethodEnum.ALL;

    /**
     * 附加条件值
     * @return
     */
    String[] addition() default {};
}
