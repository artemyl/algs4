package com.company;

/**
 * Created by artemlobachev on 10.11.14.
 */
public class BinarySearchDictionary<Key extends Comparable<Key>, Value> extends AbstractDictionary<Key, Value> {
    private int capacity;
    private Key keys[];
    private Value values[];

    @SuppressWarnings("unchecked")
    public BinarySearchDictionary(int capacity){
        this.size = 0;
        this.capacity = capacity;
        this.keys = (Key []) new Comparable[capacity];
        this.values = (Value []) new Object[capacity];
    }

    @Override
    public Value get(Key key) {
        if (key == null) throw new NullPointerException();
        int keyNumber = this.rank(key);
        if (key.compareTo(this.keys[keyNumber]) == 0) return this.values[keyNumber];
        else return null;
    }

    @Override
    public void put(Key key, Value value) {
        if (key == null) throw new NullPointerException();
        if (this.size < this.capacity) {
            int placeForNewValue = this.rank(key);
            for (int i = this.size - 1; i >= placeForNewValue; i--){
                this.keys[i + 1] = this.keys[i];
                this.values[i + 1] = this.values[i];
            }
            this.keys[placeForNewValue] = key;
            this.values[placeForNewValue] = value;
            this.size++;
        }
    }

    @Override
    public void delete(Key key) {
        if (key == null) throw new NullPointerException();
        int elementNumber = this.rank(key);
        if (elementNumber < this.size && key.equals(this.keys[elementNumber])){
            for (int i = elementNumber; i < this.size; i++){
                if (i == this.size - 1){
                    this.keys[i] = null;
                    this.values[i] = null;
                } else {
                    this.keys[i] = this.keys[i + 1];
                    this.values[i] = this.values[i + 1];
                }
            }
            this.size--;
        }

    }

    @Override
    public Key min() {
        return this.keys[0];
    }

    @Override
    public Key max() {
        return this.keys[size - 1];
    }

    @Override
    public int rank(Key key) {
        int min = 0, max = this.size - 1, mid = min + (max - min) / 2;
        while (min <= max){
            int compareResult = this.keys[mid].compareTo(key);
            if (compareResult == 0) return mid;
            else if (compareResult < 0) min = mid + 1;
            else max = mid - 1;
            mid = min + (max - min) / 2;
        }
        return mid;
    }

    @Override
    public Key select(int k) {
        if (k > 0 && k < this.size) {
            return this.keys[k];
        } else {
            return null;
        }
    }
}
