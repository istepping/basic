package com.sunlei.dp;

/**
 * @author sunLei on 2019/3/16 15:51
 * @version 1.0
 * @apiNote
 */
public class Dp {
    /**台阶问题*/
    private static int step(int n){
        if(n==1 || n==2){
            return n;
        }
        return step(n-1)+step(n-2);
    }
    public static void main(String[] args){
        System.out.println(step(5));
    }
}
