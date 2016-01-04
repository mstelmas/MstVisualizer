package org.gis.mstvisualizer.Algorithms;

import org.gis.mstvisualizer.Graph.Edge;
import org.gis.mstvisualizer.Graph.MinEdgeComparator;
import org.gis.mstvisualizer.Graph.WeightedGraph;
import org.gis.mstvisualizer.UnionFind;

import java.util.PriorityQueue;
import java.util.Queue;


public class KruskalMST extends AlgorithmMST {

    public KruskalMST(final WeightedGraph G) {
        final Queue<Edge> edgesQueue = new PriorityQueue<Edge>(new MinEdgeComparator());

        for(Edge e : G.edges()) {
            edgesQueue.add(e);
        }

        final UnionFind uf = new UnionFind(G.getV());

        while(!edgesQueue.isEmpty() && mst.size() < G.getV() - 1) {
            final Edge e = edgesQueue.remove();
            final int v = e.either();
            final int w = e.other(v);

            if(!uf.connected(v, w)) {
                uf.union(v, w);
                mst.add(e);
                weight += e.getWeight();
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }
}
