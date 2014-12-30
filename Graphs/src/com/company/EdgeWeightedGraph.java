package com.company;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.introcs.In;

/**
 * Created by artemlobachev on 29.12.14.
 */
public class EdgeWeightedGraph {
    private final int vCount;
    private int eCount;
    private Bag<Edge>[] graphStructure;

    @SuppressWarnings("unchecked")
    public EdgeWeightedGraph(int vertexesCount) {
        this.vCount = vertexesCount;
        this.eCount = 0;
        this.graphStructure = (Bag<Edge>[]) new Bag[vertexesCount];
        for (int v = 0; v < vertexesCount; v++) {
            this.graphStructure[v] = new Bag<>();
        }
    }

    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int edgesCount = in.readInt();
        for (int i = 0; i < edgesCount; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            Edge e = new Edge(v, w, weight);
            this.addEdge(e);
        }
    }

    public int vertexesCount() {
        return this.vCount;
    }

    public int edgesCount() {
        return this.eCount;
    }

    public void addEdge(Edge e) {
        int vertex1 = e.either();
        int vertex2 = e.other(vertex1);
        if (vertex1 < this.vCount && vertex2 < this.vCount) {
            this.graphStructure[vertex1].add(e);
            this.graphStructure[vertex2].add(e);
            this.eCount++;
        }
    }

    public Iterable<Edge> adj(int vertexNumber) {
        return vertexNumber < this.vCount ? this.graphStructure[vertexNumber] : null;
    }

    public Iterable<Edge> edges() {
        Bag<Edge> result = new Bag<>();
        for (int v = 0; v < this.vCount; v++) {
            int selfLoops = 0;
            for (Edge e : this.adj(v)) {
                if (e.other(v) > v) {
                    result.add(e);
                }
                // only add one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) result.add(e);
                    selfLoops++;
                }
            }
        }
        return result;
    }

    public String toString() {
        String lineSeparator = System.lineSeparator();
        StringBuilder s = new StringBuilder();
        s.append(this.vCount).append(" ").append(this.eCount).append(lineSeparator);
        for (int v = 0; v < this.vCount; v++) {
            s.append(v).append(": ");
            for (Edge e : this.graphStructure[v]) {
                s.append(e).append("  ");
            }
            s.append(lineSeparator);
        }
        s.setLength(s.length() - 1);
        return s.toString();
    }

}
