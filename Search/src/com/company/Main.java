package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

    private static Integer[] generateСonsecutiveArray(int count){
        Integer result[] = new Integer[count];
        for (int i = 0; i < count; i++){
            result[i] = i;
        }
        return result;
    }

    @SuppressWarnings({"unused", "unchecked"})
    private static void experimentWithClass(Class<? extends AbstractDictionary> dictClass, Comparable<?> array[]){
        try {
            AbstractDictionary dict = dictClass.newInstance();
            Comparable<?> arrayForExperiment[] = array.clone();
            long startTime = System.currentTimeMillis();
            for (Comparable<?> element:arrayForExperiment){
                dict.put(element, element.toString());
            }
            long time = System.currentTimeMillis();
            System.out.printf("Part 1 - put for class \"%s\" = %d%n", dictClass.getCanonicalName(), time - startTime);
            List<Comparable<?>> tmpList = Arrays.asList(arrayForExperiment);
            Collections.shuffle(tmpList);
            arrayForExperiment = (Comparable<?>[])tmpList.toArray();
            startTime = System.currentTimeMillis();
            for (Comparable<?> element:arrayForExperiment){
                Object str = dict.get(element);
            }
            time = System.currentTimeMillis();
            System.out.printf("Part 2 - get for class \"%s\" = %d%n", dictClass.getCanonicalName(), time - startTime);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int elementsCount = 200000;
        System.out.println("Experiment with random array:");
        Integer experimentData[] = generateArray(elementsCount);
        experimentWithClass(BinarySearchTreeDictionary.class, experimentData);
        experimentWithClass(RedBlackBST.class, experimentData);
        System.out.println("Experiment with consecutive array:");
        experimentData = generateСonsecutiveArray(elementsCount);
        experimentWithClass(BinarySearchTreeDictionary.class, experimentData);
        experimentWithClass(RedBlackBST.class, experimentData);
    }
}
