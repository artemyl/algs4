package com.company;

import java.util.Stack;

public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private boolean[] onStack;
    private Stack<Integer> cycle;

    public DirectedCycle(Graph graph) {
        this.marked  = new boolean[graph.vertexesCount()];
        this.onStack = new boolean[graph.vertexesCount()];
        this.edgeTo  = new int[graph.vertexesCount()];
        for (int v = 0; v < graph.vertexesCount(); v++)
            if (!this.marked[v]) search(graph, v);
    }

    private void search(Graph graph, int v) {
        this.onStack[v] = true;
        this.marked[v] = true;
        for (int w : graph.vertexEdges(v)) {
            if (this.cycle != null) return;
            else if (!this.marked[w]) {
                this.edgeTo[w] = v;
                this.search(graph, w);
            }
            else if (this.onStack[w]) {
                this.cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    this.cycle.push(x);
                }
                this.cycle.push(w);
                this.cycle.push(v);
            }
        }

        this.onStack[v] = false;
    }

    public boolean hasCycle() {
        return this.cycle != null;
    }

    public Iterable<Integer> cycle() {
        return this.cycle;
    }

    @Override
    public String toString() {
        if (this.hasCycle()){
            StringBuilder builder = new StringBuilder("Graph has at least one cycle:" + System.lineSeparator());
            String separator = " -> ";
            for (int v:this.cycle()){
                builder.append(v).append(separator);
            }
            builder.setLength(builder.length() - separator.length());
            return builder.toString();
        } else {
            return "Graph has no cycles";
        }
    }
}
