package com.sunlei.compile.work1;

/**
 * @author sunLei on 2018/9/16
 * @version 1.0
 * @apiNote
 */
public class CheckPassword {
    /**预定旧密码*/
    private String oldPassword="by99YL17!";
    public boolean check(String newPassword){
        return newPassword.matches("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$") && !checkCommon(newPassword);
    }
    private boolean checkCommon(String newPassword){
        int num=0;
        for(int i=0;i<newPassword.length();i++){
            if(oldPassword.contains(String.valueOf(newPassword.charAt(i)))){
                num++;
                if(num>3){
                    return true;
                }
            }
        }
        return false;
    }
}
