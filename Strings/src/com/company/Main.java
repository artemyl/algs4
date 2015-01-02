package com.company;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Main {

    private static void testStringsSort(){
        StdOut.println("Input strings count(all strings must have same length):");
        final int stringsCount = StdIn.readInt();
        String testStringArray[] = new String[stringsCount];
        StdOut.println("Input strings:");
        for (int i = 0; i < stringsCount; i++){
            testStringArray[i] = StdIn.readString();
        }
        StdOut.println("Raw data:");
        for (String str : testStringArray){
            StdOut.println(str);
        }
        LSDStringSort.sort(testStringArray, testStringArray[0].length());
        StdOut.println("Sorted data:");
        for (String str : testStringArray){
            StdOut.println(str);
        }
    }

    private static void testSubstringSearch(){
        StdOut.println("Testing substring search. Input pattern(space = end line):");
        String pattern = StdIn.readString();
        StdOut.println("Input text(space = end line):");
        String text = StdIn.readString();
        KMP kmp = new KMP(pattern);
        BoyerMoore boyerMoore = new BoyerMoore(pattern);
        StdOut.println("Text length = " + text.length() + ". KMP result = " + kmp.search(text)
                + ". Boyer-Moore result = " + boyerMoore.search(text) + ".");
    }

    public static void main(String[] args) {
        if (args.length == 1){
            if ("c".equals(args[0])){
                Huffman.compress();
            } else if ("e".equals(args[0])){
                Huffman.expand();
            }
            return;
        }
        testStringsSort();
        testSubstringSearch();
    }
}
