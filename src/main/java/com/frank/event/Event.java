/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.frank.event;

/**
 *
 * @author wb-wj449816
 * @version $Id: Event.java, v 0.1 2019年09月02日 17:38 wb-wj449816 Exp $
 */
public class Event {

    private BPEvent event;
    private String exeCode;

    public BPEvent getEvent() {
        return event;
    }

    public void setEvent(BPEvent event) {
        this.event = event;
    }

    public String getExeCode() {
        return exeCode;
    }

    public void setExeCode(String exeCode) {
        this.exeCode = exeCode;
    }

}