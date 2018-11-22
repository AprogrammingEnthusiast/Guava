package com.frank.biz.info;

import com.frank.biz.util.ToStringUtil;

import java.io.Serializable;

/**
 * 基础类
 *
 * @author wangj
 * @date 2018/11/22 17:06
 * Life is so short,do something to make yourself happy,such as coding
 */

public abstract class BaseInfo implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 2865922429468950537L;

    @Override
    public String toString() {
        return ToStringUtil.reflectionToLogStringByFields(this);
    }
}
