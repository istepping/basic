package com.nowcoder;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author sunLei on 2019/3/20 8:57
 * @version 1.0 链表从尾到头插入列表
 */
public class Solution3 {
    public static class ListNode{
        int val;
        ListNode next=null;
        ListNode(int val){
            this.val=val;
        }
    }
    /**使用栈*/
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode){
        Stack<Integer> stack=new Stack<>();
        ArrayList<Integer> list=new ArrayList<>();
        ListNode p=listNode;
        while(p!=null){
            stack.push(p.val);
            p=p.next;
        }
        int size=stack.size();
        for(int i=0;i<size;i++){
            list.add(stack.pop());
        }
        return list;
    }
    /**使用递归*/
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode){
        ArrayList<Integer> list=new ArrayList<>();
        if(listNode!=null){
            if(listNode.next!=null){
                list=printListFromTailToHead2(listNode.next);
            }
            list.add(listNode.val);
        }
        return list;
    }
}
