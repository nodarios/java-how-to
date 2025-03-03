package com.cmp.javahowto.dsa;

import java.util.*;

public class Sort {

    public static void main(String[] args) {
        //int[] arr = new int[]{7, 5, 2, 4, 6, 3};
        //bubbleSort(arr);
        //System.out.println(Arrays.toString(arr));

        //int[] arr = new int[]{6, 3, 2, 7, 8, 4};
        //selectionSort(arr);
        //System.out.println(Arrays.toString(arr));

        //int[] arr = new int[]{6, 4, 2, 8, 9, 7};
        //insertionSort(arr);
        //System.out.println(Arrays.toString(arr));

        int[] arr = new int[]{5, 2, 6, 1, 4, 3};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    // takes O(n log n)
    public static void quickSort(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int pivot = findPivot(arr, leftIndex, rightIndex);
            quickSort(arr, leftIndex, pivot - 1);
            quickSort(arr, pivot + 1, rightIndex);
        }
    }

    private static int findPivot(int[] arr, int leftIndex, int rightIndex) {
        int j = leftIndex - 1;
        for (int i = leftIndex; i < rightIndex; i++) {
            if (arr[i] < arr[rightIndex]) {
                j++;
                swapElements(arr, j, i);
            }
        }
        swapElements(arr, j + 1, rightIndex);
        return j + 1;
    }

    private static void swapElements(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int temp = arr[leftIndex];
            arr[leftIndex] = arr[rightIndex];
            arr[rightIndex] = temp;
        }
    }

    // first element is considered to be sorted
    // start with next element and compare it with previous elements and insert where appropriate
    // no swap, only shifts
    // takes O(n²)
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int valueToInsert = arr[i];
            int tempIndex = i;
            for (int j = i - 1; j >= 0 && valueToInsert < arr[j]; j--) {
                arr[tempIndex] = arr[j];
                tempIndex = j;
            }
            if (tempIndex != i) {
                arr[tempIndex] = valueToInsert;
            }
            //int j = i - 1;
            //while (j >= 0 && valueToInsert < arr[j]) {
            //    arr[j + 1] = arr[j];
            //    j--;
            //}
            //if (i != j + 1) {
            //    arr[j + 1] = valueToInsert;
            //}
        }
    }

    // takes O(n²)
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if (i < minIndex) {
                int tempValue = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = tempValue;
            }
        }
    }

    // takes O(n²)
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tempValue = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tempValue;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

}
