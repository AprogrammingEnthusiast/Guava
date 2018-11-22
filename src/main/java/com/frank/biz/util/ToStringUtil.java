package com.frank.biz.util;

import com.frank.biz.CustomClass;
import com.frank.biz.Mfield;
import com.frank.biz.ShieldField;
import com.frank.biz.constant.SystemCommonConstant;
import com.frank.biz.model.FieldInfo;
import com.frank.biz.shields.ShieldFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 日志屏蔽类
 * <p/>
 * 扩展支持多个屏蔽功能，代码重构
 *
 * @author wangj
 * @date 2018/11/22 17:19
 * Life is so short,do something to make yourself happy,such as coding
 */

public class ToStringUtil {

    /**
     * 默认返回值
     */
    private static final Object DEFAULT_VALUE = null;
    /**
     * 字符串空值返回值
     */
    private static final String RETURN_NULL = "null";
    /**
     * 左中括号
     */
    private static final String LEFT_SQUARE_BRACKET = "[";
    /**
     * 右中括号
     */
    private static final String RIGHT_SQUARE_BRACKET = "]";
    /**
     * 属性值 分隔符
     */
    private static final String PROPERTY_SEPARATING_CHARACTER = "=";
    /**
     * 分号用于分割field
     */
    private static final String SEMICOLON = ";";
    /**
     * 逗号，用于分割Collection或者数组
     */
    private static final String COMMA = ",";

    /**
     * 缓存域信息
     */
    private static Map<String, List<FieldInfo>> fieldMap = new ConcurrentHashMap<String, List<FieldInfo>>();

    /**
     * 默认嵌套层数
     */
    private static final int DEFAULT_NESTED_LAYER_NUM = 1;

    /**
     * 通过各个字段的注解来进行屏蔽
     * <p/>
     * 约定：
     * <p/>
     * 1. 对于null对象，返回null，对于空字符串，返回空字符串
     * 2. static 字段不打印
     * 3. 对于非空对象的null字段，如果不是CLEAN，则输出null，如果是CLEAN则输出空字符串
     *
     * @param object 要屏蔽的对象
     * @return 屏蔽后的返回信息
     */
    public static String reflectionToLogStringByFields(Object object) {
        return reflectionToLogStringByFields(object, DEFAULT_NESTED_LAYER_NUM);
    }

    /**
     * @param object         要屏蔽的对象
     * @param nestedLayerNum 嵌套层数
     * @return 屏蔽后的返回信息
     */
    public static String reflectionToLogStringByFields(Object object, int nestedLayerNum) {
        if (object == null) {
            return null;
        }

        // 原生类型返回toString
        if (object instanceof Byte ||
                object instanceof Short ||
                object instanceof Integer ||
                object instanceof Long ||
                object instanceof Float ||
                object instanceof Double ||
                object instanceof Character ||
                object instanceof String ||
                object instanceof Boolean ||
                object instanceof Date
        ) {
            return object.toString();
        }

        CustomClass customClass = object.getClass().getAnnotation(CustomClass.class);
        if (customClass != null) {
            return object.toString();
        }

        // 拼接各个字段toString方法
        StringBuilder sbResult = new StringBuilder();
        sbResult.append(object.getClass().getSimpleName());
        sbResult.append(LEFT_SQUARE_BRACKET);
        // 获取对象实例所有字段的列表
        List<FieldInfo> fieldList = getFieldList(object);
        // 过滤字段信息
        for (FieldInfo field : fieldList) {
            ShieldField shieldField = field.getShieldField();
            // 默认规则：
            int modifiers = field.getField().getModifiers();
            if (Modifier.isStatic(modifiers)) {
                continue;
            }

            try {
                String fieldName = field.getField().getName();
                Object propertyVal = DEFAULT_VALUE;
                if (field.getFieldAccessMethod() != null) {
                    try {
                        propertyVal = field.getFieldAccessMethod().invoke(object);
                    } catch (IllegalAccessException e) {
                        // IllegalAccessException, 为提升性能, 将fieldAccessMethod设置为null
                        field.setFieldAccessMethod(null);
                    }catch (Throwable t) {

                    }
                }

                // 有标注字段 - 按照标注类型过滤信息
                if (!isClean(shieldField)) {
                    String shieldValue = shield(shieldField, propertyVal, nestedLayerNum);
                    sbResult.append(fieldName).append(PROPERTY_SEPARATING_CHARACTER).append(shieldValue).append(SEMICOLON);
                }
            } catch (Exception e) {

            }
        }
        sbResult.append(RIGHT_SQUARE_BRACKET);
        return sbResult.toString();
    }

    /**
     * 是否啥都不输出
     *
     * @param annotation
     */
    @SuppressWarnings("SimplifiableIfStatement")
    private static boolean isClean(ShieldField annotation) {
        if (annotation == null) {
            return false;
        }
        // 获取过滤器
        Shield shield = ShieldFactory.getShielder(annotation.method());
        return shield.isClean();
    }

