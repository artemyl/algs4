package com.company;

/**
 * Created by artemlobachev on 01.01.15.
 */
public class KMP {
    private int[][] dfa;
    private String pattern;

    public KMP(String pattern) {
        int alphabetCapacity = 256;
        this.pattern = pattern;
        int patternLength = pattern.length();
        this.dfa = new int[alphabetCapacity][patternLength];
        this.dfa[pattern.charAt(0)][0] = 1;
        for (int mismatchReturnStatement = 0, symbolInPattern = 1; symbolInPattern < patternLength; symbolInPattern++) {
            for (int strNumber = 0; strNumber < alphabetCapacity; strNumber++) {
                this.dfa[strNumber][symbolInPattern] = this.dfa[strNumber][mismatchReturnStatement];      // Copy mismatch cases.
            }
            this.dfa[pattern.charAt(symbolInPattern)][symbolInPattern] = symbolInPattern+1;               // Set match case.
            mismatchReturnStatement = this.dfa[pattern.charAt(symbolInPattern)][mismatchReturnStatement]; // Update restart state.
        }
    }

    /**
     * @param text text to find pattern in
     * @return first symbol number if pattern found or text length if pattern not found
     */
    public int search(String text) {
        // simulate operation of DFA on text
        int patternLength = this.pattern.length();
        int textLength = text.length();
        int i, j;
        for (i = 0, j = 0; i < textLength && j < patternLength; i++) {
            j = this.dfa[text.charAt(i)][j];
        }
        if (j == patternLength) return i - patternLength;
        return textLength;
    }
}
