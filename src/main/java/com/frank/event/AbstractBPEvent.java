/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.frank.event;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wb-wj449816
 * @version $Id: AbstractBPEvent.java, v 0.1 2019年09月02日 17:28 wb-wj449816 Exp $
 */
public abstract class AbstractBPEvent implements BPEvent {

    /**
     * 所有尚未调用成功的jobs
     */
    @JSONField(serialize = false)
    private List<BPJob> jobs = new ArrayList<>();

    /**
     * 所有job
     */
    public void addJob(BPJob job) {
        if (job == null) {
            return;
        }
        jobs.add(job);
    }

    /**
     * 删除job
     *
     * @param job
     */
    public void removeJob(BPJob job) {
        if (job == null) {
            return;
        }
        jobs.remove(job);
    }

    /**
     * 删除所有jobs
     */
    public void clearJobs() {
        jobs.clear();
    }

    /**
     * 未成功的jobId
     *
     * @return
     */
    @Override
    @JSONField(serialize = false)
    public List<BPJob> getPendingJobs() {
        return jobs;
    }

}