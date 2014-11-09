package org.artemyl;

/**
 * Created by artemlobachev on 31.10.14.
 */
public abstract class AbstractSort {

    public abstract void sort(Comparable array[]);

    @SuppressWarnings("unchecked") // assuming a and b are the objects of the same classes
    public boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    public boolean isSorted(Comparable array[]){
        for (int i = 0; i < array.length - 1; i++){
            if (this.less(array[i + 1], array[i])){
                System.err.printf("Error in element №%d in class %s%n", i, this.getClass().getCanonicalName());
                return false;
            }
        }
        return true;
    }

    public void swap(Comparable array[], int firstNumber, int secondNumber){
        if (firstNumber == secondNumber) return;
        Comparable tmp = array[firstNumber];
        array[firstNumber] = array[secondNumber];
        array[secondNumber] = tmp;
    }

    public void print(Comparable array[]){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
