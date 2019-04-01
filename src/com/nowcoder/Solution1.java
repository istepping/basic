package com.nowcoder;

/**
 * @author sunLei on 2019/4/1 13:53
 * @version 1.0
 */
public class Solution1 {
    public boolean Find(int target, int[][] array) {
        int length = array[0].length;
        int height = array.length;
        int i = 0;
        int current;
        while (length != 0 && i < height) {
            current = array[i][length - 1];
            if (target == current) {
                return true;
            } else if (target < current) {
                length--;
            } else {
                i++;
            }
        }
        return false;
    }
}
