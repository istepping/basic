package com.sunlei.compile.work3;

import com.sunlei.compile.work1.LexcialAnalyzer;

import static com.sunlei.utils.Assist.*;

/**
 * @author sunLei on 2018/10/3 17:52
 * @version 1.0
 * @apiNote
 */
public class Test {
    public static void main(String[] args){
        LexcialAnalyzer la=new LexcialAnalyzer();
        //print(la.analyzeForParsing("n1 + n2 * n3"));
        Parsing parsing=new Parsing();
        print("请输入源码:");
        parsing.analyze(input.nextLine());
//        print(getFirst("T"));
//        print(getFirst("F"));
//        print(getFirst("T'"));
//        print(getFollow("E"));
//        print(getFollow("E'"));
//        print(getFollow("T"));
//        print(getFollow("T'"));
//        print(getFollow("F"));
//        String[] strs=getStrings("E E' T T' ");
//        print(strs.length);
//        for(int i=0;i<row;i++){
//            for(int j=0;j<column;j++){
//                System.out.print(graph[i][j]);
//            }
//            print(" ");
//        }
    }
}
