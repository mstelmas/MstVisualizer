package org.gis.mstvisualizer.Core.Algorithms;

import org.gis.mstvisualizer.Core.Graph.Edge;
import org.gis.mstvisualizer.Core.Graph.MinEdgeComparator;
import org.gis.mstvisualizer.Core.Graph.WeightedGraph;
import org.gis.mstvisualizer.Core.Simulation.Events.Mst.AddEdgeToMstEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.EdgePickedEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.EdgeVisitedEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.Queue.DequeueEdgeEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.Queue.EnqueueEdgeEvent;
import org.gis.mstvisualizer.Core.UnionFind;

import java.util.PriorityQueue;
import java.util.Queue;


public class KruskalMST extends AlgorithmMST {

    public KruskalMST(final WeightedGraph G) {
        super("Kruskal's Algorithm");

        final Queue<Edge> edgesQueue = new PriorityQueue<Edge>(new MinEdgeComparator());

        for(Edge e : G.edges()) {
            edgesQueue.add(e);

            /* EVENT */
            algorithmEventStorage.addEvent(new EnqueueEdgeEvent(e));
        }

        final UnionFind uf = new UnionFind(G.getV());

        while(!edgesQueue.isEmpty() && mst.size() < G.getV() - 1) {
            final Edge e = edgesQueue.remove();

            /* EVENT */
            algorithmEventStorage.addEvent(new DequeueEdgeEvent(e));

             /* EVENT */
            algorithmEventStorage.addEvent(new EdgePickedEvent(e));

            final int v = e.either();
            final int w = e.other(v);

            if(!uf.connected(v, w)) {
                uf.union(v, w);

                 /* EVENT */
                algorithmEventStorage.addEvent(new EdgeVisitedEvent(e));

                mst.add(e);
                weight += e.getWeight();

                 /* EVENT */
                algorithmEventStorage.addEvent(new AddEdgeToMstEvent(e));
            }
        }
    }
}
