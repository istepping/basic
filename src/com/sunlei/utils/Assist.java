package com.sunlei.utils;

import java.util.Scanner;

/**
 * @author sunLei on 2018/8/27
 * @version 1.0
 * @apiNote 辅助类
 */
public class Assist {
    public static Scanner input=new Scanner(System.in);
    public static void outputVar(Object o1,Object o2){System.out.print("("+o1+",'"+o2+"')\t");}
    public static void output(Object o1,Object o2){System.out.print("("+o1+","+o2+")\t");}
    /**
     * @author sunLei on 2018/8/27
     * @apiNote 输出
     * @param  o 输出对象
     */
    public static void print(Object o){
        System.out.println(o);
    }
    /**字符串数组转字符串*/
    public static String getString(String[] input){
        StringBuilder output=new StringBuilder();
        for(String item:input){
            output.append(item);
            output.append(" ");
        }
        return output.toString();
    }
    /**字符串转字符串数组*/
    public static String[] getStrings(String input){
        return input.split("\\s+");
    }
}
