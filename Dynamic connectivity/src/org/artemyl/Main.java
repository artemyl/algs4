package org.artemyl;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void testWithClass(Class myUFClass, String pathToTestFile){
        In input = new In(pathToTestFile);
        int N = input.readInt();
        StdOut.printf("Input created, N = %d%n", N);
        UnionFind uf;

        try {
            Constructor myUFConstructor = myUFClass.getConstructor(int.class);
            Object objectByClassName = myUFConstructor.newInstance(N);
            if (objectByClassName instanceof UnionFind){
                uf = (UnionFind) objectByClassName;
                String className = uf.getClass().getCanonicalName();
                StdOut.printf("UF class \"%s\" prepared%n", className);
                long startTime = System.currentTimeMillis();
                while (!input.isEmpty()) {
                    int p = input.readInt();
                    int q = input.readInt();
                    if (uf.connected(p, q)) continue;
                    uf.union(p, q);
                }
                long time = System.currentTimeMillis() - startTime;
                StdOut.println(uf.count() + " components");
                StdOut.printf("Test time for class \"%s\" = %d%n", className, time);
            } else {
                StdOut.println("Error: given class is not instance of UnionFind class!");
            }
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            StdOut.println("Something wrong with testing class");
        }

    }

    public static void main(String[] args) {
        String pathToTestFile = "/Users/artemlobachev/development/repos/algs4/Dynamic connectivity/out/production/Dynamic connectivity/org/artemyl/largeUF.txt";
        testWithClass(WeightedQuickUnionFind.class, pathToTestFile );
        testWithClass(CompressedWeightedQuickUnionFind.class, pathToTestFile);
        testWithClass(QuickFind.class, pathToTestFile);
    }
}
