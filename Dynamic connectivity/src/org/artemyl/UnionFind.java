package org.artemyl;

/**
 * Created by artemlobachev on 29.10.14.
 */
public abstract class UnionFind {

    protected int id[];
    protected int count;

    public abstract int find(int id);
    public abstract int union(int id1, int id2);

    public int count(){
        return this.count;
    }

    public boolean connected(int id1, int id2){
        return this.find(id1) == this.find(id2);
    }

}
