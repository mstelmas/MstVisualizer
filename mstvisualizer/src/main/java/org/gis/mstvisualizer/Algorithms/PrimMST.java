package org.gis.mstvisualizer.Algorithms;

import org.gis.mstvisualizer.Graph.Edge;
import org.gis.mstvisualizer.Graph.WeightedGraph;

public class PrimMST extends AlgorithmMST {

    private final Edge[] edgeTo;
    private final double[] distanceTo;
    private final boolean[] marked;


    public PrimMST(final WeightedGraph G) {
        edgeTo = new Edge[G.getV()];
        distanceTo = new double[G.getV()];
        marked = new boolean[G.getV()];
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }
}
