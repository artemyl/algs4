package org.artemyl;

/**
 * Created by artemlobachev on 09.11.14.
 */
public class HeapSort extends AbstractSort {

    private void sink(Comparable array[], int elementNumber, int arraySize){
        while (2 * elementNumber + 1 < arraySize){
            int childNumber = elementNumber * 2 + 1;
            if ((childNumber + 1 < arraySize) && (this.less(array[childNumber], array[childNumber + 1]))) childNumber++;
            if (this.less(array[elementNumber], array[childNumber])) {
                this.swap(array, childNumber, elementNumber);
                elementNumber = childNumber;
            } else {
                break;
            }
        }
    }

    @Override
    public void sort(Comparable array[]) {
        int arrayLength = array.length;
        for (int i = arrayLength / 2; i >= 0; i--){
            this.sink(array, i, arrayLength);
        }
        while (arrayLength > 1){
            this.swap(array, 0, --arrayLength);
            this.sink(array, 0, arrayLength);
        }
    }
}
