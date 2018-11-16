package com.sunlei.compile.work1;

import static com.sunlei.utils.Assist.input;
import static com.sunlei.utils.Assist.print;

/**
 * @author sunLei on 2018/9/16
 * @version 1.0
 * @apiNote
 */
public class Test2 {
    public static void main(String[] args){
        CheckPassword checkPassword=new CheckPassword();
        for(int i=0;i<100;i++){
            print("请输入新密码(要有字母（大小写都有）、数字、符号，八个以上字符):");
            String password=input.nextLine();
            if(checkPassword.check(password)){
                print("输入正确");
            }else{
                print("输入不合法");
            }
        }
    }
}
