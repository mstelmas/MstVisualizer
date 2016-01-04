package org.gis.mstvisualizer.Core;

import lombok.Getter;

public class UnionFind {

    @Getter
    private int count;

    private final int[] parent;
    private final byte[] rank;

    public UnionFind(final int N) {
        if(N < 0)
            throw new IllegalArgumentException();

        count = N;
        parent = new int[N];
        rank = new byte[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(final int componentId) {
        validate(componentId);

        int p = componentId;

        while(p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }

        return p;
    }

    public boolean connected(final int p, final int q) {
        return find(p) == find(q);
    }

    public void union(final int p, final int q) {
        final int rootP = find(p);
        final int rootQ = find(q);

        if(rootP == rootQ)
            return;

        if(rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        }
        else if(rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        }
        else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }

        count--;
    }

    private void validate(final int id) {
        final int N = parent.length;

        if(id < 0 || id >= N) {
            throw new IndexOutOfBoundsException();
        }
    }

}
