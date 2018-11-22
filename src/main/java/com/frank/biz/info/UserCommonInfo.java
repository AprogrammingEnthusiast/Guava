package com.frank.biz.info;

import com.frank.biz.Mfield;
import com.frank.biz.ShieldField;
import com.frank.biz.enums.ShieldMethodEnum;

/**
 * 通用用户信息类
 * @author wangj
 * @date 2018/11/22 17:12
 * Life is so short,do something to make yourself happy,such as coding
 */

public class UserCommonInfo extends BaseInfo {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2346547765682072671L;

    /**
     * name
     */
    private String name;

    /**
     * phoneNum
     */
    @ShieldField(method = ShieldMethodEnum.BEGIN3_END4)
    private String phoneNum;

    /**
     * 身份证号
     */
    @ShieldField(method = ShieldMethodEnum.CUSTOMIZE, addition = { "6", "4", "8"})
    @Mfield(num = 1)
    private String idNumber;

    public UserCommonInfo(String name, String phoneNum, String idNumber) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.idNumber = idNumber;
    }

    public UserCommonInfo() { }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
