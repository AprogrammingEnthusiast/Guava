package com.frank.biz.shields;

import com.frank.biz.util.SensitiveDataUtil;
import com.frank.biz.util.TracerBizNumberUtils;

/**
 * 自定义屏蔽词
 *
 * @author wangj
 * @date 2018/11/22 17:00
 * Life is so short,do something to make yourself happy,such as coding
 */

public class CustomizeShield extends AbstractShield {

    @Override
    String doShield(Object fieldValue, String[] additions) {
        // not enough params
        if (additions == null || additions.length < 3) {
            return fieldValue.toString();
        }
        try {
            int frontCharNum = TracerBizNumberUtils.toInt(additions[0]);
            int tailCharNum = TracerBizNumberUtils.toInt(additions[1]);
            int hiddenCharNum = TracerBizNumberUtils.toInt(additions[2]);
            return SensitiveDataUtil.customizeHide(fieldValue.toString(), frontCharNum, tailCharNum, hiddenCharNum);
        } catch (NumberFormatException nfe) {
            return fieldValue.toString();
        }
    }
}
