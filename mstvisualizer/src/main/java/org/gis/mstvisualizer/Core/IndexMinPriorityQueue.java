package org.gis.mstvisualizer.Core;

import java.util.NoSuchElementException;

public class IndexMinPriorityQueue<Key extends Comparable<Key>> {

    private final int maxN;
    private  int N;
    private final int[] pq;
    private final int[] qp;
    private final Key[] keys;

    @SuppressWarnings("unchecked")
    public IndexMinPriorityQueue(final int maxElements) {
        if(maxElements < 0) {
            throw new IllegalArgumentException();
        }
        maxN = maxElements;

        keys = (Key[]) new Comparable[maxElements + 1];
        pq = new int[maxElements + 1];
        qp = new int[maxElements + 1];

        for (int i = 0; i <= maxElements; i++) {
            qp[i] = -1;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(final int i) {
        if(i < 0 || i >= maxN) {
            throw new IndexOutOfBoundsException();
        }
        return qp[i] != -1;
    }

    public void insert(final int i, final Key key) {
        if(i < 0 || i >= maxN) {
            throw new IndexOutOfBoundsException();
        }

        if(contains(i)) {
            throw new IllegalArgumentException();
        }

        N++;
        qp[i] = N;
        pq[N] = i;
        keys[i] = key;
        swim(N);
    }

    public int delMin() {
        if(N == 0) {
            throw new NoSuchElementException();
        }

        final int min = pq[1];

        exch(1, N--);
        sink(1);

        qp[min] = -1;
        keys[pq[N + 1]] = null;
        pq[N + 1] = -1;

        return min;
    }

    public void decreaseKey(final int i, final Key key) {
        if (i < 0 || i >= maxN) {
            throw new IndexOutOfBoundsException();
        }

        if (!contains(i)) {
            throw new NoSuchElementException();
        }

        if (keys[i].compareTo(key) <= 0) {
            throw new IllegalArgumentException();

        }

        keys[i] = key;
        swim(qp[i]);
    }

    private boolean greater(final int i, final int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(final int i, final int j) {
        final int swap = pq[i];

        pq[i] = pq[j];
        pq[j] = swap;

        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int key)  {
        while (key > 1 && greater(key / 2, key)) {
            exch(key, key / 2);
            key = key / 2;
        }
    }

    private void sink(int key) {
        while (2 * key <= N) {
            int j = 2 * key;

            if (j < N && greater(j, j + 1))
                j++;

            if (!greater(key, j))
                break;

            exch(key, j);
            key = j;
        }
    }
}
