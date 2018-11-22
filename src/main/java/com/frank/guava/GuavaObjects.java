package com.frank.guava;

import com.google.common.base.Objects;

/**
 * Guava复写的Object常用方法
 *
 * @author wangj
 * @date 2018/11/19 11:26
 * Life is so short,do something to make yourself happy,such as coding
 */
public class GuavaObjects {

    public static void main(String[] args) {
        String string = "this is a test";
        System.out.println(string.substring(10));
    }
    /**
     * equals方法：
     * <p>
     * equals是一个经常需要覆写的方法， 可以查看Object的equals方法注释， 对equals有几个性质的要求：
     * 　1. 自反性reflexive：任何非空引用x，x.equals(x)返回为true；
     * 　2. 对称性symmetric：任何非空引用x和y，x.equals(y)返回true当且仅当y.equals(x)返回true；
     * 　3. 传递性transitive：任何非空引用x和y，如果x.equals(y)返回true，并且y.equals(z)返回true，那么x.equals(z)返回true；
     * 　4. 一致性consistent：两个非空引用x和y，x.equals(y)的多次调用应该保持一致的结果，（前提条件是在多次比较之间没有修改x和y用于比较的相关信息）；
     * 　5. 对于所有非null的值x， x.equals(null)都要返回false。 (如果你要用null.equals(x)也可以，会报NullPointerException)。
     * <p>
     * 当我们要覆写的类中某些值可能为null的时候，就需要对null做很多判断和分支处理。 使用Guava的Objects.equal方法可以避免这个问题，
     * 使得equals的方法的覆写变得更加容易， 而且可读性强，简洁优雅。
     */
    public void equalTest() {
        System.out.println(Objects.equal("a", "a"));
        System.out.println(Objects.equal(null, "a"));
        System.out.println(Objects.equal("a", null));
        System.out.println(Objects.equal(null, null));
        System.out.println(Objects.equal(new Person("peida", 23), new Person("peida", 23)));
        Person person = new Person("peida", 23);
        System.out.println(Objects.equal(person, person));
    }

    /**
     * hashCode方法：
     *  <p>
     *  当覆写（override）了equals()方法之后，必须也覆写hashCode()方法，反之亦然。这个方法返回一个整型值（hash code value），如果两个
     *  对象被equals()方法判断为相等，那么它们就应该拥有同样的hash code。Object类的hashCode()方法为不同的对象返回不同的值，Object类的
     *  hashCode值表示的是对象的地址。
     *  <p>
     *  hashCode的一般性契约（需要满足的条件）如下：
     *   1.在Java应用的一次执行过程中，如果对象用于equals比较的信息没有被修改，那么同一个对象多次调用hashCode()方法应该返回同一个整型值。
     *   应用的多次执行中，这个值不需要保持一致，即每次执行都是保持着各自不同的值。
     *   2.如果equals()判断两个对象相等，那么它们的hashCode()方法应该返回同样的值。
     *   3.并没有强制要求如果equals()判断两个对象不相等，那么它们的hashCode()方法就应该返回不同的值。即，两个对象用equals()方法比较返回false，
     *   它们的hashCode可以相同也可以不同。但是，应该意识到，为两个不相等的对象产生两个不同的hashCode可以改善哈希表的性能。
     *   <p>
     *  写一个hashCode本来也不是很难，但是Guava提供给我们了一个更加简单的方法--Objects.hashCode(Object ...)， 这是个可变参数的方法，参
     *  数列表可以是任意数量，所以可以像这样使用Objects.hashCode(field1， field2， ...， fieldn)。非常方便和简洁。
     */
    public void hashcodeTest() {
        System.out.println(Objects.hashCode("a"));
        System.out.println(Objects.hashCode("a"));
        System.out.println(Objects.hashCode("a", "b"));
        System.out.println(Objects.hashCode("b", "a"));
        System.out.println(Objects.hashCode("a", "b", "c"));

        Person person = new Person("peida", 23);
        System.out.println(Objects.hashCode(person));
        System.out.println(Objects.hashCode(person));
    }

    public void toStringTest() {
        System.out.println(Objects.toStringHelper(this).add("x", 1).toString());
        System.out.println(Objects.toStringHelper(Person.class).add("x", 1).toString());

        Person person=new Person("peida",23);
        String result = Objects.toStringHelper(Person.class)
                .add("name", person.name)
                .add("age", person.age).toString();
        System.out.print(result);
    }

}

class Person {
    public String name;
    public int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}