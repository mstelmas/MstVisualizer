package org.gis.mstvisualizer.Core.Algorithms;

import edu.uci.ics.jung.graph.Graph;
import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Simulation.Events.EdgePickedEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.EdgeVisitedEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.Mst.AddEdgeToMstEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.Queue.DequeueEdgeEvent;
import org.gis.mstvisualizer.Core.UnionFind;

import java.util.PriorityQueue;


public class KruskalMST extends AlgorithmMST {

    public KruskalMST(final Graph<Integer, Link> G) {
        super("Kruskal's Algorithm");

        final PriorityQueue<Link> edgesQueue = new PriorityQueue<>(G.getEdges());

        final UnionFind uf = new UnionFind(G.getVertexCount());

        while(!edgesQueue.isEmpty() && mst.size() < G.getVertexCount() - 1) {
            final Link e = edgesQueue.poll();

            /* EVENT */
            algorithmEventStorage.addEvent(new DequeueEdgeEvent(e));

             /* EVENT */
            algorithmEventStorage.addEvent(new EdgePickedEvent(e));

            final int v = G.getEndpoints(e).getFirst();
            final int w = G.getEndpoints(e).getSecond();

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
