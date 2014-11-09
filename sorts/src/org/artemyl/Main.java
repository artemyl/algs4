package org.artemyl;

import java.util.Random;

public class Main {

    private static Integer[] generateArray(int count){
        Random random = new Random();
        Integer result[] = new Integer[count];
        for (int i = 0; i < count; i++){
            result[i] = random.nextInt();
        }
        return result;
    }

    private static long experimentWithClass(Class<? extends AbstractSort> sortClass, Comparable<?> array[]){
        try {
            AbstractSort sort = sortClass.newInstance();
            Comparable<?> arrayForExperiment[] = array.clone();
            long startTime = System.currentTimeMillis();
            sort.sort(arrayForExperiment);
            long time = System.currentTimeMillis();
            if (!sort.isSorted(arrayForExperiment)){
                System.out.println("Incorrect sort result for class " + sort.getClass().getCanonicalName());
            }
            return time - startTime;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @SuppressWarnings("unused")
    private static void debugSort(Class<? extends AbstractSort> sortClass){
        System.out.println("Testing class = " + sortClass.getCanonicalName());
        Integer experimentData[] = generateArray(100);
        System.out.println("Before:");
        AbstractSort sort;
        try {
            sort = sortClass.newInstance();
            sort.print(experimentData);
            sort.sort(experimentData);
            System.out.println("After:");
            sort.print(experimentData);
            if (sort.isSorted(experimentData)){
                System.out.println("Experiment data is sorted!");
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Integer experimentData[] = generateArray(10000000);
        System.out.printf("Experiment with Quick sort, result = %d%n", experimentWithClass(QuickSort.class, experimentData));
        System.out.printf("Experiment with Merge sort, result = %d%n", experimentWithClass(MergeSort.class, experimentData));
        System.out.printf("Experiment with Heap sort, result = %d%n", experimentWithClass(HeapSort.class, experimentData));
        System.out.printf("Experiment with Shell sort, result = %d%n", experimentWithClass(ShellSort.class, experimentData));
        // interesting to compare, but too much time to wait
        //System.out.printf("Experiment with Selection sort, result = %d%n", experimentWithClass(SelectionSort.class, experimentData));
        //System.out.printf("Experiment with Insertion sort, result = %d%n", experimentWithClass(InsertionSort.class, experimentData));
        System.out.println("All tests complete!");
    }
}
