package com.company;

/**
 * Created by artemlobachev on 10.11.14.
 */
@SuppressWarnings("unused")
public abstract class AbstractDictionary<Key extends Comparable<Key>, Value> {
    protected int size;

    public abstract Value get(Key key);
    public abstract void put(Key key, Value value);
    public abstract void  delete(Key key);

    public abstract Key min();
    public abstract Key max();

    public abstract int rank(Key key);
    public abstract Key select(int k);

    public boolean contains(Key key){
        return this.get(key) != null;
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

}
