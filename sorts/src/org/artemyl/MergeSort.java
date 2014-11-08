package org.artemyl;

/**
 * Created by artemlobachev on 08.11.14.
 */
public class MergeSort extends AbstractSort {

    private Comparable tmpArray[];

    private void recursiveSort(Comparable array[], int min, int max){
        if (max <= min) return;
        int mid = min + (max - min) / 2;
        this.recursiveSort(array , min, mid);
        this.recursiveSort(array, mid + 1, max);
        this.merge(array, min, mid, max);
    }

    private void merge(Comparable array[], int min, int mid, int max){
        /*
        for (int i = min; i <= max; i++){
            this.tmpArray[i] = array[i];
        }
         */
        System.arraycopy(array, min, this.tmpArray, min, max + 1 - min); // experimentally, sorting with this works ~ 15% faster
        int i = min, j = mid + 1;
        for (int k = min; k <= max; k++){
            if (i > mid){
                array[k] = this.tmpArray[j++];
            } else if (j > max ) {
                array[k] = this.tmpArray[i++];
            } else if (this.less(this.tmpArray[i], this.tmpArray[j])) {
                array[k] = this.tmpArray[i++];
            } else {
                array[k] = this.tmpArray[j++];
            }
        }
    }

    @Override
    public void sort(Comparable array[]) {
        this.tmpArray = new Comparable[array.length];
        this.recursiveSort(array, 0, array.length - 1);
    }
}
