package com.company;

/**
 * Created by artemlobachev on 29.12.14.
 */
public class Edge implements Comparable<Edge> {

    private final int firstVertex;
    private final int secondVertex;
    private final double weight;

    public Edge(int firstVertex, int secondVertex, double weight) {
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
        this.weight = weight;
    }

    public double weight() {
        return this.weight;
    }

    public int either() {
        return this.firstVertex;
    }

    public int other(int vertex) {
        if      (vertex == this.firstVertex) return this.secondVertex;
        else if (vertex == this.secondVertex) return this.firstVertex;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    public int compareTo(Edge that) {
        if      (this.weight() < that.weight()) return -1;
        else if (this.weight() > that.weight()) return +1;
        else                                    return  0;
    }

    public String toString() {
        return String.format("%d-%d %.5f", this.firstVertex, this.secondVertex, this.weight);
    }

}
