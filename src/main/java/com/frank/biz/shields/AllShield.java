package com.frank.biz.shields;

/**
 * 全部屏蔽
 *
 *  约定：对于非String非空对象，返回空字符串""
 *  对于null或者空字符串，遵照ToStringUtil的约定，返回对象本身
 * @author wangj
 * @date 2018/11/22 16:57
 * Life is so short,do something to make yourself happy,such as coding
 */

public class AllShield extends AbstractShield {

    @Override
    public String handleNull() {
        return "***";
    }

    @Override
    public String handleEmpty() {
        return "***";
    }

    @Override
    String doShield(Object fieldValue, String[] addition) {
        return "***";
    }
}

