/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.frank.event;

/**
 *
 * @author wb-wj449816
 * @version $Id: BPEventListener.java, v 0.1 2019年09月02日 17:39 wb-wj449816 Exp $
 */
public interface BPEventListener {

    /**
     * 将event 持久化为一个job，为了后续的任务补偿
     *
     * 不一定能所有的event，都需要持久化为一个job。
     * 同步handle的event 就不需要持久化为Job去补偿。
     * 异步handle的event，才需要持久化一个job去补偿
     * @param t event
     * @param exeCode job exe code
     * @return 返回此event关联的job
     */
    void schedule(BPEvent t, String exeCode);

    /**
     * 处理event，如果event处理成功，再删除此event关联的job。
     * 如果处理不成功，后续的Job会做一个补偿
     * @param t 需要处理的event
     */
    void handle(BPEvent t);

}