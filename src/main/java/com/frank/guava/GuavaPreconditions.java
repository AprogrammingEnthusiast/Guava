package com.frank.guava;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Guava中的校验方法
 *
 * Guava的preconditions有这样几个优点:
 * 在静态导入后, 方法很明确无歧义, checkNotNull可以清楚地告诉你它是干什么的, 它会抛出怎样的异常.
 * checkNotNull在验证通过后直接返回, 可以这样方便地写代码: this.field = checkNotNull(field).
 * 简单而又强大的可变参数'printf'风格的自定义错误信息.
 *
 * @author wangj
 * @date 2018/11/16 20:30
 * Life is so short,do something to make yourself happy,such as coding
 */
public class GuavaPreconditions {

    public static void main(String[] args) {
        GuavaPreconditions guavaPreconditions = new GuavaPreconditions();
        guavaPreconditions.Preconditions();
    }
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
     */
    public void Preconditions() {

        try {
            getPersonByPrecondition(8,"peida");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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

        List<Integer> intList=new ArrayList<Integer>();
        for(int i=0;i<10;i++){
            try {
                checkState(intList,9);
                intList.add(i);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        try {
            checkPositionIndex(intList,3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            checkPositionIndex(intList,13);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            checkPositionIndexes(intList,3,7);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            checkPositionIndexes(intList,3,17);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            checkPositionIndexes(intList,13,17);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            checkElementIndex(intList,6);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            checkElementIndex(intList,16);
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

    public static void checkState(List<Integer> intList, int index)throws Exception{
        //表达式为true不抛异常
        Preconditions.checkState(intList.size()<index, " intList size 不能大于"+index);
    }

    public static void checkPositionIndex(List<Integer> intList,int index) throws Exception{
        Preconditions.checkPositionIndex(index, intList.size(), "index "+index+" 不在 list中， List size为："+intList.size());
    }

    public static void checkPositionIndexes(List<Integer> intList,int start,int end) throws Exception{
        Preconditions.checkPositionIndexes(start, end, intList.size());
    }

    public static void checkElementIndex(List<Integer> intList,int index) throws Exception{
        Preconditions.checkElementIndex(index, intList.size(),"index 为 "+index+" 不在 list中， List size为： "+intList.size());
    }

}
