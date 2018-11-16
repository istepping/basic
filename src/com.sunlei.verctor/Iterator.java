package com.sunlei.verctor;

import java.util.ArrayList;
import java.util.List;

import static com.sunlei.utils.Assist.print;

/**
 * @author 孙磊 on 2018/8/27
 * @version 1.0
 * @apiNote 迭代器
 */
public class Iterator {
    /**
     * @author 孙磊 on 2018/8/27
     * @apiNote test
     */
    public void test(){
        List<String> list=new ArrayList<>();
        list.add("sunLei");
        list.add("sunLei");
        //迭代器遍历
        java.util.Iterator<String> it=list.iterator();//返回指向第一个元素的指针
        for(String string:list){
            print(string);
            //遍历的时候不可以调用remove 删除函数
        }
    }
}
