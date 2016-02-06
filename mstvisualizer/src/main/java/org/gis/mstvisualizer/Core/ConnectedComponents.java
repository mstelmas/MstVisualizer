package org.gis.mstvisualizer.Core;


import edu.uci.ics.jung.graph.Graph;
import lombok.Getter;
import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Graph.Vertex;


import java.util.Collection;

public class ConnectedComponents {
    private final boolean[] marked;
    private final int[] id;
    private final int[] size;

    @Getter
    private int count;

    private Collection<Vertex> vertices;

    public ConnectedComponents(final Graph<Vertex, Link> G) {
        marked = new boolean[G.getVertexCount()];
        id = new int[G.getVertexCount()];
        size = new int[G.getVertexCount()];
        vertices = G.getVertices();

        for (int i = 0; i < G.getVertexCount(); i++) {
            if(!marked[i]) {
                dfs(G, i);
                count++;
            }
        }
    }

    private void dfs(final Graph<Vertex, Link> G, final int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;

        for(Object neighbor : G.getNeighbors(getVertexById(v))) {
            final int w = ((Vertex)neighbor).getV();
            if(!marked[w]) {
                dfs(G, w);
            }
        }
    }

    private Vertex getVertexById(final int id) {
        return vertices.stream()
                .filter(x -> x.getV() == id)
                .findAny()
                .orElse(null);
    }
}
