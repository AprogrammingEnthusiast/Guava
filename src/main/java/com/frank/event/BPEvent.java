/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.frank.event;

import java.util.List;

/**
 *
 * @author wb-wj449816
 * @version $Id: BPEvent.java, v 0.1 2019年09月02日 17:25 wb-wj449816 Exp $
 */
public interface BPEvent {

    /**
     * 获取所有未完成的job
     * @return
     */
    List<BPJob> getPendingJobs();

}