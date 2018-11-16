package com.sunlei.compile.work3;

import com.sunlei.compile.work1.LexcialAnalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.sunlei.compile.work3.Assist.*;
import static com.sunlei.utils.Assist.getString;
import static com.sunlei.utils.Assist.getStrings;
import static com.sunlei.utils.Assist.print;

/**
 * @author sunLei on 2018/10/3
 * @version 1.0
 * @apiNote 语法分析器
 */
public class Parsing {
    /**
     * 初始化文法数据
     */
    public void initGrammars() {
        print("初始化文法:完成");
        String[] str1 = {"E", "T", "E'"};
        String[] str2 = {"E'", "+", "T", "E'"};
        String[] str3 = {"E'", "null"};
        String[] str4 = {"T", "F", "T'"};
        String[] str5 = {"T'", "*", "F", "T'"};
        String[] str6 = {"T'", "null"};
        String[] str7 = {"F", "(", "E", ")"};
        String[] str8 = {"F", "id"};
        grammarsList.add(str1);
        grammarsList.add(str2);
        grammarsList.add(str3);
        grammarsList.add(str4);
        grammarsList.add(str5);
        grammarsList.add(str6);
        grammarsList.add(str7);
        grammarsList.add(str8);
        columnMap.put("id", 0);
        columnMap.put("+", 1);
        columnMap.put("*", 2);
        columnMap.put("(", 3);
        columnMap.put(")", 4);
        columnMap.put("$", 5);
        rowMap.put("E", 0);
        rowMap.put("E'", 1);
        rowMap.put("T", 2);
        rowMap.put("T'", 3);
        rowMap.put("F", 4);
        getGraph();
        print("初始化分析表:完成");
        strStack.push("$");
        strStack.push(startChar);

    }

    /**
     * 构建分析表
     */
    public void getGraph() {
        for(String[] item:grammarsList){
            if(item[1].equals("null")){
                List<String> follow = getFollow(item[0]);
                for(String f:follow){
                    graph[rowMap.get(item[0])][columnMap.get(f)]=getString(item);
                }
                continue;
            }
            if(isEndChar(item[1])){
                //print(item[1]);
                graph[rowMap.get(item[0])][columnMap.get(item[1])]=getString(item);
            }else{
                List<String> first = getFirst(item[1]);
                for(String fst:first){
                    graph[rowMap.get(item[0])][columnMap.get(fst)]=getString(item);
                }
            }
        }
        for(String item:notEndChars){
            List<String> follow = getFollow(item);
            for(String fo:follow){
                if(graph[rowMap.get(item)][columnMap.get(fo)]==null){
                    graph[rowMap.get(item)][columnMap.get(fo)]="synch";
                }
            }
        }
    }

    /**
     * @param input 程序输入
     * @author sunLei on 2018/10/3 17:43
     * @apiNote 分析器
     */
    public void analyze(String input) {
        //利用词法分析器
        LexcialAnalyzer la = new LexcialAnalyzer();
        List<String> token = la.analyzeForParsing(input);
        print("词法分析器分析结果:"+token);
        if (token.size() == 0) {
            return;
        }
        //这里进行分析前准备
        initGrammars();
        //这里进行分析算法
        for(String item:token){
            print("正在处理:"+item);
            parse(item);
        }
        print("语法分析:完成");
    }
    public void parse(String item){
        if(isEndChar(strStack.peek())){
            if(!item.equals(strStack.peek())){
                print("error:"+item);
                print("跳过处理!");
            }else{
                strStack.pop();
            }
        }else{
            String temp=graph[rowMap.get(strStack.peek())][columnMap.get(item)];
            if(temp==null){
                print("error:"+item);
                print("跳过处理!");
            }else if(temp.equals("synch")){
                print("error:"+item);
                print("同步集合中,无需跳过!");
                strStack.pop();
                parse(item);
            }else{
                String[] temps=getStrings(temp);
                strStack.pop();
                if(!temps[1].equals("null")){
                    for(int i=temps.length-1;i>=1;i--){
                        //逆序压栈
                        strStack.push(temps[i]);
                    }
                }
                output1(temps);
                parse(item);
            }
        }
    }
}
