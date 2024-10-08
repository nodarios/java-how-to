package com.cmp.javahowto.jat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TestSolutions {

    public static void main(String[] args) {
        //System.out.println(areWordsAnagram("andro", "nodar"));
        //System.out.println(calculateFactorial(5));
        //System.out.println(checkIfStringIsPalindrome("rar"));
        //printFibonacci(0, 1, 20);
        //System.out.println(findMissingNumber(new int[]{1, 2, 5, 3}));
        //System.out.println(checkIfNumberIsMagic(163));
        //findUsingBinarySearch(0, 9, 70, List.of(10, 20, 30, 40, 50, 60, 70, 80, 90, 100), 0);

        towerOfHanoi(3, 'S', 'A', 'D');
    }

    public static void towerOfHanoi(int n, char source, char auxiliary, char destination) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + destination);
            return;
        }

        // Move (n-1) disks from source to auxiliary peg using destination peg as the temporary peg
        towerOfHanoi(n - 1, source, destination, auxiliary);

        // Move the nth disk from source peg to destination peg
        System.out.println("Move disk " + n + " from " + source + " to " + destination);

        // Move the (n-1) disks from auxiliary peg to destination peg using source peg as the temporary peg
        towerOfHanoi(n - 1, auxiliary, source, destination);
    }

    private static void findUsingBinarySearch(int left, int right, int target, List<Integer> listToSearch, int counter) {
        counter++;
        int mid = (right + left) / 2;
        System.out.println("hit number is: " + listToSearch.get(mid));

        if (listToSearch.get(mid).equals(target)) {
            System.out.println("found at try: " + counter);
        } else {
            if (listToSearch.get(mid) > target) {
                right = mid - 1;
                findUsingBinarySearch(left, right, target, listToSearch, counter);
            } else {
                left = mid + 1;
                findUsingBinarySearch(left, right, target, listToSearch, counter);
            }
        }
    }

    private static boolean checkIfNumberIsMagic(int number) {
        // Step 1: 163 => 1+6+3 = 10
        // Step 2: 10 => 1+0 = 1 => Hence 163 is a magic number
        int firstSum = getNumberSum(number);
        int secondSum = getNumberSum(firstSum);
        return secondSum == 1;
    }

    private static int getNumberSum(int number) {
        List<String> list = Arrays.asList(String.valueOf(number).split(""));
        int sum = 0;
        for (String e : list) {
            sum += Integer.valueOf(e);
        }
        return sum;
    }

    private static Optional<Integer> findMissingNumber(int[] array) {
        Optional<Integer> missingNumber = Optional.empty();
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            if (array[i] != i + 1) {
                missingNumber = Optional.of(i + 1);
                break;
            }
        }
        return missingNumber;
    }

    private static void printFibonacci(int first, int second, int limit) {
        limit--;
        // 0 1 1 2 3 5 8 13 21 34 ...
        if (limit == 0) {
            return;
        }
        System.out.println(first + second);
        printFibonacci(second, first + second, limit);
    }

    private static boolean checkIfStringIsPalindrome(String word) {
        String reversedWord = reverseWord(word);
        return word.equals(reversedWord);
    }

    private static String reverseWord(String word) {
        return (word.length() == 1)
                ? word
                : word.charAt(word.length() - 1) + reverseWord(word.substring(0, word.length() - 1));
    }

    private static int calculateFactorial(int num) {
        return (num == 1)
                ? num
                : num * calculateFactorial(num - 1);
    }

    private static boolean areWordsAnagram(String word1, String word2) {
        var word1List = convertStringToList(word1);
        var word2List = convertStringToList(word2);
        Collections.sort(word1List);
        Collections.sort(word2List);
        return word1List.equals(word2List);
    }

    private static List<String> convertStringToList(String word) {
        return Arrays.asList(word.split(""));
    }

}
