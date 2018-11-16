package com.frank.guava;

import com.google.common.base.Preconditions;

/**
 * Guava中的校验方法
 * @author wangj
 * @date 2018/11/16 20:30
 * Life is so short,do something to make yourself happy,such as coding
 */
public class GuavaPreconditions {

    /**
     *  1 .checkArgument(boolean) ：
     * 　功能描述：检查boolean是否为真。 用作方法中检查参数
     *　　失败时抛出的异常类型: IllegalArgumentException
     *
     *　2.checkNotNull(T)：
     *　　功能描述：检查value不为null， 直接返回value；
     *　　失败时抛出的异常类型：NullPointerException
     *
     *  3.checkState(boolean)：
     *　功能描述：检查对象的一些状态，不依赖方法参数。 例如， Iterator可以用来next是否在remove之前被调用。
     *  失败时抛出的异常类型：IllegalStateException
     *
     *　4.checkElementIndex(int index, int size)：
     *　功能描述：检查index是否为在一个长度为size的list， string或array合法的范围。 index的范围区间是[0, size)(包含0不包含size)。无需直接传入list， string或array， 只需传入大小。返回index。
     *  失败时抛出的异常类型：IndexOutOfBoundsException
     *
     *　5.checkPositionIndex(int index, int size)：
     *　功能描述：检查位置index是否为在一个长度为size的list， string或array合法的范围。 index的范围区间是[0， size)(包含0不包含size)。无需直接传入list， string或array， 只需传入大小。返回index。
     *  失败时抛出的异常类型：IndexOutOfBoundsException
     *
     *　6.checkPositionIndexes(int start, int end, int size)：
     *　功能描述：检查[start, end)是一个长度为size的list， string或array合法的范围子集。伴随着错误信息。
     *  失败时抛出的异常类型：IndexOutOfBoundsException
     * @throws Exception
     */
    public void Preconditions() throws Exception {

        getPersonByPrecondition(8,"peida");

        try {
            getPersonByPrecondition(-9,"peida");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            getPersonByPrecondition(8,"");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            getPersonByPrecondition(8,null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getPersonByPrecondition(int age,String neme)throws Exception{
        Preconditions.checkNotNull(neme, "neme为null");
        Preconditions.checkArgument(neme.length()>0, "neme为\'\'");
        Preconditions.checkArgument(age>0, "age 必须大于0");
        System.out.println("a person age:"+age+",neme:"+neme);
    }

}
