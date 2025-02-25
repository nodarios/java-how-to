package com.cmp.javahowto.jat;

import java.util.*;

public class InterviewTask {

    public static void main(String[] args) {
        //int[] transactions = new int[]{1, 1, 3, 1, 5};
        //System.out.println(findIndexWhereElementsSumBeforeAndAfterAreEqual(transactions));

        //System.out.println(removeConsecutiveDuplicates("abccccda"));

        //int[] arr = new int[]{1, 2, 3, 4, 5};
        //reverseArray(arr);
        //System.out.println(Arrays.toString(arr));

        // Given an array of integers, write a function to return the maximum sum of contiguous sub arrays.
        // Contiguous sub arrays are: "-2", "-2, 1", "-2, 1, -3" and so on.
        int[] inputArray = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        OptionalInt maxSum = findMaxSubArraySum(inputArray);
        if (maxSum.isPresent()) {
            System.out.println("Maximum sum of a contiguous sub arrays is " + maxSum.getAsInt());
        }
    }

    private static OptionalInt findMaxSubArraySum(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            return OptionalInt.empty();
        }
        List<Integer> sumList = new ArrayList<>();
        sumList.add(inputArray[0]);
        for (int i = 1; i < inputArray.length; i++) {
            sumList.add(sumList.get(i - 1) + inputArray[i]);
        }
        return sumList.stream().mapToInt(Integer::intValue).max();
    }

    private static void reverseArray(int[] arr) {
        int startIndex = 0;
        int endIndex = arr.length - 1;
        while (startIndex < endIndex) {
            int temp = arr[endIndex];
            arr[endIndex] = arr[startIndex];
            arr[startIndex] = temp;
            startIndex++;
            endIndex--;
        }

    }

    private static String removeConsecutiveDuplicates(String s) {
        var chars = s.toCharArray();
        char currentChar;
        char previousChar = 0;
        var sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            currentChar = chars[i];
            if (i == 0) {
                sb.append(currentChar);
                previousChar = currentChar;
                continue;
            }
            if (currentChar != previousChar) {
                sb.append(currentChar);
            }
            previousChar = currentChar;
        }
        return sb.toString();
    }

    public static int findIndexWhereElementsSumBeforeAndAfterAreEqual(int[] transactions) {

        for (int i = 0; i < transactions.length; i++) {
            if (i > 0 && i < transactions.length - 1) {
                int[] beforeArray = Arrays.copyOfRange(transactions, 0, i);
                int[] afterArray = Arrays.copyOfRange(transactions, i + 1, transactions.length);
                var beforeSum = Arrays.stream(beforeArray).sum();
                var afterSum = Arrays.stream(afterArray).sum();
                if (beforeSum == afterSum) {
                    return i;
                }
            }
        }

        return -1;
    }

}
