/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.frank.event;

/**
 * 异步的
 * @author wb-wj449816
 * @version $Id: Async.java, v 0.1 2019年09月02日 17:31 wb-wj449816 Exp $
 */
public interface Async {

    /**
     * 是否关联执行
     * @return
     */
    boolean isCascade();

    /**
     * 执行它
     * @return
     */
    boolean run(ExecutionContext context);

    /**
     * 生成job
     */
    BPJob schedule(ExecutionContext context);

    /**
     * get the job
     * @return
     */
    BPJob getJob();

}