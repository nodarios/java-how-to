package com.cmp.javahowto.jat;

public class InterviewTask2 {

    public static void main(String[] args) {
        System.out.println(removeConsecutiveDuplicates("abccccda"));
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

}
