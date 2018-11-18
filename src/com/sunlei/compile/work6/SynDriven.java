package com.sunlei.compile.work6;

import com.sunlei.compile.work1.LexcialAnalyzer;
import com.sunlei.utils.Assist;

import java.util.*;

import static com.sunlei.compile.work3.Assist.output1;
import static com.sunlei.utils.Assist.print;

/**
 * @author sunLei on 2018/11/18 13:58
 * @version 1.0
 * @apiNote 语法制导的算术表达式的扩充分析器
 */
public class SynDriven {
    private List<String[]> grammarsList = new ArrayList<>();
    private String[][] graph = new String[11][16];
    //分析表横轴的对应关系
    private Map<String, Integer> rowMap = new HashMap<>();
    private Stack<String> mStack = new Stack<>();
    private Stack<Integer> valStack = new Stack<>();
    private boolean flag = true;

    private void init() {
        String[] str1 = {"E", "E", "+", "T"};
        String[] str2 = {"E", "T"};
        String[] str3 = {"T", "T", "*", "F"};
        String[] str4 = {"T", "F"};
        String[] str5 = {"F", "(", "E", ")"};
        String[] str6 = {"F", "id"};
        String[] str7 = {"E", "E", "-", "T"};
        String[] str8 = {"T", "T", "/", "F"};
        grammarsList.add(str1);
        grammarsList.add(str2);
        grammarsList.add(str3);
        grammarsList.add(str4);
        grammarsList.add(str5);
        grammarsList.add(str6);
        grammarsList.add(str7);
        grammarsList.add(str8);
        print("初始化文法:完成");
        rowMap.put("id", 0);
        rowMap.put("+", 1);
        rowMap.put("-", 2);
        rowMap.put("*", 3);
        rowMap.put("/", 4);
        rowMap.put("(", 5);
        rowMap.put(")", 6);
        rowMap.put("$", 7);
        rowMap.put("E", 8);
        rowMap.put("T", 9);
        rowMap.put("F", 10);
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 16; j++) {
                graph[i][j] = "e";
            }
        }
        graph[0][0] = "s5";
        graph[5][0] = "s4";
        graph[8][0] = "1";
        graph[9][0] = "2";
        graph[10][0] = "3";
        graph[1][1] = "s6";
        graph[2][1] = "s7";
        graph[7][1] = "acc";
        graph[1][2] = "r2";
        graph[2][2] = "r2";
        graph[3][2] = "s8";
        graph[4][2] = "s9";
        graph[6][2] = "r2";
        graph[7][2] = "r2";
        graph[1][3] = "r4";
        graph[2][3] = "r4";
        graph[3][3] = "r4";
        graph[4][3] = "r4";
        graph[6][3] = "r4";
        graph[7][3] = "r4";
        graph[0][4] = "s5";
        graph[5][4] = "s4";
        graph[8][4] = "10";
        graph[9][4] = "2";
        graph[10][4] = "3";
        graph[1][5] = "r6";
        graph[2][5] = "r6";
        graph[3][5] = "r6";
        graph[4][5] = "r6";
        graph[6][5] = "r6";
        graph[7][5] = "r6";
        graph[0][6] = "s5";
        graph[5][6] = "s4";
        graph[9][6] = "11";
        graph[10][6] = "3";
        graph[0][7] = "s5";
        graph[5][7] = "s4";
        graph[9][7] = "12";
        graph[10][7] = "3";
        graph[0][8] = "s5";
        graph[5][8] = "s4";
        graph[10][8] = "13";
        graph[0][9] = "s5";
        graph[5][9] = "s4";
        graph[10][9] = "14";
        graph[1][10] = "r6";
        graph[2][10] = "r7";
        graph[6][10] = "r15";
        graph[1][11] = "r1";
        graph[2][11] = "r1";
        graph[3][11] = "s8";
        graph[4][11] = "s9";
        graph[6][11] = "r1";
        graph[7][11] = "r1";
        graph[1][12] = "r7";
        graph[2][12] = "r7";
        graph[3][12] = "s8";
        graph[4][12] = "s9";
        graph[6][12] = "r7";
        graph[7][12] = "r7";
        graph[1][13] = "r3";
        graph[2][13] = "r3";
        graph[3][13] = "r3";
        graph[4][13] = "r3";
        graph[6][13] = "r3";
        graph[7][13] = "r3";
        graph[1][14] = "r8";
        graph[2][14] = "r8";
        graph[3][14] = "r8";
        graph[4][14] = "r8";
        graph[6][14] = "r8";
        graph[7][14] = "r8";
        graph[1][15] = "r5";
        graph[2][15] = "r5";
        graph[3][15] = "r5";
        graph[4][15] = "r5";
        graph[6][15] = "r5";
        graph[7][15] = "r5";
        print("初始化分析表:完成");
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(graph[j][i] + " ");
            }
            print("");
        }
        mStack.push("0");
        valStack.push(0);
    }

    public void parse() {
        init();
        while (true) {
            print("请输入表达式:");
            mStack.clear();
            valStack.clear();
            mStack.push("0");
            valStack.push(0);
            String input = Assist.input.nextLine();
            LexcialAnalyzer lexcialAnalyzer = new LexcialAnalyzer();
            List<Node> nodes = lexcialAnalyzer.analyzeForDriven(input);
            for (Node node : nodes) {
                if(!analyzer(node)){
                    //出错
                    print("表达式有错误!");
                    break;
                }
            }
            print("输入1继续，其他结束:");
            String input2=Assist.input.nextLine();
            if(!input2.equals("1")){
                flag=false;
            }
        }
    }

    //规约状态下同时处理语法制导的翻译
    private boolean analyzer(Node node) {
        print(mStack);
        print(valStack);
        int i = rowMap.get(node.getNode());//横坐标
        int j = Integer.parseInt(mStack.get(mStack.size() - 1));
        String result = graph[i][j];
        //print("result"+result);
        if (result.equals("e")) {
            print("error");
            //跳过处理
            return false;
        }
        if (result.equals("acc")) {
            print("接受");
            print("E.val=" + valStack.get(mStack.size() - 2));
            return true;
        }
        char first = result.charAt(0);
        if (first == 'r') {
            int last = Integer.parseInt(result.substring(1));
            String[] grammar = grammarsList.get(last - 1);
            print("归约");
            output1(grammar);
            //弹栈
            while (!mStack.pop().equals(grammar[1])) {
            }

            int x = rowMap.get(grammar[0]);
            int y = Integer.parseInt(mStack.get(mStack.size() - 1));
            String goTo = graph[x][y];
            if (goTo.equals("e")) {
                print("error2");
            } else {
                mStack.push(grammar[0]);
                mStack.push(goTo);
                translate(last-1);
                analyzer(node);
            }
        } else {
            print("移进");
            String last = result.substring(1);
            mStack.push(node.getNode());
            mStack.push(last);
            valStack.push(node.getVal());
            valStack.push(1);
        }
        return true;
    }

    private void translate(int index) {
        switch (index) {
            case 0:
                int val = valStack.get(valStack.size() - 2) + valStack.get(valStack.size() - 6);
                for (int i = 0; i < 6; i++) {
                    valStack.pop();
                }
                valStack.push(val);
                valStack.push(1);//占位符
                break;
            case 1:
                //无需操作
                break;
            case 2:
                int val2 = valStack.get(valStack.size() - 2) * valStack.get(valStack.size() - 6);
                for (int i = 0; i < 6; i++) {
                    valStack.pop();
                }
                valStack.push(val2);
                valStack.push(1);
                break;
            case 3:
                break;
            case 4:
                int val3=valStack.get(valStack.size()-4);
                for (int i = 0; i < 6; i++) {
                    valStack.pop();
                }
                valStack.push(val3);
                valStack.push(1);
                break;
            case 5:
                break;
            case 6:
                int val6 = valStack.get(valStack.size() - 2) - valStack.get(valStack.size() - 6);
                for (int i = 0; i < 6; i++) {
                    valStack.pop();
                }
                valStack.push(val6);
                valStack.push(1);//占位符
                break;
            case 7:
                int val7 =  valStack.get(valStack.size() - 6) / valStack.get(valStack.size() - 2);
                for (int i = 0; i < 6; i++) {
                    valStack.pop();
                }
                valStack.push(val7);
                valStack.push(1);
                break;
            default:
                break;
        }
    }
}
