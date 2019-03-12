package com.sunlei.ds;

/**
 * @author sunLei on 2019/3/1 19:21
 * @version 1.0
 * @apiNote
 */
public class SingleLinkedList {
    private class Node {
        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    public SingleLinkedList() {
        this.size = 0;
        this.head = null;
    }

    //表头增加
    public Object addHead(Object obj) {
        Node node = new Node(obj);
        if (size == 0) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
        return obj;
    }

    //表头删除
    public Object deleteHead() {
        Object obj = head.data;
        head = head.next;
        size--;
        return obj;
    }

    //查找指定元素
    public Node find(Object obj) {
        Node current = head;
        int tempSize = size;
        while (tempSize > 0) {
            if (current.data.equals(obj)) {
                return current;
            } else {
                current = current.next;
            }
            tempSize--;
        }
        return null;
    }

    //删除指定元素


    //判断是否为空
    public boolean isEmpty() {
        return size == 0;
    }
}
