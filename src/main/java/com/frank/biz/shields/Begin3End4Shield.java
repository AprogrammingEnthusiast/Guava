package com.frank.biz.shields;

import com.frank.biz.util.SensitiveDataUtil;

/**
 * 前3后4屏蔽器
 *
 * @author wangj
 * @date 2018/11/22 16:59
 * Life is so short,do something to make yourself happy,such as coding
 */

public class Begin3End4Shield extends AbstractShield {

    @Override
    public String doShield(Object fieldValue, String[] addition) {
        int length = fieldValue.toString().trim().length();
        return SensitiveDataUtil.customizeHide(fieldValue.toString(), 3, 4, length - 7);
    }
}
