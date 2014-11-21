package com.company;

/**
 * Created by artemlobachev on 16.11.14.
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> extends BinarySearchTreeDictionary<Key, Value>{

    protected class RedBlackNode extends BinarySearchTreeDictionary<Key, Value>.Node{
        private static final boolean RED = true;
        private static final boolean BLACK = false;

        private RedBlackNode leftRB, rightRB;
        private boolean color;

        @Override
        public RedBlackNode getLeft() {
            return leftRB;
        }

        public void setLeft(RedBlackNode left) {
            this.leftRB = left;
        }

        @Override
        public RedBlackNode getRight() {
            return rightRB;
        }

        public void setRight(RedBlackNode right) {
            this.rightRB = right;
        }

        public boolean getColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public RedBlackNode(Key key, Value value, boolean color) {
            super(key, value);
            this.color = color;
        }
    }

    private RedBlackNode root;

    private boolean isRed(RedBlackNode n) {
        return n != null && n.getColor() == RedBlackNode.RED;
    }

    private RedBlackNode rotateLeft(RedBlackNode root){
        RedBlackNode newRoot = root.getRight();
        root.setRight(newRoot.getLeft());
        newRoot.setLeft(root);
        newRoot.setColor(root.getColor());
        root.setColor(RedBlackNode.RED);
        int subTreeSize = root.getSize();
        root.setSize(newRoot.getSize());
        newRoot.setSize(subTreeSize);
        return newRoot;
    }

    private RedBlackNode rotateRight(RedBlackNode root){
        RedBlackNode newRoot = root.getLeft();
        root.setLeft(newRoot.getRight());
        newRoot.setRight(root);
        newRoot.setColor(root.getColor());
        root.setColor(RedBlackNode.RED);
        int subTreeSize = root.getSize();
        root.setSize(newRoot.getSize());
        newRoot.setSize(subTreeSize);
        return newRoot;
    }

    private void flipColors(RedBlackNode n){
        n.leftRB.setColor(RedBlackNode.BLACK);
        n.rightRB.setColor(RedBlackNode.BLACK);
        n.setColor(RedBlackNode.RED);
    }

    private RedBlackNode putToSubTree(RedBlackNode root, Key key, Value value){
        if (root == null)
            return new RedBlackNode(key, value, RedBlackNode.RED);

        int compareResult = key.compareTo(root.getKey());

        if (compareResult < 0) root.setLeft(this.putToSubTree(root.getLeft(), key, value));
        else if (compareResult > 0) root.setRight(this.putToSubTree(root.getRight(), key, value));
        else root.setValue(value);

        if (this.isRed(root.getRight()) && (!this.isRed(root.getLeft()))) root = this.rotateLeft(root);
        if (this.isRed(root.getLeft()) && this.isRed(root.getLeft().getLeft())) root = this.rotateRight(root);
        if (this.isRed(root.getLeft()) && (this.isRed(root.getRight()))) this.flipColors(root);

        root.setSize(1 + this.subTreeSize(root.getLeft()) + this.subTreeSize(root.getRight()));

        return root;
    }

    @Override
    public Value get(Key key) {
        return super.getInSubtree(this.root, key);
    }

    @Override
    public void put(Key key, Value value) {
        this.root = this.putToSubTree(this.root, key, value);
        this.root.setColor(RedBlackNode.BLACK);
        this.size = this.subTreeSize(this.root);
    }

    @Override
    public void delete(Key key) {
        // TODO: next iteration
        throw new UnsupportedOperationException();
    }
}
