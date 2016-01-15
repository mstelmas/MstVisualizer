package org.gis.mstvisualizer.Core;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Graph.Vertex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Utils {


    public static int graphMaxDegree(final Graph<Vertex, Link> G) {
        int max = 0;

        for (Vertex v : G.getVertices()) {
            if (G.degree(v) > max) {
                max = G.degree(v);
            }
        }
        return max;
    }

    public static double graphAvgDegree(final Graph<Vertex, Link> G) {
        return 2.0 * G.getEdgeCount() / G.getVertexCount();
    }

    /* TODO: Implement */
    public static int graphSelfLoopsCount(final Graph<Vertex, Link> G) {
        int selfLoopsCount = 0;
        for (Vertex v : G.getVertices()) {
            for (Link adjV : G.getIncidentEdges(v)) {
                /* ... */
            }
        }

        return selfLoopsCount;
    }

    public static Graph<Vertex, Link> loadGraphFromFile(final String file) throws FileNotFoundException, IOException {
        final Graph<Vertex, Link> g = new UndirectedSparseGraph<Vertex, Link>() {
        };
        final Map<Integer, Vertex> vertices = new HashMap<>();

        final BufferedReader br = new BufferedReader(new FileReader(file));

        for (String line; (line = br.readLine()) != null; ) {
            String[] s = line.split(" ");
            if (s.length == 3) {
                Integer i1 = Integer.parseInt(s[0]);
                Integer i2 = Integer.parseInt(s[1]);
                Double weight = Double.parseDouble(s[2]);
                if (!vertices.containsKey(i1)) {
                    Vertex v1 = new Vertex(i1);
                    vertices.put(i1, v1);
                    g.addVertex(v1);
                }
                if (!vertices.containsKey(i2)) {
                    Vertex v2 = new Vertex(i2);
                    vertices.put(i2, v2);
                    g.addVertex(v2);
                }

                final Vertex v1 = vertices.get(i1);
                final Vertex v2 = vertices.get(i2);

                g.addEdge(new Link(v1, v2, weight), vertices.get(i1), vertices.get(i2));
            }
        }

        return g;
    }

}
