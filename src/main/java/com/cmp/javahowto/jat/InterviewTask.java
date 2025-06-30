package com.cmp.javahowto.jat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class InterviewTask {

    public static void main(String[] args) {
        //int[] inputArray = new int[]{1, 1, 3, 1, 5};
        //System.out.println(findIndexWhereElementsSumBeforeAndAfterAreEqual(inputArray));

        //System.out.println(removeConsecutiveDuplicates("abccccda"));

        //int[] arr = new int[]{1, 2, 3, 4, 5};
        //reverseArray(arr);
        //System.out.println(Arrays.toString(arr));

        // Given an array of integers, write a function to return the maximum sum of contiguous sub arrays.
        // Contiguous sub arrays are: "-2", "-2, 1", "-2, 1, -3" and so on.
        //int[] inputArray = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        //OptionalInt maxSum = findMaxSubArraySum(inputArray);
        //if (maxSum.isPresent()) {
        //    System.out.println("Maximum sum of a contiguous sub arrays is " + maxSum.getAsInt());
        //}

        // Assume you have an array [1,2,3,4,5], you need to return subArrays
        // e.g. [1], [1,2], [1,2,3], [1,2,3,4], [1,2,3,4,5], [2], [2,3], [2,3,4], [2,3,4,5], [3]...
        //int[] inputArray = new int[]{1, 2, 3, 4, 5};
        //List<List<Integer>> subArrays = getSubArrays(inputArray);
        //for (List<Integer> subArray : subArrays) {
        //    System.out.println(subArray);
        //}

        // Find the index of the smallest element in rotated sorted array
        // Sorted: [1, 2, 4, 7, 9, 12, 15, 21, 27]
        // Rotated at index 2: [21, 27, 1, 2, 4, 7, 9, 12, 15]
        // Smallest Element: 1 (Index: 2)
        // In rotated sorted array, the smallest element is the only one where the previous element is greater
        //int[] inputArray = new int[]{9, 12, 14, 18, -8, -6, -2, 1, 3, 6, 8};
        //System.out.println(findSmallestElementIndexInRotatedSortedArray(inputArray));

        streamGrouping();
    }

    // {} -- Valid expression
    // ({}[]) -- Valid
    // {[}] -- Invalid
    // ()[]{} -- Valid
    // {[()]} -- Valid
    // {[(])} -- Invalid

    private static void streamGrouping() {
        record Person(int id, String name, int age, String city) {
        }
        List<Person> list = new ArrayList<>();
        list.add(new Person(1, "name1", 15, "london"));
        list.add(new Person(2, "name2", 16, "tbilisi"));
        list.add(new Person(3, "name3", 20, "tbilisi"));
        list.add(new Person(4, "name4", 21, "tbilisi"));

        Map<String, Map<String, List<Person>>> result = list.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::city,
                                Collectors.groupingBy(p -> p.age > 18 ? "adult" : "teen")
                        )
                );
        System.out.println(result);
    }

    private static int findSmallestElementIndexInRotatedSortedArray(int[] inputArray) {
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i - 1] > inputArray[i]) {
                return i;
            }
        }
        return 0;
    }

    private static List<List<Integer>> getSubArrays(int[] inputArray) {
        List<List<Integer>> subArrays = new ArrayList<>();
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = i; j < inputArray.length; j++) {
                List<Integer> subArray = new ArrayList<>();
                for (int k = i; k <= j; k++) {
                    subArray.add(inputArray[k]);
                }
                subArrays.add(subArray);
            }
        }
        return subArrays;
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
        String[] arr = s.split("");
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (!arr[i].equals(arr[i - 1])) {
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }

    public static int findIndexWhereElementsSumBeforeAndAfterAreEqual(int[] inputArray) {
        for (int i = 1; i < inputArray.length - 1; i++) {
            int[] beforeArray = Arrays.copyOfRange(inputArray, 0, i);
            int[] afterArray = Arrays.copyOfRange(inputArray, i + 1, inputArray.length);
            var beforeSum = Arrays.stream(beforeArray).sum();
            var afterSum = Arrays.stream(afterArray).sum();
            if (beforeSum == afterSum) {
                return i;
            }
        }
        return -1;
    }

}
