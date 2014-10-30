package org.artemyl;

/**
 * Added from <a href="http://algs4.cs.princeton.edu/15uf/"> book official site</> for quick experiment.
 */
public class QuickFind extends UnionFind {

    private int[] id;    // id[i] = component identifier of i

    public QuickFind(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    @Override
    public int find(int p) {
        return id[p];
    }

    @Override
    public void union(int p, int q) {
        if (connected(p, q)) return;
        int pid = id[p];
        for (int i = 0; i < id.length; i++)
            if (id[i] == pid) id[i] = id[q];
        count--;
    }

}