    /**
     * 调用过滤器开始过滤信息
     * 对容器进行处理，优先于对具体注解的处理
     *
     * @param annotation 标注信息值
     * @param value      要过滤的实例
     * @return
     */
    private static String shield(ShieldField annotation, Object value, int nestedLayerNum) {
        StringBuilder builder = new StringBuilder();
        if (value instanceof Collection) {
            Iterator iterator = ((Collection) value).iterator();
            while (iterator.hasNext()) {
                Object item = iterator.next();
                builder.append(shieldSingleObject(annotation, item, nestedLayerNum));
                builder.append(COMMA);
            }
            return builder.toString();
        } else if (value instanceof Map && annotation == null) {
            builder.append(value.toString());
            return builder.toString();
        } else if (value instanceof Object[]) {
            Object[] array = (Object[]) value;
            for (int i = 0; i < array.length; i++) {
                Object item = array[i];
                builder.append(shieldSingleObject(annotation, item, nestedLayerNum));
                builder.append(COMMA);
            }
            return builder.toString();
        } else {
            return shieldSingleObject(annotation, value, nestedLayerNum);
        }
    }

    private static String shieldSingleObject(ShieldField annotation, Object value, int nestedLayerNum) {
        // 内嵌对象的支持
        if (annotation == null) {
            if (value == null) {
                return RETURN_NULL;
            }

            if (nestedLayerNum <= 0) {
                return value.toString();
            } else {
                nestedLayerNum = nestedLayerNum - 1;
                return reflectionToLogStringByFields(value, nestedLayerNum);
            }
        }

        // 获取过滤器，必须在内嵌对象方法的后面，否则会导致解析内嵌对象失败
        Shield shield = ShieldFactory.getShielder(annotation.method());
        if (shield == null) {
            return RETURN_NULL;
        }

        return shield.shield(value, annotation.addition());
    }

    /**
     * 获得域列表
     *
     * @param object
     * @return
     */
    @SuppressWarnings("unchecked")
    private static List<FieldInfo> getFieldList(Object object) {
        String className = object.getClass().getName();
        // 从缓存中拿
        List<FieldInfo> fieldList = fieldMap.get(className);
        if (fieldList != null) {
            return fieldList;
        } else {
            fieldList = new ArrayList<>();
            // 获取数据
            appendField(fieldList, object.getClass());

            // 按annotation中的num排序
            Collections.sort(fieldList, new Comparator<FieldInfo>() {

                /**
                 * 比较次序
                 *
                 * @param field1
                 * @param field2
                 * @return
                 */
                @Override
                public int compare(FieldInfo field1, FieldInfo field2) {
                    Mfield mfield1 = field1.getField().getAnnotation(Mfield.class);
                    Mfield mfield2 = field2.getField().getAnnotation(Mfield.class);

                    if (mfield1 == null && mfield2 == null) {
                        return 0;
                    } else if (mfield1 == null) {
                        return 1;
                    } else if (mfield2 == null) {
                        return -1;
                    }

                    int f1 = mfield1.num();
                    int f2 = mfield2.num();
                    return f1 - f2;
                }
            });

            // 缓存数据
            fieldMap.put(className, fieldList);
            return fieldList;
        }
    }

    /**
     * 递归获取对象的所有字段
     *
     * @param fieldList 对象实例
     * @param cl        对象类型
     */
    private static void appendField(List<FieldInfo> fieldList, Class<?> cl) {
        // 判断对象是否
        if (Object.class.getName().equals(cl.getName())) {
            return;
        }
        for (Field field : Arrays.asList(cl.getDeclaredFields())) {
            ShieldField shieldField = field.getAnnotation(ShieldField.class);
            Method fieldAccessMethod = getFieldAccessMethod(field, cl);
            fieldList.add(new FieldInfo(field, shieldField, fieldAccessMethod));
        }
        appendField(fieldList, cl.getSuperclass());
    }

    private static Method getFieldAccessMethod(Field field, Class<?> cl) {
        String methodName = getMethodName(field);
        Method fieldAccessMethod = getMethodByName(cl, methodName);
        // 兼容boolean变量定义了getXXX，而不是isXXX方法的情况
        if (fieldAccessMethod == null && (field.getType().equals(boolean.class) || field.getType().equals(Boolean.class))) {
            String fieldName = field.getName();
            methodName = SystemCommonConstant.INVOKE_PREFIX + fieldName.substring(0, 1).toUpperCase()
                    + fieldName.substring(1, fieldName.length());
            fieldAccessMethod = getMethodByName(cl, methodName);
        }

        return fieldAccessMethod;
    }

    /**
     * 根据Field类型,得到域的 getmethod name
     *
     * @param field
     * @return
     */
    private static String getMethodName(Field field) {
        String fieldName = field.getName();
        Class fieldClass = field.getType();
        String methodName = "";
        if (fieldClass.equals(boolean.class) || fieldClass.equals(Boolean.class)) {
            // booleans getter name is isXXX
            if (fieldName.startsWith("is")) {
                methodName = fieldName;
            } else {
                methodName = SystemCommonConstant.INVOKE_BOOLEAN_PREFIX + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1, fieldName.length());
            }
        } else {
            // other types
            methodName = SystemCommonConstant.INVOKE_PREFIX + fieldName.substring(0, 1).toUpperCase()
                    + fieldName.substring(1, fieldName.length());
        }
        return methodName;
    }

    /**
     * 根据函数名及类得到函数
     *
     * @param methodName 函数名
     * @return 函数
     */
    private static Method getMethodByName(Class<?> clsRender, String methodName) {
        try {
            return clsRender.getDeclaredMethod(methodName);
        } catch (NoSuchMethodException ne) {
            /* 遍历父类 */
            Class<?> supperClass = clsRender.getSuperclass();
            if (supperClass != null && !Object.class.getName().equals(supperClass.getName())) {
                return getMethodByName(supperClass, methodName);
            }
        } catch (Throwable t) {

        }

        return null;
    }
}
