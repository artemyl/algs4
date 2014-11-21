package com.company;

/**
 * Created by artemlobachev on 12.11.14.
 */
public class BinarySearchTreeDictionary<Key extends Comparable<Key>, Value> extends AbstractDictionary<Key, Value> {
    private Node root;

    protected class Node{
        private Key key;
        private Value value;
        private Node left, right;
        private int size;
        public Node(Key key, Value value){
            this.key = key;
            this.value = value;
            this.size = 1;
        }

        public Key getKey() {
            return key;
        }

        public Value getValue() {

            return value;
        }

        public void setValue(Value value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    protected int subTreeSize(Node root){
        if (root == null) return 0;
        else return root.getSize();
    }

    protected Value getInSubtree(Node root, Key key){
        if (root == null) return null;
        int compareResult = key.compareTo(root.getKey());
        if (compareResult < 0) return this.getInSubtree(root.getLeft(), key);
        else if (compareResult > 0) return  this.getInSubtree(root.getRight(), key);
        else return root.getValue();
    }

    protected Node putToSubtree(Node root, Key key, Value value){
        if (root == null) return new Node(key, value);
        int compareResult = key.compareTo(root.getKey());
        if (compareResult < 0) root.setLeft(this.putToSubtree(root.getLeft(), key, value));
        else if (compareResult > 0) root.setRight(this.putToSubtree(root.getRight(), key, value));
        else root.setValue(value);
        root.setSize(this.subTreeSize(root.getLeft()) + this.subTreeSize(root.getRight()) + 1);
        this.size = this.root.getSize();
        return root;
    }

    protected Node selectInSubTree(Node root, int k){
        if (root == null) return null;
        int size = this.subTreeSize(root);
        if (size < k) return this.selectInSubTree(root.getLeft(), k);
        else if (size > k) return this.selectInSubTree(root.getRight(), k - size - 1);
        else return root;
    }

    protected int rankInSubTree(Node root, Key key){
        if (root == null) return 0;
        int compareResult = key.compareTo(root.getKey());
        if (compareResult < 0) return this.rankInSubTree(root.getLeft(), key);
        else if (compareResult > 0) return this.subTreeSize(root.getLeft()) + this.rankInSubTree(root.getRight(), key) + 1;
        else return root.getSize() - 1;
    }

    protected Node minInSubTree(Node root){
        if (root == null) return null;
        Node t = root;
        while (t.getLeft() != null) t = t.getLeft();
        return t;
    }

    protected Node deleteMin(Node root){
        if (root.getLeft() == null) return root.getRight();
        root.setLeft(this.deleteMin(root.getLeft()));
        root.setSize(this.subTreeSize(root.getLeft()) + this.subTreeSize(root.getRight()) + 1);
        this.size = this.root.getSize();
        return root;
    }

    protected Node delete(Node root, Key key){
        if (root == null) return null;
        int compareResult = key.compareTo(root.getKey());
        if (compareResult < 0) root.setLeft(this.delete(root.getLeft(), key));
        else if (compareResult > 0) root.setRight(this.delete(root.getRight(), key));
        else {
            if (root.getLeft() == null) return root.getRight();
            if (root.getRight() == null) return root.getLeft();
            Node delNode = root;
            root = this.minInSubTree(root.getRight());
            root.setRight(this.deleteMin(delNode.getRight()));
            root.setLeft(delNode.getLeft());
        }
        root.setSize(this.subTreeSize(root.getLeft()) + this.subTreeSize(root.getRight()) + 1);
        this.size = this.root.getSize();
        return root;
    }

    @Override
    public Value get(Key key) {
        return this.getInSubtree(this.root, key);
    }

    @Override
    public void put(Key key, Value value) {
        this.root = this.putToSubtree(this.root, key, value);

    }

    @Override
    public void delete(Key key) {
        this.delete(this.root, key);
    }

    @Override
    public Key min() {
        return this.minInSubTree(this.root).getKey();
    }

    @Override
    public Key max() {
        if (this.root == null) return null;
        Node t = this.root;
        while (t.getRight() != null) t = t.getRight();
        return t.getKey();
    }

    @Override
    public int rank(Key key) {
        return this.rankInSubTree(this.root, key);
    }

    @Override
    public Key select(int k) {
        return this.selectInSubTree(this.root, k).getKey();
    }
}
