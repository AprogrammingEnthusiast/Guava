package com.frank.biz;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定制对象，如果在class级别添加了这个注解，使用toString方法来输出
 *
 * @author wangj
 * @date 2018/11/22 17:21
 * Life is so short,do something to make yourself happy,such as coding
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface CustomClass {

}
