package com.sunlei.study_static;

/**
 * @author sunLei on 2019/2/25 11:02
 * @version 1.0
 */
public class Base {
    private String baseName="base";
    public Base(){
        callName();
    }
    public void callName(){
        System.out.println(baseName);
    }
    static class Sub extends Base{
        private String baseName="sub";
        public void callName(){
            System.out.println(baseName);
        }
    }
    public static void main(String[] args){
        Base b=new Sub();//输出结果null
    }
}
