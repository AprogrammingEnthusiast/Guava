package com.frank.biz.model;

import com.frank.biz.ShieldField;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 域信息
 *
 * @author wangj
 * @date 2018/11/22 17:26
 * Life is so short,do something to make yourself happy,such as coding
 */

public class FieldInfo {

    /** 域 */
    private Field       field;

    /** ShieldField注解信息 */
    private ShieldField shieldField;

    /** 访问域的Method */
    private Method fieldAccessMethod;

    /**
     * 默认构造函数
     */
    public FieldInfo() {
        super();
    }

    /**
     * 构造函数
     *
     * @param field
     * @param shieldField
     */
    public FieldInfo(Field field, ShieldField shieldField, Method fieldAccessMethod) {
        super();
        this.field = field;
        this.shieldField = shieldField;
        this.fieldAccessMethod = fieldAccessMethod;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public ShieldField getShieldField() {
        return shieldField;
    }

    public void setShieldField(ShieldField shieldField) {
        this.shieldField = shieldField;
    }

    public Method getFieldAccessMethod() {
        return fieldAccessMethod;
    }

    public void setFieldAccessMethod(Method fieldAccessMethod) {
        this.fieldAccessMethod = fieldAccessMethod;
    }
}

