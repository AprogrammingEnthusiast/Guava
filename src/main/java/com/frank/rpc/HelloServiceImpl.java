/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.frank.rpc;

/**
 *
 * @author wb-wj449816
 * @version $Id: HelloServiceImpl.java, v 0.1 2019年08月29日 14:37 wb-wj449816 Exp $
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello " + name;
    }

}