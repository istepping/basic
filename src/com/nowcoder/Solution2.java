package com.nowcoder;

/**
 * @author sunLei on 2019/3/19 23:37
 * @version 1.0 替换空格
 */
public class Solution2 {
    public String replaceSpace(StringBuffer str){
        StringBuilder result=new StringBuilder();
        for(int i=0;i<str.length();i++){
            char item=str.charAt(i);
            if(item!=' '){
                result.append(item);
            }else{
                result.append("%20");
            }
        }
        return result.toString();
    }
    public String replaceSpace2(StringBuffer str){
        String myStr=new String(str);
        String[] result=myStr.split("\\s+");
        StringBuilder buffer=new StringBuilder();
        for(int i=0;i<result.length-1;i++){
            buffer.append(result[i]);
            buffer.append("%20");
        }
        buffer.append(result[result.length-1]);
        if(str.charAt(str.length()-1)==' '){
            buffer.append("%20");
        }
        return buffer.toString();
    }
}
