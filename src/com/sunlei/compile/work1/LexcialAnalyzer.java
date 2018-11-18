package com.sunlei.compile.work1;

import com.sunlei.compile.work6.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sunlei.utils.Assist.output;
import static com.sunlei.utils.Assist.outputVar;
import static com.sunlei.utils.Assist.print;

/**
 * @author sunLei on 2018/9/15
 * @version 1.0
 * @apiNote 词法分析器
 */
public class LexcialAnalyzer {
    //变量, 关键字，操作符，数字，其他.
    /**关键字*/
    private String[] keyWorld={"for","if","then","else","while","do","until","int","input","output"};
    /**词法记号表*/
    private Map<String,String> markMap=new HashMap<>();
    private void init(){
        markMap.put("for","1");
        markMap.put("if","2");
        markMap.put("then","3");
        markMap.put("else","4");
        markMap.put("while","5");
        markMap.put("do","6");
        markMap.put("id","10");
        markMap.put("num","11");
        markMap.put("+","13");
        markMap.put("-","14");
        markMap.put("*","15");
        markMap.put("/","16");
        markMap.put("until","29");
        markMap.put("input","31");
        markMap.put(":","17");
        markMap.put(":=","18");
        markMap.put("<","20");
        markMap.put("<>","21");
        markMap.put("<=","22");
        markMap.put(">","23");
        markMap.put(">=","24");
        markMap.put("=","25");
        markMap.put(";","26");
        markMap.put("(","27");
        markMap.put(")","28");
        markMap.put("#","0");
        markMap.put("int","30");
        markMap.put("output","32");
        markMap.put("error","-1");
    }
    public void analyze(String input){
        init();
        String[] words=input.split("\\s+");
        for(String word:words){
            if(isKeyWorld(word)){
                //关键字
                //print("1");
                output(markMap.get(word),word);
            }else if (isID(word)){
                //变量
                //print("2");
                outputVar(markMap.get("id"),word);
            }else if(isInMap(word)){
                //操作符,分隔符
                //print("3");
                output(markMap.get(word),word);
            }else if(isNumber(word)){
                //数字
                //print("4");
                output(markMap.get("num"),word);
            }
            else {
                //错误
                output(markMap.get("error"),"error!");
            }
        }
    }
    /**为语法分析器输出的功能*/
    public List<String> analyzeForParsing(String input){
        init();
        String[] words=input.split("\\s+");
        List<String> outputList=new ArrayList<>();
        for(String word:words){
            if(isKeyWorld(word)){
                outputList.add(word);
            }else if (isID(word)){
                outputList.add("id");
            }else if(isInMap(word)){
                //操作符
                outputList.add(word);
            }else if(isNumber(word)){
                //数字
                outputList.add(word);
            }
            else {
                //错误
                output(markMap.get("error"),"error!");
                outputList.clear();
                break;
            }
        }
        if(outputList.size()>0){
            outputList.add("$");
        }
        return outputList;
    }
    /**为语法制导的输出*/
    public List<Node> analyzeForDriven(String input){
        init();
        String[] words=input.split("\\s+");
        List<Node> outputList=new ArrayList<>();
        for(String word:words){
            Node node=new Node();
            if(isKeyWorld(word)){
                node.setNode(word);
                outputList.add(node);
            }else if (isID(word)){
                node.setNode("id");
                outputList.add(node);
            }else if(isInMap(word)){
                //操作符
                node.setNode(word);
                outputList.add(node);
            }else if(isNumber(word)){
                //数字
                node.setNode("id");
                node.setVal(Integer.parseInt(word));
                outputList.add(node);
            }
            else {
                //错误
                output(markMap.get("error"),"error!");
                outputList.clear();
                break;
            }
        }
        if(outputList.size()>0){
            Node node=new Node();
            node.setNode("$");
            outputList.add(node);
        }
        return outputList;
    }

    /**合法的变量名包括关键字*/
    private boolean isID(String id){
        return id.matches("^[a-zA-Z][_a-zA-Z0-9]*$");
    }
    private boolean isKeyWorld(String world){
        for(String item:keyWorld){
            if(item.equals(world)){
                return true;
            }
        }
        return false;
    }
    private boolean isNumber(String num){
        return num.matches("[0-9]*");
    }
    /**在词法记号表中*/
    private boolean isInMap(String world){
        return markMap.containsKey(world);
    }
}
