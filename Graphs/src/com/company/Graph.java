package com.company;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.introcs.In;

/**
 * Created by artemlobachev on 24.12.14.
 */
public class Graph {

    protected final int vCount;
    protected int eCount;
    protected Bag<Integer> graphStructure[];


    @SuppressWarnings("unchecked")
    public Graph(int vertexesCount){
        this.vCount = vertexesCount;
        this.eCount = 0;
        this.graphStructure = (Bag<Integer>[]) new Bag[this.vCount];
        for(int i = 0; i < vertexesCount; i++){
            this.graphStructure[i] = new Bag<>();
        }
    }

    public Graph(In in) {
        this(in.readInt());
        int edgesCount = in.readInt();
        for (int i = 0; i < edgesCount; i++) {
            int v = in.readInt();
            int w = in.readInt();
            this.addEdge(v, w);
        }
    }

    public int vertexesCount(){
        return this.vCount;
    }

    public int edgesCount(){
        return this.eCount;
    }

    public void addEdge(int vertex1, int vertex2){
        if (vertex1 < this.vCount && vertex2 < this.vCount){
            this.graphStructure[vertex1].add(vertex2);
            this.graphStructure[vertex2].add(vertex1);
            this.eCount++;
        }
    }

    public Iterable<Integer> vertexEdges(int vertexNumber){
        return vertexNumber < this.vCount ? this.graphStructure[vertexNumber] : null;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        final String lineSeparator = System.lineSeparator();
        s.append(this.vCount).append(" vertices, ").append(this.eCount).append(" edges ").append(lineSeparator);
        for (int v = 0; v < this.vCount; v++) {
            s.append(v).append(": ");
            for (int w : graphStructure[v]) {
                s.append(w).append(" ");
            }
            s.append(lineSeparator);
        }
        s.setLength(s.length() - 1);
        return s.toString();
    }
}
