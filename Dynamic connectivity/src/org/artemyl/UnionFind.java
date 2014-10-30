package org.artemyl;

/**
 * Created by artemlobachev on 29.10.14.
 */
public abstract class UnionFind {

    protected int objectToSet[];
    protected int count;

    public abstract int find(int objectID);
    public abstract void union(int objectID1, int objectID2);

    public int count(){
        return this.count;
    }

    public boolean connected(int objectID1, int objectID2){
        return this.find(objectID1) == this.find(objectID2);
    }

}
