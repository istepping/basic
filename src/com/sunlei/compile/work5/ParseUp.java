package com.sunlei.compile.work5;

import com.sun.nio.sctp.AbstractNotificationHandler;
import com.sunlei.compile.work1.LexcialAnalyzer;

import java.util.*;

import static com.sunlei.compile.work3.Assist.grammarsList;
import static com.sunlei.compile.work3.Assist.output1;
import static com.sunlei.utils.Assist.print;

/**
 * @author sunLei on 2018/11/4 18:45
 * @version 1.0
 * @apiNote 自下而上分析器
 */
public class ParseUp {
    public List<String[]> grammarsList = new ArrayList<>();
    public String[][] graph = new String[9][12];
    List<String> tokens=new ArrayList<>();
    private Map<String,Integer> rowMap=new HashMap<>();
    private Stack<String> mStack=new Stack<>();
    private void init(){
        String[] str1 = {"E", "E", "+","T"};
        String[] str2 = {"E", "T"};
        String[] str3 = {"T", "T","*","F"};
        String[] str4 = {"T", "F"};
        String[] str5 = {"F", "(", "E", ")"};
        String[] str6 = {"F", "id"};
        grammarsList.add(str1);
        grammarsList.add(str2);
        grammarsList.add(str3);
        grammarsList.add(str4);
        grammarsList.add(str5);
        grammarsList.add(str6);
        print("初始化文法:完成");
        rowMap.put("id",0);
        rowMap.put("+",1);
        rowMap.put("*",2);
        rowMap.put("(",3);
        rowMap.put(")",4);
        rowMap.put("$",5);
        rowMap.put("E",6);
        rowMap.put("T",7);
        rowMap.put("F",8);
        for(int i=0 ;i<9;i++){
            for(int j=0;j<12;j++){
                graph[i][j]="e";
            }
        }
        graph[0][0]="s5";
        graph[3][0]="s4";
        graph[6][0]="1";
        graph[7][0]="2";
        graph[8][0]="3";
        graph[1][1]="s6";
        graph[5][1]="acc";
        graph[1][2]="r2";
        graph[2][2]="s7";
        graph[4][2]="r2";
        graph[5][2]="r2";
        graph[1][3]="r4";
        graph[2][3]="r4";
        graph[4][3]="r4";
        graph[5][3]="r4";
        graph[0][4]="s5";
        graph[3][4]="s4";
        graph[6][4]="8";
        graph[7][4]="2";
        graph[8][4]="3";
        graph[1][5]="r6";
        graph[2][5]="r6";
        graph[4][5]="r6";
        graph[5][5]="r6";
        graph[0][6]="s5";
        graph[3][6]="s4";
        graph[7][6]="9";
        graph[8][6]="3";
        graph[0][7]="s5";
        graph[3][7]="s4";
        graph[8][7]="10";
        graph[1][8]="s6";
        graph[4][8]="s11";
        graph[1][9]="r1";
        graph[2][9]="s7";
        graph[4][9]="r1";
        graph[5][9]="r1";
        graph[1][10]="r3";
        graph[2][10]="r3";
        graph[4][10]="r3";
        graph[5][10]="r3";
        graph[1][11]="r5";
        graph[2][11]="r5";
        graph[4][11]="r5";
        graph[5][11]="r5";
        print("初始化分析表:完成");
        for(int i=0 ;i<12;i++){
            for(int j=0;j<9;j++){
                System.out.print(graph[j][i]+" ");
            }
            print("");
        }
        tokens.add("id");
        tokens.add("*");
        tokens.add("+");
        tokens.add("id");
        tokens.add("+");
        tokens.add("(");
        tokens.add("id");
        tokens.add("+");
        tokens.add("id");
        tokens.add(")");
        tokens.add("$");
        print("输入为:");
        print(tokens);
        mStack.push("0");
    }
    public void parse(){
        init();
        for (int i = 0; i < tokens.size(); i++) {
            analyzer(tokens.get(i));
        }
    }
    private void analyzer(String token){
        //print("栈"+mStack);
        //print("token"+token);
        int i=rowMap.get(token);//横坐标
        int j=Integer.parseInt(mStack.get(mStack.size()-1));
        String result=graph[i][j];
        //print("result"+result);
        if(result.equals("e")){
            print("error");
            //跳过处理
            return;
        }
        if(result.equals("acc")){
            print("接受");
            return;
        }
        char first=result.charAt(0);
        if(first=='r'){
            int last=Integer.parseInt(result.substring(1));
            String[] grammar=grammarsList.get(last-1);
            print("归约");
            output1(grammar);
            while(!mStack.pop().equals(grammar[1])){

            }
            int x=rowMap.get(grammar[0]);
            int y=Integer.parseInt(mStack.get(mStack.size()-1));
            String goTo=graph[x][y];
            if (goTo.equals("e")){
                print("error2");
            }else {
                mStack.push(grammar[0]);
                mStack.push(goTo);
                analyzer(token);
            }
        }else{
            print("移进");
            String last=result.substring(1);
            mStack.push(token);
            mStack.push(last);
        }
    }
}
