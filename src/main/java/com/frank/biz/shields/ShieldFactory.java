package com.frank.biz.shields;

import com.frank.biz.enums.ShieldMethodEnum;
import com.frank.biz.util.Shield;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取Shielder实例的工厂类
 *
 * @author wangj
 * @date 2018/11/22 17:32
 * Life is so short,do something to make yourself happy,such as coding
 */

public class ShieldFactory {

    /** 屏蔽器列表
     *  注意没有NESTED_OBJECT的key
     */
    private static final Map<ShieldMethodEnum, Shield> shielderMap;
    static {
        shielderMap = new HashMap<ShieldMethodEnum, Shield>(ShieldMethodEnum.values().length);
        for (ShieldMethodEnum shieldMethodEnum : ShieldMethodEnum.values()) {
            Class<? extends Shield> clazz = shieldMethodEnum.getImplementClass();
            if (clazz == null) {
                continue;
            }
            try {
                Shield shield = clazz.newInstance();
                shielderMap.put(shieldMethodEnum, shield);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static Shield getShielder(ShieldMethodEnum shieldMethodEnum) {
        return shielderMap.get(shieldMethodEnum);
    }
}
