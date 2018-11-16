package com.sunlei.compile.work3;

import java.util.*;

import static com.sunlei.utils.Assist.print;

/**
 * @author sunLei on 2018/10/3
 * @version 1.0
 * @apiNote
 */
public class Assist {
    /**
     * 存储文法集合
     */
    public static List<String[]> grammarsList = new ArrayList<>();
    /**
     * 终结符数组
     */
    public static final String[] endChars = {"id","+", "*", "(", ")", "$"};
    /**
     * 非终结符集
     */
    public static final String[] notEndChars = {"E", "E'", "T", "T'", "F"};
    /**
     * 文法开始符号
     */
    public static final String startChar = "E";
    /**
     * 输入符号序号表
     */
    public static Map<String, Integer> columnMap = new HashMap<>();
    /**
     * 非终结符序号表
     */
    public static Map<String, Integer> rowMap = new HashMap<>();
    public static final int row = 5;
    public static final int column = 6;
    /**
     * 分析表
     */
    public static String[][] graph = new String[row][column];
    /**分析栈*/
    public static Stack<String> strStack=new Stack<>();

    /**
     * @param input first集合的处理对象
     * @return List<String> 返回集合
     * @author sunLei on 2018/10/3 17:38
     * @apiNote first集合的计算
     */
    public static List<String> getFirst(String input) {
        List<String> first = new ArrayList<>();
        //遍历文法
        for (String[] item : grammarsList) {
            if (item[0].equals(input)) {
                if (isEndChar(item[1])) {
                    first.add(item[1]);
                } else {
                    first.addAll(getFirst(item[1]));
                }
            }
        }
        return first;
    }

    /**
     * @param input 输入
     * @return 输出集合
     * @author sunLei on 2018/10/3 18:49
     * @apiNote 获取follow集合
     */
    public static List<String> getFollow(String input) {
        List<String> follow = new ArrayList<>();
        //follow集合求解方法
        if (isStartChar(input)) {
            follow.add("$");
        }
        for (String[] item : grammarsList) {
            for (int i = 1; i < item.length; i++) {
                if (item[i].equals(input)) {
                    if (i < item.length - 1) {
                        //右边还有
                        if (isEndChar(item[i + 1])) {
                            //终结符
                            follow.add(item[i + 1]);
                        } else {
                            List<String> temp1 = delNull(getFirst(item[i + 1]));
                            for (String item1 : temp1) {
                                if (!follow.contains(item1)) {
                                    follow.add(item1);
                                }
                            }
                            if (getFirst(item[i + 1]).contains("null") && !item[0].equals(input)) {
                                List<String> temp2 = getFollow(item[0]);
                                for (String item2 : temp2) {
                                    if (!follow.contains(item2)) {
                                        follow.add(item2);
                                    }
                                }
                            }
                        }
                    } else {
                        //右边没有
                        if (!item[0].equals(input)) {
                            List<String> temp3 = getFollow(item[0]);
                            for (String item3 : temp3) {
                                if (!follow.contains(item3)) {
                                    follow.add(item3);
                                }
                            }
                        }
                    }
                }
            }
        }
        return follow;
    }

    /**
     * @param input 输入
     * @author sunLei on 2018/10/3 17:49
     * @apiNote 判断是否是终结符
     */
    public static boolean isEndChar(String input) {
        for (String endChar : endChars) {
            if (input.equals(endChar) || input.equals("null")) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param input 输入
     * @return 输出结果
     * @author sunLei on 2018/10/3 18:51
     * @apiNote 是否是文法开始符号
     */
    public static boolean isStartChar(String input) {
        if (input.equals(startChar)) {
            return true;
        }
        return false;
    }

    /**
     * 出去空集后的串
     */
    public static List<String> delNull(List<String> input) {
        List<String> output = new ArrayList<>();
        for (String item : input) {
            if (!item.equals("null")) {
                output.add(item);
            }
        }
        return output;
    }
    /**输出文法*/
    public static void output1(String[] input){
        StringBuilder output=new StringBuilder();
        for(int i=0;i<input.length;i++){
            output.append(input[i]);
            if(i==0){
                output.append("->");
            }
        }
        print(output.toString());
    }
}
