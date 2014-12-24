package com.company;

/**
 * Created by artemlobachev on 24.12.14.
 */
public interface PathChecker {
    public boolean hasPathTo(int vertex);
    public int reachableVertexesCount();
    public void printResult();
}
