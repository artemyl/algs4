package com.company;

import edu.princeton.cs.introcs.StdOut;

/**
 * Created by artemlobachev on 24.12.14.
 */
public class DeepSearch implements PathChecker {
    private final boolean marked[];
    private final int startVertex;
    private int count;

    public DeepSearch(Graph graph, int vertex){
        this.marked = new boolean[graph.vertexesCount()];
        this.startVertex = vertex;
        this.count = 0;
        this.search(graph, vertex);
    }

    private void search(Graph graph, int vertex){
        this.marked[vertex] = true;
        this.count++;
        for(int i:graph.vertexEdges(vertex)){
            if (!this.marked[i]){
                this.search(graph,i);
            }
        }
    }

    @Override
    public boolean hasPathTo(int vertex) {
        return this.marked[vertex];
    }

    @Override
    public int reachableVertexesCount() {
        return this.count;
    }

    @Override
    public void printResult(){
        StringBuilder result = new StringBuilder();
        String lineSeparator = System.lineSeparator();
        result.append("DeepSearch result for vertex ").append(this.startVertex).append(":").append(lineSeparator);
        for (int i = 0; i < this.marked.length; i++){
            result.append("Vertex ").append(i).append(" is ");
            if (this.marked[i]) {
                result.append("reachable");
            }
            else {
                result.append("unreachable ");
            }
            result.append(lineSeparator);
        }
        StdOut.print(result.toString());
    }

}
