package com.company;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.introcs.BinaryStdIn;
import edu.princeton.cs.introcs.BinaryStdOut;

/**
 * Created by artemlobachev on 02.01.15.
 */
public class Huffman {

    private static final int alphabetCapacity = 256;

    private static class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        private boolean isLeaf() {
            return (this.left == null && this.right == null);
        }

        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    public static void compress() {
        String originalStr = BinaryStdIn.readString();
        char[] originalStrChars = originalStr.toCharArray();
        int[] freq = new int[alphabetCapacity];
        for (char c : originalStrChars) {
            freq[c]++;
        }
        Node root = buildTrie(freq);
        String[] codeTable = new String[alphabetCapacity];
        buildCode(codeTable, root, "");
        writeTrie(root);
        BinaryStdOut.write(originalStrChars.length);
        for (char symbol : originalStrChars) {
            String code = codeTable[symbol];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '0') {
                    BinaryStdOut.write(false);
                } else {
                    BinaryStdOut.write(true);
                }
            }
        }
        BinaryStdOut.close();
    }

    private static Node buildTrie(int[] freq) {
        MinPQ<Node> pq = new MinPQ<>();
        for (char i = 0; i < alphabetCapacity; i++)
            if (freq[i] > 0)
                pq.insert(new Node(i, freq[i], null, null));
        // special case in case there is only one character with a nonzero frequency
        if (pq.size() == 1) {
            if (freq['\0'] == 0) pq.insert(new Node('\0', 0, null, null));
            else                 pq.insert(new Node('\1', 0, null, null));
        }
        while (pq.size() > 1) {
            Node left  = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.insert(parent);
        }
        return pq.delMin();
    }

    private static void writeTrie(Node x) {
        if (x.isLeaf()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.ch, 8);
        } else {
            BinaryStdOut.write(false);
            writeTrie(x.left);
            writeTrie(x.right);
        }
    }

    private static void buildCode(String[] st, Node x, String s) {
        if (!x.isLeaf()) {
            buildCode(st, x.left,  s + '0');
            buildCode(st, x.right, s + '1');
        }
        else {
            st[x.ch] = s;
        }
    }

    public static void expand() {
        Node root = readTrie();
        int length = BinaryStdIn.readInt();
        for (int i = 0; i < length; i++) {
            Node x = root;
            while (!x.isLeaf()) {
                boolean bit = BinaryStdIn.readBoolean();
                if (bit) x = x.right;
                else     x = x.left;
            }
            BinaryStdOut.write(x.ch, 8);
        }
        BinaryStdOut.close();
    }

    private static Node readTrie() {
        boolean isLeaf = BinaryStdIn.readBoolean();
        if (isLeaf) {
            return new Node(BinaryStdIn.readChar(), -1, null, null);
        }
        else {
            return new Node('\0', -1, readTrie(), readTrie());
        }
    }
}
