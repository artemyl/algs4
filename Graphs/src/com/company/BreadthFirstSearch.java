package com.company;

import edu.princeton.cs.introcs.StdOut;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by artemlobachev on 24.12.14.
 */
public class BreadthFirstSearch implements PathChecker {
    private final boolean marked[];
    private final int startVertex;
    private int count;
    private int edgeTo[];

    public BreadthFirstSearch(Graph graph, int startVertex){
        this.startVertex = startVertex;
        this.count = 0;
        this.marked = new boolean[graph.vertexesCount()];
        this.edgeTo = new int[graph.vertexesCount()];
        this.edgeTo[startVertex] = startVertex;
        this.search(graph, startVertex);
    }

    private void search(Graph graph, int startVertex){
        this.marked[startVertex] = true;
        this.count++;
        Queue<Integer> vertexes = new LinkedList<>();
        vertexes.offer(startVertex);
        while (!vertexes.isEmpty()){
            Integer currentElement = vertexes.poll();
            for (int i : graph.vertexEdges(currentElement)){
                if(!this.marked[i]) {
                    this.marked[i] = true;
                    this.count++;
                    this.edgeTo[i] = currentElement;
                    vertexes.offer(i);
                }
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
    public void printResult() {
        StringBuilder result = new StringBuilder();
        String lineSeparator = System.lineSeparator();
        result.append("DeepSearch result for vertex ").append(this.startVertex).append(":").append(lineSeparator);
        for (int i = 0; i < this.marked.length; i++){
            result.append("Vertex ").append(i).append(" is ");
            if (this.marked[i]) {
                result.append("reachable, the shortest way is: ").append(lineSeparator);
                int tmp = i;
                while (tmp != this.startVertex) {
                    result.append(tmp).append(" -> ");
                    tmp = this.edgeTo[tmp];
                }
                result.append(tmp);
            }
            else {
                result.append("unreachable ");
            }
            result.append(lineSeparator);
        }
        StdOut.print(result.toString());
    }
}
