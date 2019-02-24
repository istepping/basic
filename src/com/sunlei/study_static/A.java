package com.sunlei.study_static;

/**
 * @author sunLei on 2019/2/24 22:41
 * @version 1.0
 */
public class A {
    static{
        System.out.println("A static");
    }
    private static String staticStr=getStaticStr();
    private String str=getStr();
    {
        System.out.println("A 实例块");
    }
    public A(){
        System.out.println("A 构造方法");
    }
    private static String getStaticStr(){
        System.out.println("A staticStr 初始化");
        return null;
    }
    private String getStr(){
        System.out.println("A 实例属性初始化");
        return null;
    }
    public static void main(String[] args){
        new B();
        new B();
    }
}
