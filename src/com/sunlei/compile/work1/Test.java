package com.sunlei.compile.work1;

import static com.sunlei.utils.Assist.input;
import static com.sunlei.utils.Assist.print;

/**
 * @author sunLei on 2018/9/15
 * @version 1.0
 * @apiNote
 */
public class Test {
    public static void main(String[] args){
        //int x = 5 ; if ( x > 0 ) x := 5 ; else x := 6 ; #
        //简单词法分析器
        LexcialAnalyzer lexcialAnalyzer=new LexcialAnalyzer();
        print("请输入源代码:");
        lexcialAnalyzer.analyze(input.nextLine());
    }
}
