package com.frank.biz.util;

/**
 * 屏蔽器接口
 * @author wangj
 * @date 2018/11/22 16:58
 * Life is so short,do something to make yourself happy,such as coding
 */

public interface Shield {

    /**
     * 隐藏符
     */
    public static final char SHIELD_CHAR = '*';

    /**
     * 隐藏敏感信息
     *
     * @param fieldValue 需要屏蔽的值，一般是字符串
     * @return
     */
    public String shield(Object fieldValue, String[] addition);

    /**
     * 是否返回空串
     * @return
     */
    public boolean isClean();
}



