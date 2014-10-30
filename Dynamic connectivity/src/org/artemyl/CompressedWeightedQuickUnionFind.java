package org.artemyl;

/**
 * Created by artemlobachev on 30.10.14.
 */
public class CompressedWeightedQuickUnionFind extends WeightedQuickUnionFind {

    public CompressedWeightedQuickUnionFind(int N) {
        super(N);
    }

    @Override
    public int find(int objectID) {
        int originalObjectID = objectID;
        objectID = super.find(objectID);
        while (this.objectToSet[originalObjectID] != objectID){
            int next = this.objectToSet[originalObjectID];
            this.objectToSet[originalObjectID] = objectID;
            this.treeHeight[originalObjectID] = 2;
            originalObjectID = next;
        }
        return objectID;
    }


}
