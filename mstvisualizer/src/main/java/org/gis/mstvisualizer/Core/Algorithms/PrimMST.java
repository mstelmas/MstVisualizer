package org.gis.mstvisualizer.Core.Algorithms;

import edu.uci.ics.jung.graph.Graph;
import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Simulation.Events.VertexPickedEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.VertexVisitedEvent;

import java.util.PriorityQueue;

public class PrimMST extends AlgorithmMST {

    private final boolean[] marked;
    private PriorityQueue<Link> edgesQueue = new PriorityQueue<>();

    public PrimMST(final Graph G) {
        super("Prim's Algorithm");
        marked = new boolean[G.getVertexCount()];

        for (int i = 0; i < G.getVertexCount(); i++) {
            if(!marked[i]) {

                /* EVENT */
                algorithmEventStorage.addEvent(new VertexPickedEvent(i));

                prim(G, i);
            }
        }
    }

    private void prim(final Graph G, final int s) {
        scan(G, s);

        while(!edgesQueue.isEmpty()) {
            final Link e = edgesQueue.poll();

             /* EVENT */
//            algorithmEventStorage.addEvent(new DequeueEdgeEvent(e));

            final int v = (int) G.getEndpoints(e).getFirst();
            final int w = (int) G.getEndpoints(e).getSecond();

            if(marked[v] && marked[w]) {
                continue;
            }

            mst.add(e);
            weight += e.getWeight();

            /* EVENT */
//            algorithmEventStorage.addEvent(new AddEdgeToMstEvent(e));

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

    private void scan(final Graph G, final int v) {
        marked[v] = true;

        /* EVENT */
        algorithmEventStorage.addEvent(new VertexVisitedEvent(v));

        for(Object neighbor : G.getNeighbors(v)) {
            if(!marked[(int)neighbor]) {
                edgesQueue.add((Link)G.findEdge(v, neighbor));

                /* EVENT */
//                algorithmEventStorage.addEvent(new EnqueueEdgeEvent(edge));
            }
        }
    }
}
