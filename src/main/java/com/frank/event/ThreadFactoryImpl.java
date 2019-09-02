/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.frank.event;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author wb-wj449816
 * @version $Id: ThreadFactoryImpl.java, v 0.1 2019年09月02日 17:36 wb-wj449816 Exp $
 */
public class ThreadFactoryImpl implements ThreadFactory {

    private final AtomicLong threadIndex = new AtomicLong(0);

    private final String threadNamePrefix;

    /**
     * ThreadFactoryImpl
     * @param threadNamePrefix
     */
    public ThreadFactoryImpl(final String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }

    /**
     * newThread
     * @param r
     * @return
     */
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, threadNamePrefix + "#" + this.threadIndex.incrementAndGet());
    }

}