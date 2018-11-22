package com.frank.biz.util;

public class SensitiveDataUtil {

    /**
     * 自定义屏蔽位数和屏蔽位置
     *
     * <pre>
     * SensitiveDataUtil.customizeHide("13568794561",3,4,4) = "135****4561"
     * SensitiveDataUtil.customizeHide("13568794561",0,4,4) = "****4561"
     * SensitiveDataUtil.customizeHide("13568794561",3,0,4) = "135****"
     * SensitiveDataUtil.customizeHide("13568794561",3,0,8) = "135********"
     * </pre>
     *
     * @param sensitiveData
     *            原数据
     * @param frontCharNum
     *            展示前几位
     * @param tailCharNum
     *            展示后几位
     * @param hiddenCharNum
     *            展示星号*的个数
     * @return 部分隐藏的敏感数据字符串
     */
    public static String customizeHide(final String sensitiveData, final int frontCharNum, final int tailCharNum,
                                       final int hiddenCharNum) {
        if (isBlank(sensitiveData)) {
            return sensitiveData;
        }
        String tmp = sensitiveData.trim();
        int length = tmp.length();
        // 合法性检查，如果参数不合法，返回源数据内容
        if (frontCharNum < 0 || tailCharNum < 0 || hiddenCharNum < 0 || frontCharNum + tailCharNum > length) {
            return tmp;
        }

        int beginIndex = frontCharNum - 1;
        int endIndex = length - tailCharNum;

        // 原数据前半部分
        StringBuilder result = new StringBuilder();
        if (beginIndex >= 0 && beginIndex < length) {
            result.append(tmp.substring(0, frontCharNum));
        }

        // 中间*
        for (int i = 0; i < hiddenCharNum; i++) {
            result.append('*');
        }

        // 原数据后半部分
        if (endIndex >= 0 && endIndex < length) {
            result.append(tmp.substring(endIndex));
        }

        return result.toString();
    }

    /**
     * 简单判断是否为空字符串
     *
     * @param str
     * @return
     */
    private static boolean isBlank(final String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

}
