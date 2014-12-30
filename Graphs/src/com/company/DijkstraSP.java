package com.company;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.introcs.StdOut;

import java.util.Stack;

/**
 * Created by artemlobachev on 30.12.14.
 */
public class DijkstraSP implements PathChecker{
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> pq;
    private final int start;

    public DijkstraSP(EdgeWeightedDigraph graph, int s) {
        this.start = s;
        this.distTo = new double[graph.vertexesCount()];
        this.edgeTo = new DirectedEdge[graph.vertexesCount()];
        for (int v = 0; v < graph.vertexesCount(); v++)
            this.distTo[v] = Double.POSITIVE_INFINITY;
        this.distTo[s] = 0.0;

        // relax vertices in order of distance from s
        this.pq = new IndexMinPQ<>(graph.vertexesCount());
        this.pq.insert(s, this.distTo[s]);
        while (!this.pq.isEmpty()) {
            int v = this.pq.delMin();
            for (DirectedEdge e : graph.adj(v))
                this.relax(e);
        }
    }

    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (this.distTo[w] > this.distTo[v] + e.weight()) {
            this.distTo[w] = this.distTo[v] + e.weight();
            this.edgeTo[w] = e;
            if (this.pq.contains(w)){
                this.pq.decreaseKey(w, this.distTo[w]);
            }
            else {
                this.pq.insert(w, this.distTo[w]);
            }
        }
    }

    public double distTo(int v) {
        return this.distTo[v];
    }

    @Override
    public boolean hasPathTo(int v) {
        return this.distTo[v] < Double.POSITIVE_INFINITY;
    }

    @Override
    public int reachableVertexesCount() {
        int result = -1;
        for (double t : this.distTo){
            if (t < Double.POSITIVE_INFINITY){
                result++;
            }
        }
        return result;
    }

    @Override
    public void printResult() {
        StdOut.println(this.toString());
    }

    @Override
    public String toString() {
        String lineSeparator = System.lineSeparator();
        StringBuilder builder = new StringBuilder("Dijkstra algorithm result for vertex ").append(this.start).append(lineSeparator);
        for (int i = 0; i < this.edgeTo.length; i++){
            if (this.edgeTo[i] == null){
                builder.append("Vertex ").append(i).append(" is unreachable").append(lineSeparator);
            }
            else {
                builder.append("Vertex ").append(i).append(" is reachable. Path weight = ")
                        .append(this.distTo[i]).append(lineSeparator).append("Path is:").append(lineSeparator).append(i).append(" <- ");
                int currentVertex = this.edgeTo[i].from();
                while (currentVertex != this.start){
                    builder.append(currentVertex).append(" <- ");
                    currentVertex = this.edgeTo[currentVertex].from();
                }
                builder.append(this.start).append(lineSeparator);
            }
        }
        builder.setLength(builder.length() - 1);
        return builder.toString();
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!this.hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = this.edgeTo[v]; e != null; e = this.edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

}
