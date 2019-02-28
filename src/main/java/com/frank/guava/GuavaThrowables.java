/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.frank.guava;

import com.google.common.base.Throwables;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 简化异常处理的Throwables类
 *
 * 传递异常的常用方法：
 *
 * 　　1.RuntimeException propagate(Throwable)：把throwable包装成RuntimeException，用该方法保证异常传递，抛
 *      出一个RuntimeException异常
 * 　　2.void propagateIfInstanceOf(Throwable, Class<X extends Exception>) throws X：当且仅当它是一个X的实例时，传递throwable
 * 　　3.void propagateIfPossible(Throwable)：当且仅当它是一个RuntimeException和Error时，传递throwable
 * 　　4.void propagateIfPossible(Throwable, Class<X extends Throwable>) throws X：
 *      当且仅当它是一个RuntimeException和Error时，或者是一个X的实例时，传递throwable。
 *
 * @author wangj
 * @date 2018/12/13 17:02
 * Life is so short,do something to make yourself happy,such as coding
 */

public class GuavaThrowables {

    /**
     * 传递异常的常用方法：
     *
     * 　　1.RuntimeException propagate(Throwable)：把throwable包装成RuntimeException，用该方法保证异常传递，
     *      抛出一个RuntimeException异常
     * 　　2.void propagateIfInstanceOf(Throwable, Class<X extends Exception>) throws X：
     *      当且仅当它是一个X的实例时，传递throwable
     * 　　3.void propagateIfPossible(Throwable)：当且仅当它是一个RuntimeException和Error时，传递throwable
     * 　　4.void propagateIfPossible(Throwable, Class<X extends Throwable>) throws X：
     *      当且仅当它是一个RuntimeException和Error时，或者是一个X的实例时，传递throwable。
     */



    /**
     * 有时候, 当我们我们捕获异常, 并且像把这个异常传递到下一个try/catch块中。
     * Guava提供了一个异常处理工具类, 可以简单地捕获和重新抛出多个异常。例如：
     */
    public void testThrowables() {
        try {
            throw new Exception();
        } catch (Throwable t) {
            String ss = Throwables.getStackTraceAsString(t);
            System.out.println("ss:" + ss);
            Throwables.propagate(t);
        }
    }

    public void call() throws IOException {
        try {
            throw new IOException();
        } catch (Throwable t) {
            Throwables.propagateIfInstanceOf(t, IOException.class);
            throw Throwables.propagate(t);
        }
    }

    /**
     * 将检查异常转换成未检查异常,例如：
     */
    public void testCheckException(){
        try {
            URL url = new URL("http://ociweb.com");
            final InputStream in = url.openStream();
            // read from the input stream
            in.close();
        } catch (Throwable t) {
            throw Throwables.propagate(t);
        }
    }
}