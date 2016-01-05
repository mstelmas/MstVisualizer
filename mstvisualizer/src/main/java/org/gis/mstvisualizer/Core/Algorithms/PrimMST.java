package org.gis.mstvisualizer.Core.Algorithms;

import org.gis.mstvisualizer.Core.Graph.Edge;
import org.gis.mstvisualizer.Core.Graph.MinEdgeComparator;
import org.gis.mstvisualizer.Core.Graph.WeightedGraph;
import org.gis.mstvisualizer.Core.Simulation.Events.Mst.AddEdgeToMstEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.Queue.DequeueEdgeEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.Queue.EnqueueEdgeEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.VertexPickedEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.VertexVisitedEvent;

import java.util.PriorityQueue;
import java.util.Queue;

public class PrimMST extends AlgorithmMST {

    private final boolean[] marked;

    final Queue<Edge> edgesQueue = new PriorityQueue<Edge>(new MinEdgeComparator());


    public PrimMST(final WeightedGraph G) {
        super("Prim's Algorithm");

        marked = new boolean[G.getV()];

        for (int i = 0; i < G.getV(); i++) {
            if(!marked[i]) {

                /* EVENT */
                algorithmEventStorage.addEvent(new VertexPickedEvent(i));

                prim(G, i);
            }
        }
    }

    private void prim(final WeightedGraph G, final int s) {
        scan(G, s);

        while(!edgesQueue.isEmpty()) {
            final Edge edge = edgesQueue.remove();

             /* EVENT */
            algorithmEventStorage.addEvent(new DequeueEdgeEvent(edge));

            final int v = edge.either();
            final int w = edge.other(v);

            if(marked[v] && marked[w]) {
                continue;
            }

            mst.add(edge);
            weight += edge.getWeight();

            /* EVENT */
            algorithmEventStorage.addEvent(new AddEdgeToMstEvent(edge));

            if(!marked[v]) {
                 /* EVENT */
                algorithmEventStorage.addEvent(new VertexPickedEvent(v));

                scan(G, v);
            }

            if(!marked[w]) {
                 /* EVENT */
                algorithmEventStorage.addEvent(new VertexPickedEvent(w));

                scan(G, w);
            }
        }
    }

    private void scan(final WeightedGraph G, final int v) {
        marked[v] = true;

        /* EVENT */
        algorithmEventStorage.addEvent(new VertexVisitedEvent(v));

        for(Edge edge : G.adj(v)) {
            if(!marked[edge.other(v)]) {
                edgesQueue.add(edge);

                /* EVENT */
                algorithmEventStorage.addEvent(new EnqueueEdgeEvent(edge));
            }
        }
    }
}
