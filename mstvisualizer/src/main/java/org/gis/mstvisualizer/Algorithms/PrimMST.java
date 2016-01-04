package org.gis.mstvisualizer.Algorithms;

import org.gis.mstvisualizer.Graph.Edge;
import org.gis.mstvisualizer.Graph.WeightedGraph;
import org.gis.mstvisualizer.IndexMinPriorityQueue;

public class PrimMST extends AlgorithmMST {

    private final Edge[] edgeTo;
    private final double[] distanceTo;
    private final boolean[] marked;
    private final IndexMinPriorityQueue<Double> pq;


    public PrimMST(final WeightedGraph G) {
        edgeTo = new Edge[G.getV()];
        distanceTo = new double[G.getV()];
        marked = new boolean[G.getV()];
        pq = new IndexMinPriorityQueue<>(G.getV());

        for (int i = 0; i < G.getV(); i++) {
            distanceTo[i] = Double.POSITIVE_INFINITY;
        }

        for (int i = 0; i < G.getV(); i++) {
            if(!marked[i]) {
                prim(G, i);
            }
        }

        weight = 0.0;

        for (int v = 0; v < edgeTo.length; v++) {
            final Edge e = edgeTo[v];
            if(e != null) {
                mst.add(e);
                weight += e.getWeight();
            }
        }
    }

    private void prim(final WeightedGraph G, final int s) {
        distanceTo[s] = 0.0;
        pq.insert(s, distanceTo[s]);
        while(!pq.isEmpty()) {
            final int v = pq.delMin();
            scan(G, v);
        }
    }

    private void scan(final WeightedGraph G, final int v) {
        marked[v] = true;

        for(Edge e : G.adj(v)) {
            final int w = e.other(v);
            if(marked[w]) continue;
            if(e.getWeight() < distanceTo[w]) {
                distanceTo[w] = e.getWeight();
                edgeTo[w] = e;

                if(pq.contains(w)) {
                    pq.decreaseKey(w, distanceTo[w]);
                } else {
                    pq.insert(w, distanceTo[w]);
                }
            }
        }
    }
}
