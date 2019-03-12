package com.sunlei.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunLei on 2019/3/11 20:42
 * @version 1.0
 * @apiNote
 */
public class QuickSort {
    public static int division(List<Integer> list, int left, int right) {
        int base = list.get(left);
        while (left < right) {
            while (left < right && list.get(right) >= base) {
                right--;
            }
            list.set(left, list.get(right));
            while (left<right && list.get(left) <=base){
                left++;
            }
            list.set(right,list.get(left));
        }
        list.set(left,base);
        return left;
    }

    public static void quickSort(List<Integer> list, int left, int right) {
        if (left < right) {
            int base = division(list, left, right);
            quickSort(list, left, base - 1);
            quickSort(list, base + 1, right);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(4);
        list.add(5);
        list.add(1);
        list.add(3);
        quickSort(list, 0, list.size() - 1);
    }
}
