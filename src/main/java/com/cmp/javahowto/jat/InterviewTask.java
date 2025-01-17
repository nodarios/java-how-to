package com.cmp.javahowto.jat;

import java.util.Arrays;

public class InterviewTask {

    public static void main(String[] args) {

        //int[] transactions = new int[]{1, 1, 3, 1, 5};
        //System.out.println(findIndexWhereElementsSumBeforeAndAfterAreEqual(transactions));

        //System.out.println(removeConsecutiveDuplicates("abccccda"));

        int[] arr = new int[]{1, 2, 3, 4, 5};
        reverseArray(arr);
        System.out.println(Arrays.toString(arr));

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
