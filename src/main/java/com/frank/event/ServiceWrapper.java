/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.frank.event;

/**
 * 服务包装器
 * @author wb-wj449816
 * @version $Id: ServiceWrapper.java, v 0.1 2019年09月02日 17:33 wb-wj449816 Exp $
 */
public class ServiceWrapper {

    /**
     * 事件
     */
    private   BPEventManager         bpEventManager;

    /**
     * Getter method for property <tt>bpEventManager</tt>.
     *
     * @return property value of bpEventManager
     */
    public BPEventManager getBpEventManager() {
        return bpEventManager;
    }

    /**
     * Setter method for property <tt>bpEventManager</tt>.
     *
     * @param bpEventManager value to be assigned to property bpEventManager
     */
    public void setBpEventManager(BPEventManager bpEventManager) {
        this.bpEventManager = bpEventManager;
    }

}