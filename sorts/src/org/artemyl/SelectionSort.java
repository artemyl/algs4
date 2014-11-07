package org.artemyl;

/**
 * Created by artemlobachev on 07.11.14.
 */
public class SelectionSort extends AbstractSort {
    @Override
    public void sort(Comparable array[]) {
        for (int i = 0; i < array.length; i++){
            int minNumber = i;
            for (int j = i; j < array.length; j++){
                if (this.less(array[j],array[minNumber])){
                    minNumber = j;
                }
            }
            this.swap(array, minNumber, i);
        }
    }
}
