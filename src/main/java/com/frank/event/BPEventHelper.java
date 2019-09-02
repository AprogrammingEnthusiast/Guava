/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.frank.event;

import java.util.List;

/**
 *
 * @author wb-wj449816
 * @version $Id: BPEventHelper.java, v 0.1 2019年09月02日 17:24 wb-wj449816 Exp $
 */
public class BPEventHelper {

    /**
     * the event manager
     */
    private static BPEventManager em;

    /**
     * 存入异步服务事件
     *
     * @param event
     */
    public static void pushAsyncEvent(BPAsyncEvent event) {
        em.push(event);
    }

    /**
     * 清空缓冲
     */
    static void flush(boolean send) {
        em.flush(send);
    }

    /**
     * 获取队列中的databus event
     * @return
     */
    public static List<BPEvent> getAllEvents() {
        return em.getAllPendingEvents();
    }

    /**
     * Setter method for property <tt>em</tt>.
     *
     * @param em value to be assigned to property em
     */
    public void setEm(BPEventManager em) {
        BPEventHelper.em = em;
    }

}