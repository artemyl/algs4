package com.company;

/**
 * Created by artemlobachev on 02.01.15.
 */
public class BoyerMoore {
    private int[] right;
    private String pattern;

    public BoyerMoore(String pattern) {
        int alphabetCapacity = 256;
        this.pattern = pattern;
        this.right = new int[alphabetCapacity];
        for (int c : this.right) {
            this.right[c] = -1;
        }
        for (int j = 0; j < pattern.length(); j++) {
            this.right[pattern.charAt(j)] = j;
        }
    }

    /**
     * @param text text to find pattern in
     * @return first symbol number if pattern found or text length if pattern not found
     */
    public int search(String text) {
        int patternLength = this.pattern.length();
        int textLength = text.length();
        int skip;
        for (int i = 0; i <= textLength - patternLength; i += skip) {
            skip = 0;
            for (int j = patternLength-1; j >= 0; j--) {
                if (this.pattern.charAt(j) != text.charAt(i+j)) {
                    skip = Math.max(1, j - this.right[text.charAt(i+j)]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return textLength;              // not found
    }
}
