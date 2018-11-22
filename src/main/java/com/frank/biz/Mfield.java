package com.frank.biz;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *  对需要监控字段进行注解
 *
 *  <Li>支持在父类中进行注解，但不支持类变量中的自定义对象
 *
 * @author wangj
 * @date 2018/11/22 17:22
 * Life is so short,do something to make yourself happy,such as coding
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Mfield {

    /** 监控参数,在日志中的排列顺序 */
    int num();
}
