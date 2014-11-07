package org.artemyl;

/**
 * Created by artemlobachev on 07.11.14.
 */
public class ShellSort extends AbstractSort {

    // Knuth, O(N^(3/2))
    private int countD(int arrayLength){
        int d = 1, i = 1;
        while (d < arrayLength / 3){
            d = 3 * i + 1;
        }
        return d;
    }

    @Override
    public void sort(Comparable array[]) {
        int d = this.countD(array.length);
        while(d >= 1) {
            for (int i = 1; i < array.length; i++) {
                for (int j = i; j >= d && this.less(array[j], array[j - d]); j -= d) {
                    this.swap(array, j, j - d);
                }
            }
            d /= 3;
        }
    }
}
