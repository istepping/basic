package com.sunlei.daily;

import java.math.BigInteger;

/**
 * @author sunLei on 2018/12/13 20:13
 * @version 1.0
 * @apiNote java 每日一题
 */
public class Solution2 {
    public static void main(String[] args){
        BigInteger a= new BigInteger("9999");
        for(int i=1;i<9999;i++){
            a=a.multiply(new BigInteger(String.valueOf(i)));
        }
        System.out.print(a.bitLength());
    }
}
