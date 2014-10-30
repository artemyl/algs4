package org.artemyl;

/**
 * Created by artemlobachev on 29.10.14.
 */
public class WeightedQuickUnionFind extends UnionFind{
    protected int treeHeight[];

    public WeightedQuickUnionFind(int N){
        this.objectToSet = new int[N];
        this.treeHeight = new int[N];
        for (int i = 0; i < N; i++){
            this.treeHeight[i] = 1;
            this.objectToSet[i] = i;
        }
        this.count = N;
    }

    @Override
    public int find(int objectID) {
        while (this.objectToSet[objectID] != objectID) {
            objectID = this.objectToSet[objectID];
        }
        return objectID;
    }

    @Override
    public void union(int objectID1, int objectID2) {
        int root1 = this.find(objectID1);
        int root2 = this.find(objectID2);
        if (root1 == root2) return;
        // in original version here (http://algs4.cs.princeton.edu/15uf/WeightedQuickUnionUF.java.html)
        // author takes account of tree size, but not tree height,
        // but we are not interested in tree size, because find method's operations count depends on tree height
        if (treeHeight[root1] > treeHeight[root2]){
            this.objectToSet[root2] = root1;
        } else {
            this.objectToSet[root1] = root2;
            if (this.treeHeight[root1] == this.treeHeight[root2]){
                this.treeHeight[root2]++;
            }
        }
        this.count--;
    }

}
