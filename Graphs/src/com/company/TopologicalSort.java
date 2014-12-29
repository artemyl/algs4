package com.company;

import java.util.Stack;

/**
 * Created by artemlobachev on 29.12.14.
 */
public class TopologicalSort {
    private final boolean marked[];
    private final Stack<Integer> reversePost;

    public TopologicalSort(Digraph graph){
        DirectedCycle directedCycle = new DirectedCycle(graph);
        if (directedCycle.hasCycle())
            throw new RuntimeException("Can't build topological order: graph has cycles");
        this.marked = new boolean[graph.vertexesCount()];
        this.reversePost = new Stack<>();
        for (int i = 0; i < graph.vertexesCount(); i++){
            if (!this.marked[i]){
                this.search(graph, i);
            }
        }
    }

    private void search(Digraph graph, int vertex){
        this.marked[vertex] = true;
        for(int i:graph.vertexEdges(vertex)){
            if (!this.marked[i]){
                this.search(graph,i);
            }
        }
        this.reversePost.push(vertex);
    }

    @Override
    public String toString(){
        String separator = " -> ";
        StringBuilder builder  = new StringBuilder("Topological order is:" + System.lineSeparator());
        while (!this.reversePost.isEmpty()){
            builder.append(this.reversePost.pop()).append(separator);
        }
        builder.setLength(builder.length() - separator.length());
        return builder.toString();
    }
}
