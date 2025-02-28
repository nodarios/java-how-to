package com.cmp.javahowto.dsa;

import java.util.*;

public class Sort {

    public static void main(String[] args) {
        insertionSort();
    }

    public static void insertionSort() {
        // first element is considered to be sorted
        // start with next element and compare it with previous elements and insert where appropriate
        // no swap, only shifts
        int[] arr = new int[]{6, 4, 2, 8, 9, 7};
        for (int i = 1; i < arr.length; i++) {
            int tempValue = arr[i];
            int tempIndex = i;
            for (int j = i - 1; j >= 0 && tempValue < arr[j]; j--) {
                arr[tempIndex] = arr[j];
                tempIndex = j;
            }
            if (tempIndex != i) {
                arr[tempIndex] = tempValue;
            }
            //int j = i - 1;
            //while (j >= 0 && tempValue < arr[j]) {
            //    arr[j + 1] = arr[j];
            //    j--;
            //}
            //if (i - 1 != j) {
            //    arr[j + 1] = tempValue;
            //}
        }
        System.out.println(Arrays.toString(arr));
    }

}
