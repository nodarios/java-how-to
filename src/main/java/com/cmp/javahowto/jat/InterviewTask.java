package com.cmp.javahowto.jat;

import java.util.Arrays;

public class InterviewTask {

    public static void main(String[] args) {

        int[] transactions = new int[]{1, 1, 3, 1, 5};
        System.out.println(findIndexWhereElementsSumBeforeAndAfterAreEqual(transactions));

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
