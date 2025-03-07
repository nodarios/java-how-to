package com.cmp.javahowto.dsa.algorithm;

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

        //int[] arr = new int[]{5, 2, 6, 1, 4, 3};
        //quickSort(arr, 0, arr.length - 1);
        //System.out.println(Arrays.toString(arr));

        int[] arr = new int[]{4, 2, 1, 6, 5, 3};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    // takes O(n log n)
    public static void mergeSort(int[] arr, int left, int right) {
        // 4, 2, 1, 6, 5, 3
        // 4, 2, 1 | 6, 5, 3
        // 4, 2 | 1 | 6, 5 | 3
        // 4 | 2 | 1 | 6 | 5 | 3
        // 2, 4 | 1 | 5, 6 | 3
        // 1, 2, 4 | 3, 5, 6
        // 1, 2, 3, 4, 5, 6
        if (left < right) {
            //int mid = (left + right)/2;
            int mid = left + (right - left) / 2;

            // Recursively divide the array into left and right halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int leftSize = mid + 1 - left;  // Size of left half
        int rightSize = right - mid;      // Size of right half

        // Temporary arrays
        int[] leftArr = new int[leftSize];
        int[] rightArr = new int[rightSize];

        // Copy data to temp arrays
        for (int i = 0; i < leftSize; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int i = 0; i < rightSize; i++) {
            rightArr[i] = arr[mid + 1 + i];
        }

        // Merge the temp arrays back into arr[] in sorted way
        int i = 0, j = 0, k  = left;

        while (i < leftSize && j < rightSize) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements (if any)
        while (i < leftSize) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
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
