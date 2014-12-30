package com.company;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.UF;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by artemlobachev on 30.12.14.
 */
public class KruskalMST {
    private double weight;
    private Queue<Edge> mst = new LinkedList<>();

    public KruskalMST(EdgeWeightedGraph graph) {
        // more efficient to build heap by passing array of edges
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : graph.edges()) {
            pq.insert(e);
        }

        // run greedy algorithm
        UF uf = new UF(graph.vertexesCount());
        while (!pq.isEmpty() && this.mst.size() < graph.vertexesCount() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) { // v-w does not create a cycle
                uf.union(v, w);  // merge v and w components
                this.mst.offer(e);  // add edge e to mst
                this.weight += e.weight();
            }
        }
    }

    public Iterable<Edge> edges() {
        return this.mst;
    }

    public double weight() {
        return this.weight;
    }

}
