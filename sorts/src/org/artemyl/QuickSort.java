package org.artemyl;

/**
 * Created by artemlobachev on 09.11.14.
 */
public class QuickSort extends AbstractSort {

    protected int partition(Comparable array[], int min, int max){
        int i = min, j = max + 1;
        Comparable referenceElement = array[min];
        do {
            while (this.less(array[++i], referenceElement) && (i != max));
            while (this.less(referenceElement, array[--j]) && (j != min));
            if (i < j) this.swap(array, i, j);
        } while (i < j);
        this.swap(array, min, j);
        return j;
    }

    protected void recursiveSort(Comparable array[], int min, int max){
        if (max <= min) return;
        int mid = this.partition(array, min, max);
        this.recursiveSort(array, min, mid - 1);
        this.recursiveSort(array, mid + 1, max);
    }

    @Override
    public void sort(Comparable[] array) {
        this.recursiveSort(array, 0, array.length - 1);
    }
}
