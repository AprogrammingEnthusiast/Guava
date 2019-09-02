/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.frank.event;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

/**
 *
 * @author wb-wj449816
 * @version $Id: BPJob.java, v 0.1 2019年09月02日 17:26 wb-wj449816 Exp $
 */
public class BPJob {

    /**  */
    private static final long serialVersionUID = 7055998454676034671L;

    /**
     * This property corresponds to db column <tt>process_instance_id</tt>.
     */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private long processInstanceId;

    /**
     * This property corresponds to db column <tt>task_id</tt>.
     */
    private String activityCode;

    /**
     * This property corresponds to db column <tt>retry_times</tt>.
     */
    private int retryTimes;

    /**
     * This property corresponds to db column <tt>gmt_exe</tt>.
     */
    private Date gmtExe;

    /**
     * This property corresponds to db column <tt>ignore_weekend</tt>.
     */
    private int ignoreWeekend;

    /**
     * This property corresponds to db column <tt>exe_strategy</tt>.
     */
    private String exeStrategy;

    /**
     * This property corresponds to db column <tt>transition_to</tt>.
     */
    private String transitionTo;

    /**
     * This property corresponds to db column <tt>context</tt>.
     */
    private String context;

    /**
     * This property corresponds to db column <tt>time_interval</tt>.
     */
    private String timeInterval;

    /**
     * This property corresponds to db column <tt>exe_code</tt>.
     */
    private String exeCode;

    /**
     * This property corresponds to db column <tt>enforce</tt>.
     */
    private int enforce;

    /**
     * This property corresponds to db column <tt>prevJobId</tt>.
     */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private long prevJobId;

    /**
     * This property corresponds to db column <tt>target_tnt_inst_id</tt>.
     */
    private String targetTntInstId;

    /**
     * This property corresponds to db column <tt>flow_level</tt>.
     * 不落DB的job无此属性值，请注意
     */
    private int flowLevel;

    /**
     * Getter method for property <tt>processInstanceId</tt>.
     *
     * @return property value of processInstanceId
     */
    public long getProcessInstanceId() {
        return processInstanceId;
    }

    /**
     * Setter method for property <tt>processInstanceId</tt>.
     *
     * @param processInstanceId value to be assigned to property processInstanceId
     */
    public void setProcessInstanceId(long processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    /**
     * Getter method for property <tt>activityCode</tt>.
     *
     * @return property value of activityCode
     */
    public String getActivityCode() {
        return activityCode;
    }

    /**
     * Setter method for property <tt>activityCode</tt>.
     *
     * @param activityCode value to be assigned to property activityCode
     */
    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    /**
     * Getter method for property <tt>retryTimes</tt>.
     *
     * @return property value of retryTimes
     */
    public int getRetryTimes() {
        return retryTimes;
    }

    /**
     * Setter method for property <tt>retryTimes</tt>.
     *
     * @param retryTimes value to be assigned to property retryTimes
     */
    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    /**
     * Getter method for property <tt>gmtExe</tt>.
     *
     * @return property value of gmtExe
     */
    public Date getGmtExe() {
        return gmtExe;
    }

    /**
     * Setter method for property <tt>gmtExe</tt>.
     *
     * @param gmtExe value to be assigned to property gmtExe
     */
    public void setGmtExe(Date gmtExe) {
        this.gmtExe = gmtExe;
    }

    /**
     * Getter method for property <tt>ignoreWeekend</tt>.
     *
     * @return property value of ignoreWeekend
     */
    public int getIgnoreWeekend() {
        return ignoreWeekend;
    }

    /**
     * Setter method for property <tt>ignoreWeekend</tt>.
     *
     * @param ignoreWeekend value to be assigned to property ignoreWeekend
     */
    public void setIgnoreWeekend(int ignoreWeekend) {
        this.ignoreWeekend = ignoreWeekend;
    }

    /**
     * Getter method for property <tt>exeStrategy</tt>.
     *
     * @return property value of exeStrategy
     */
    public String getExeStrategy() {
        return exeStrategy;
    }

    /**
     * Setter method for property <tt>exeStrategy</tt>.
     *
     * @param exeStrategy value to be assigned to property exeStrategy
     */
    public void setExeStrategy(String exeStrategy) {
        this.exeStrategy = exeStrategy;
    }

    /**
     * Getter method for property <tt>transitionTo</tt>.
     *
     * @return property value of transitionTo
     */
    public String getTransitionTo() {
        return transitionTo;
    }

    /**
     * Setter method for property <tt>transitionTo</tt>.
     *
     * @param transitionTo value to be assigned to property transitionTo
     */
    public void setTransitionTo(String transitionTo) {
        this.transitionTo = transitionTo;
    }

    /**
     * Getter method for property <tt>context</tt>.
     *
     * @return property value of context
     */
    public String getContext() {
        return context;
    }

    /**
     * Setter method for property <tt>context</tt>.
     *
     * @param context value to be assigned to property context
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * Getter method for property <tt>timeInterval</tt>.
     *
     * @return property value of timeInterval
     */
    public String getTimeInterval() {
        return timeInterval;
    }

    /**
     * Setter method for property <tt>timeInterval</tt>.
     *
     * @param timeInterval value to be assigned to property timeInterval
     */
    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    /**
     * Getter method for property <tt>exeCode</tt>.
     *
     * @return property value of exeCode
     */
    public String getExeCode() {
        return exeCode;
    }

    /**
     * Setter method for property <tt>exeCode</tt>.
     *
     * @param exeCode value to be assigned to property exeCode
     */
    public void setExeCode(String exeCode) {
        this.exeCode = exeCode;
    }

    /**
     * Getter method for property <tt>enforce</tt>.
     *
     * @return property value of enforce
     */
    public int getEnforce() {
        return enforce;
    }

    /**
     * Setter method for property <tt>enforce</tt>.
     *
     * @param enforce value to be assigned to property enforce
     */
    public void setEnforce(int enforce) {
        this.enforce = enforce;
    }

    /**
     * Getter method for property <tt>prevJobId</tt>.
     *
     * @return property value of prevJobId
     */
    public long getPrevJobId() {
        return prevJobId;
    }

    /**
     * Setter method for property <tt>prevJobId</tt>.
     *
     * @param prevJobId value to be assigned to property prevJobId
     */
    public void setPrevJobId(long prevJobId) {
        this.prevJobId = prevJobId;
    }

    /**
     * Getter method for property <tt>targetTntInstId</tt>.
     *
     * @return property value of targetTntInstId
     */
    public String getTargetTntInstId() {
        return targetTntInstId;
    }

    /**
     * Setter method for property <tt>targetTntInstId</tt>.
     *
     * @param targetTntInstId value to be assigned to property targetTntInstId
     */
    public void setTargetTntInstId(String targetTntInstId) {
        this.targetTntInstId = targetTntInstId;
    }

    /**
     * Getter method for property <tt>flowLevel</tt>.
     *
     * @return property value of flowLevel
     */
    public int getFlowLevel() {
        return flowLevel;
    }

    /**
     * Setter method for property <tt>flowLevel</tt>.
     *
     * @param flowLevel value to be assigned to property flowLevel
     */
    public void setFlowLevel(int flowLevel) {
        this.flowLevel = flowLevel;
    }

}