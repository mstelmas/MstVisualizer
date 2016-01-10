package org.gis.mstvisualizer.Core.Algorithms;

import edu.uci.ics.jung.graph.Graph;
import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Graph.Vertex;
import org.gis.mstvisualizer.Core.Simulation.Events.Mst.AddEdgeToMstEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.Queue.DequeueEdgeEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.Queue.EnqueueEdgeEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.VertexPickedEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.VertexVisitedEvent;

import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

public class PrimMST extends AlgorithmMST {

    private final boolean[] marked;
    private PriorityQueue<Link> edgesQueue = new PriorityQueue<>();
    private Collection<Vertex> vertices;

    private Vertex getVertexById(final int id) {
        return vertices.stream()
                .filter(x -> x.getV() == id)
                .findAny()
                .orElse(null);
    }

    public PrimMST(final Graph<Vertex, Link> G) {
        super("Prim's Algorithm");
        marked = new boolean[G.getVertexCount()];
        this.vertices = G.getVertices();

        for (int i = 0; i < G.getVertexCount(); i++) {
            if(!marked[i]) {

                /* EVENT */
                algorithmEventStorage.addEvent(new VertexPickedEvent(getVertexById(i)));

                prim(G, i);
            }
        }
    }

    private void prim(final Graph G, final int s) {
        scan(G, s);

        while(!edgesQueue.isEmpty()) {
            final Link e = edgesQueue.poll();

             /* EVENT */
            algorithmEventStorage.addEvent(new DequeueEdgeEvent(e));

            final int v = ((Vertex)G.getEndpoints(e).getFirst()).getV();
            final int w = ((Vertex)G.getEndpoints(e).getSecond()).getV();

            if(marked[v] && marked[w]) {
                continue;
            }

            mst.add(e);
            weight += e.getWeight();

            /* EVENT */
            algorithmEventStorage.addEvent(new AddEdgeToMstEvent(e));

            if(!marked[v]) {
                 /* EVENT */
                algorithmEventStorage.addEvent(new VertexPickedEvent(getVertexById(v)));

                scan(G, v);
            }

            if(!marked[w]) {
                 /* EVENT */
                algorithmEventStorage.addEvent(new VertexPickedEvent(getVertexById(w)));

                scan(G, w);
            }
        }
    }

    private void scan(final Graph G, final int v) {
        marked[v] = true;

        /* EVENT */
        algorithmEventStorage.addEvent(new VertexVisitedEvent(getVertexById(v)));

        for(Object neighbor : G.getNeighbors(getVertexById(v))) {
            if(!marked[((Vertex)neighbor).getV()]) {
                Link e = (Link)G.findEdge(getVertexById(v), neighbor);
                edgesQueue.add(e);

                /* EVENT */
                algorithmEventStorage.addEvent(new EnqueueEdgeEvent(e));
            }
        }
    }
}
