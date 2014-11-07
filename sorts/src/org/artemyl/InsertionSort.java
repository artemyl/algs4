package org.artemyl;

/**
 * Created by artemlobachev on 07.11.14.
 */
public class InsertionSort extends AbstractSort {
    @Override
    public void sort(Comparable array[]) {
        for (int i = 1; i < array.length; i++){
            for (int j = i; j > 0 && this.less(array[j], array[j - 1]); j--) {
                this.swap(array, j, j - 1);
            }
        }
    }
}
