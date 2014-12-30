package com.company;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.introcs.In;

/**
 * Created by artemlobachev on 30.12.14.
 */
public class EdgeWeightedDigraph {
    private final int vCount;
    private int eCount;
    private Bag<DirectedEdge>[] graphStructure;

    @SuppressWarnings("unchecked")
    public EdgeWeightedDigraph(int vertexesCount) {
        this.vCount = vertexesCount;
        this.eCount = 0;
        this.graphStructure = (Bag<DirectedEdge>[]) new Bag[vertexesCount];
        for (int v = 0; v < vertexesCount; v++)
            this.graphStructure[v] = new Bag<>();
    }

    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        int edgesCount = in.readInt();
        for (int i = 0; i < edgesCount; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            this.addEdge(new DirectedEdge(v, w, weight));
        }
    }

    public int vertexesCount() {
        return this.vCount;
    }

    public int edgesCount() {
        return this.eCount;
    }

    public void addEdge(DirectedEdge e) {
        int v = e.from();
        this.graphStructure[v].add(e);
        this.eCount++;
    }

    public Iterable<DirectedEdge> adj(int vertexNumber) {
        return vertexNumber < this.vCount ? this.graphStructure[vertexNumber] : null;
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> list = new Bag<>();
        for (int v = 0; v < this.vCount; v++) {
            for (DirectedEdge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    }

    @Override
    public String toString() {
        String NEWLINE = System.lineSeparator();
        StringBuilder s = new StringBuilder();
        s.append(this.vCount).append(" ").append(this.eCount).append(NEWLINE);
        for (int v = 0; v < this.vCount; v++) {
            s.append(v).append(": ");
            for (DirectedEdge e : this.graphStructure[v]) {
                s.append(e).append("  ");
            }
            s.append(NEWLINE);
        }
        s.setLength(s.length() - 1);
        return s.toString();
    }

}
