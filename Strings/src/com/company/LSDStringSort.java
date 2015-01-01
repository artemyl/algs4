package com.company;

/**
 * Created by artemlobachev on 01.01.15.
 */
public class LSDStringSort {

    public static void sort(String[] strings, int stringLength) {
        int stringsCount = strings.length;
        int R = 256;   //alphabet size
        String[] aux = new String[stringsCount];

        for (int characterNumber = stringLength-1; characterNumber >= 0; characterNumber--) {

            // compute frequency counts
            int[] count = new int[R+1];
            for (String str : strings)
                count[str.charAt(characterNumber) + 1]++;

            // compute indexes
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            // move data
            for (String str : strings)
                aux[count[str.charAt(characterNumber)]++] = str;

            // copy back
            System.arraycopy(aux, 0, strings, 0, stringsCount);
        }
    }
}
