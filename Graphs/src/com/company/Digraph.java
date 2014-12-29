package com.company;

import edu.princeton.cs.introcs.In;

/**
 * Created by artemlobachev on 27.12.14.
 */
public class Digraph extends Graph {

    public Digraph(int vertexesCount){
        super(vertexesCount);
    }

    public Digraph(In in){
        super(in);
    }

    @Override
    public void addEdge(int vertex1, int vertex2) {
        if (vertex1 < this.vCount && vertex2 < this.vCount){
            this.graphStructure[vertex1].add(vertex2);
            this.eCount++;
        }
    }

    public Digraph reverse(){
        Digraph result = new Digraph(this.vertexesCount());
        for (int v = 0; v < this.vertexesCount(); v++) {
            for (int w : this.vertexEdges(v)) {
                result.addEdge(w, v);
            }
        }
        return result;
    }
}
