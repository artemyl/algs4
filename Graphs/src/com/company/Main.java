package com.company;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    private static Graph createTestGraph(){
        Graph testGraph = new Digraph(15);

        testGraph.addEdge(0,1);
        testGraph.addEdge(0,2);
        testGraph.addEdge(0,3);
        testGraph.addEdge(1,4);
        testGraph.addEdge(1,5);
        testGraph.addEdge(1,6);
        testGraph.addEdge(2,7);
        testGraph.addEdge(3,8);
        testGraph.addEdge(3,9);

        testGraph.addEdge(10,11);
        testGraph.addEdge(10,12);
        testGraph.addEdge(11,12);

        testGraph.addEdge(10,13);
        testGraph.addEdge(13,14);

        testGraph.addEdge(9,0);

        return testGraph;
    }

    private static Digraph createTopologicalSortTestDigraph(){
        Digraph testGraph = new Digraph(7);

        testGraph.addEdge(0,1);
        testGraph.addEdge(0,4);
        testGraph.addEdge(1,2);
        testGraph.addEdge(2,3);
        testGraph.addEdge(4,3);
        testGraph.addEdge(6,3);
        testGraph.addEdge(4,5);

        return testGraph;
    }

    private static EdgeWeightedGraph createMSTTestGraph(){
        try {
            return new EdgeWeightedGraph(new In(new URL("http://algs4.cs.princeton.edu/43mst/tinyEWG.txt")));
        } catch (MalformedURLException e) {
            return null;
        }
    }

    private static EdgeWeightedDigraph createMinPathTest(){
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(4);

        DirectedEdge edge = new DirectedEdge(0,1,1);
        graph.addEdge(edge);
        edge = new DirectedEdge(0,2,2);
        graph.addEdge(edge);
        edge = new DirectedEdge(0,3,5);
        graph.addEdge(edge);
        edge = new DirectedEdge(1,2,4);
        graph.addEdge(edge);
        edge = new DirectedEdge(2,3,2);
        graph.addEdge(edge);

        return graph;
    }

    public static void main(String[] args) {
        Graph testGraph = createTestGraph();
        StdOut.println(testGraph);

        PathChecker deepSearch = new BreadthFirstSearch(testGraph, 9);
        deepSearch.printResult();

        DirectedCycle directedCycle = new DirectedCycle(testGraph);
        StdOut.println(directedCycle);

        Digraph topologicalSortTestData = createTopologicalSortTestDigraph();
        TopologicalSort topologicalSort = new TopologicalSort(topologicalSortTestData);
        StdOut.println(topologicalSort);

        StdOut.println("Downloading test MST graph data ...");
        EdgeWeightedGraph testWeightedGraph = createMSTTestGraph();

        if (testGraph != null) {
            StdOut.println("Test MST Graph downloaded. Testing algorithm.");
            PrimMST primMST = new PrimMST(testWeightedGraph);
            KruskalMST kruskalMST = new KruskalMST(testWeightedGraph);
            StdOut.printf("Test complete.%nMST weight(P) = %f%nMST weight(K) = %f", primMST.weight(), kruskalMST.weight());
        } else {
            StdOut.println("Error downloading test data");
        }

        EdgeWeightedDigraph edgeWeightedDigraph = createMinPathTest();
        PathChecker d = new DijkstraSP(edgeWeightedDigraph,0);
        d.printResult();
    }
}
