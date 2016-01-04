package org.gis.mstvisualizer.Core.Algorithms;

import org.gis.mstvisualizer.Core.Graph.Edge;
import org.gis.mstvisualizer.Core.Graph.MinEdgeComparator;
import org.gis.mstvisualizer.Core.Graph.WeightedGraph;
import org.gis.mstvisualizer.Core.UnionFind;

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
}
