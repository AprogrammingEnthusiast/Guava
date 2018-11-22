package com.frank.biz.shields;


import com.frank.biz.util.Shield;

/**
 * 屏蔽实现的抽象类，提供公共方法，并且将实现类与外部隔离
 *
 * @author wangj
 * @date 2018/11/22 16:58
 * Life is so short,do something to make yourself happy,such as coding
 */

public abstract class AbstractShield implements Shield {
    /**
     * 是否清除全部，默认为false
     * @return
     */
    @Override
    public boolean isClean() {
        return false;
    }

    /**
     * 默认情况直接返回null
     * @return
     */
    public String handleNull() { return null; }

    /**
     * 默认情况直接返回空串
     * @return
     */
    public String handleEmpty() { return ""; }

    @Override
    public String shield(Object fieldValue, String[] additions) {
        // 统一处理null和""情况
        if (fieldValue == null) {
            return handleNull();
        }
        if (fieldValue.toString().isEmpty()) {
            return handleEmpty();
        }
        return doShield(fieldValue, additions);
    }

    abstract String doShield(Object fieldValue, String[] additions);
}

