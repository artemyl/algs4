package com.company;

import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by artemlobachev on 30.12.14.
 */
public class PrimMST {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph graph) {
        this.edgeTo = new Edge[graph.vertexesCount()];
        this.distTo = new double[graph.vertexesCount()];
        this.marked = new boolean[graph.vertexesCount()];
        this.pq = new IndexMinPQ<>(graph.vertexesCount());
        for (int v = 0; v < graph.vertexesCount(); v++) {
            this.distTo[v] = Double.POSITIVE_INFINITY;
        }
        for (int v = 0; v < graph.vertexesCount(); v++) {
            if (!this.marked[v]){
                this.prim(graph, v);
            }
        }
    }

    private void prim(EdgeWeightedGraph graph, int s) {
        this.distTo[s] = 0.0;
        this.pq.insert(s, this.distTo[s]);
        while (!this.pq.isEmpty()) {
            int v = this.pq.delMin();
            this.scan(graph, v);
        }
    }

    private void scan(EdgeWeightedGraph graph, int v) {
        this.marked[v] = true;
        for (Edge e : graph.adj(v)) {
            int w = e.other(v);
            if (this.marked[w]) continue;
            if (e.weight() < this.distTo[w]) {
                this.distTo[w] = e.weight();
                this.edgeTo[w] = e;
                if (this.pq.contains(w)){
                    this.pq.decreaseKey(w, this.distTo[w]);
                }
                else {
                    this.pq.insert(w, this.distTo[w]);
                }
            }
        }
    }

    public Iterable<Edge> edges() {
        Queue<Edge> mst = new LinkedList<>();
        for (Edge e : this.edgeTo) {
            if (e != null) {
                mst.offer(e);
            }
        }
        return mst;
    }

    public double weight() {
        double weight = 0.0;
        for (Edge e : this.edges())
            weight += e.weight();
        return weight;
    }

}
