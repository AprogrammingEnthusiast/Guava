package com.frank.biz.shields;

/**
 * 什么都不输出
 *
 * @author wangj
 * @date 2018/11/22 16:58
 * Life is so short,do something to make yourself happy,such as coding
 */

public class CleanShield extends AbstractShield {

    /**
     * 不应该调用到这个方法，isClean为true的时候，直接不显示字段
     * @param fieldValue
     * @param addition
     * @return
     */
    @Override
    String doShield(Object fieldValue, String[] addition) {
        return "";
    }

    @Override
    public boolean isClean() {
        return true;
    }

}
