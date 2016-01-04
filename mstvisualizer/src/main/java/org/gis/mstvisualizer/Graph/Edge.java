package org.gis.mstvisualizer.Graph;

import lombok.Getter;

public class Edge implements Comparable<Edge>{

    @Getter
    private final double weight;

    private final int v;
    private final int w;

    public Edge(int v, int w, double weight) {
        if(v < 0 || w < 0) {
            throw new IndexOutOfBoundsException();
        }

        if(Double.isNaN(weight)) {
            throw new IllegalArgumentException();
        }

        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either() {
        return v;
    }

    public int other(final int v) {
        if(this.v == v)
            return this.w;
        else if(this.w == v)
            return this.v;
        else
            throw new IllegalArgumentException();
    }


    public int compareTo(Edge that) {
        if(this.getWeight() < that.getWeight())
            return -1;
        else if(this.getWeight() > that.getWeight())
            return +1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return String.format("%d - %d %.5f", v, w, weight);
    }
}
