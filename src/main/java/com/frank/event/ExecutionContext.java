/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.frank.event;

import java.util.*;

/**
 * 流程运行上下文
 * @author wb-wj449816
 * @version $Id: ExecutionContext.java, v 0.1 2019年09月02日 17:29 wb-wj449816 Exp $
 */
public class ExecutionContext {

    /**
     * 流向节点名称
     */
    private String              toNode;
    /**
     * 备注
     */
    private String              memo;
    /**
     * 操作
     */
    private String              operate;
    /**
     * 唯一活动id
     */
    private String              activityCode;
    /**
     * 是否是预演
     */
    private boolean             preview     = false;
    /**
     * 是否是预发隔离运行
     */
    private boolean             isolate     = true;
    /**
     * 流程的变量（需要的时候，会存数据库）
     */
    private Map<String, Object> variables   = new HashMap<>();
    /**
     * 临时的变量（不存数据库）
     */
    private Map<String, Object> temporaries = new HashMap<>();
    /**
     * 运行计数器
     */
    private int                 counter     = 0;

    /**
     * 是否立即处理vesionChangeEvent
     */
    private boolean             processVersionChange;

    /**
     * 计数器自增
     */
    public void incCounter() {
        this.counter++;
    }

    /**
     * Setter method for property <tt>counter</tt>.
     *
     * @param counter
     *         value to be assigned to property counter
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * Getter method for property <tt>counter</tt>.
     *
     * @return property value of counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Getter method for property <tt>variables</tt>.
     *
     * @return property value of variables
     */
    public Map<String, Object> getVariables() {
        return variables;
    }

    /**
     * Getter method for property <tt>temporaries</tt>.
     *
     * @return property value of temporaries
     */
    public Map<String, Object> getTemporaries() {
        return temporaries;
    }

    /**
     * Getter method for property <tt>toNode</tt>.
     *
     * @return property value of toNode
     */
    public String getToNode() {
        return toNode;
    }

    /**
     * Setter method for property <tt>toNode</tt>.
     *
     * @param toNode
     *         value to be assigned to property toNode
     */
    public void setToNode(String toNode) {
        this.toNode = toNode;
    }

    /**
     * Getter method for property <tt>memo</tt>.
     *
     * @return property value of memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * Setter method for property <tt>memo</tt>.
     *
     * @param memo
     *         value to be assigned to property memo
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * Getter method for property <tt>operate</tt>.
     *
     * @return property value of operate
     */
    public String getOperate() {
        return operate;
    }

    /**
     * Setter method for property <tt>operate</tt>.
     *
     * @param operate
     *         value to be assigned to property operate
     */
    public void setOperate(String operate) {
        this.operate = operate;
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
     * @param activityCode
     *         value to be assigned to property activityCode
     */
    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    /**
     * Getter method for property <tt>preview</tt>.
     *
     * @return property value of preview
     */
    public boolean isPreview() {
        return preview;
    }

    /**
     * Setter method for property <tt>preview</tt>.
     *
     * @param preview
     *         value to be assigned to property preview
     */
    public void setPreview(boolean preview) {
        this.preview = preview;
    }

    /**
     * Getter method for property <tt>isolate</tt>.
     *
     * @return property value of isolate
     */
    public boolean isIsolate() {
        return isolate;
    }

    /**
     * Setter method for property <tt>isolate</tt>.
     *
     * @param isolate
     *         value to be assigned to property isolate
     */
    public void setIsolate(boolean isolate) {
        this.isolate = isolate;
    }

    /**
     * Setter method for property <tt>variables</tt>.
     *
     * @param variables
     *         value to be assigned to property variables
     */
    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }


    public boolean isProcessVersionChange() {
        return processVersionChange;
    }

    public void setProcessVersionChange(boolean processVersionChange) {
        this.processVersionChange = processVersionChange;
    }

}