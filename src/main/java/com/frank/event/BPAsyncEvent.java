/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.frank.event;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 异步服务事件
 * @author wb-wj449816
 * @version $Id: BPAsyncEvent.java, v 0.1 2019年09月02日 17:29 wb-wj449816 Exp $
 */
public class BPAsyncEvent extends AbstractBPEvent  {

    /**
     * context
     */
    private ExecutionContext context;
    /**
     * services
     */
    private List<Async>      asyncs = new ArrayList<>();

    /**
     * Getter method for property <tt>context</tt>.
     *
     * @return property value of context
     */
    public ExecutionContext getContext() {
        return context;
    }

    /**
     * Setter method for property <tt>context</tt>.
     *
     * @param context value to be assigned to property context
     */
    public void setContext(ExecutionContext context) {
        this.context = context;
    }

    /**
     * Getter method for property <tt>services</tt>.
     *
     * @return property value of services
     */
    public List<Async> getAsyncs() {
        return asyncs;
    }

    /**
     * Setter method for property <tt>asyncs</tt>.
     *
     * @param asyncs value to be assigned to property asyncs
     */
    public void setAsyncs(List<Async> asyncs) {
        this.asyncs = asyncs;
    }

    /**
     * toString
     *
     * @return
     */
    @Override
    public String toString() {
        return JSON.toJSONString(asyncs);
    }

}