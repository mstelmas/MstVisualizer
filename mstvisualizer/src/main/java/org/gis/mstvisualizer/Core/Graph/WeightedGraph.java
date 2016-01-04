package org.gis.mstvisualizer.Core.Graph;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class WeightedGraph {

    @Getter
    private final int V;

    @Getter
    private int E;

    private Set<Edge>[] edges;

    @SuppressWarnings("unchecked")
    public WeightedGraph(int V) {
        if(V < 0) {
            throw new IllegalArgumentException();
        }

        this.V = V;
        this.E = 0;

        edges = new Set[V];

        for (int i = 0; i < V; i++) {
            edges[i] = new HashSet<Edge>();
        }
    }

    public void addEdge(final Edge e) {
        final int v = e.either();
        final int w = e.other(v);

        validateVertex(v);
        validateVertex(w);

        edges[v].add(e);
        edges[w].add(e);

        E++;
    }

    public Iterable<Edge> adj(final int v) {
        validateVertex(v);

        return edges[v];
    }

    public Iterable<Edge> edges() {
        final Set<Edge> listOfEdges = new HashSet<Edge>();

        for(int v = 0; v < V; v++) {
            int loops = 0;
            for(Edge e : adj(v)) {
                if(e.other(v) > v) {
                    listOfEdges.add(e);
                } else if(e.other(v) == v) {
                    if(loops % 2 == 0) {
                        listOfEdges.add(e);
                    }
                    loops++;
                }
            }
        }
        return listOfEdges;
    }

    public int deg(final int v) {
        validateVertex(v);

        return edges[v].size();
    }

    private void validateVertex(final int v) {
        if(v < 0 || v >= V) {
            throw new IndexOutOfBoundsException();
        }
    }

}
