package com.company;

/**
 * Created by artemlobachev on 30.12.14.
 */
public class DirectedEdge {
    private final int startVertex;
    private final int endVertex;
    private final double weight;

    public DirectedEdge(int startVertex, int endVertex, double weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    public int from() {
        return this.startVertex;
    }

    public int to() {
        return this.endVertex;
    }

    public double weight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return this.startVertex + "->" + this.endVertex + " " + String.format("%5.2f", this.weight);
    }

}
