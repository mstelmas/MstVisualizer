package org.gis.mstvisualizer;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Graph.Vertex;
import org.gis.mstvisualizer.Core.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AppTest
{
    @Before
    public void before() {

    }

    @After
    public void after() {

    }

    @Test
    public void shouldReturnTrueForUniqueWeightsInAGivenGraphTest() {
        final Graph<Vertex, Link> g = new UndirectedSparseGraph<>();

        final Vertex v0 = new Vertex(0);
        final Vertex v1 = new Vertex(1);
        final Vertex v2 = new Vertex(2);
        final Vertex v3 = new Vertex(3);
        final Vertex v4 = new Vertex(4);
        final Vertex v5 = new Vertex(5);
        final Vertex v6 = new Vertex(6);
        final Vertex v7 = new Vertex(7);

        g.addVertex(v0);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);

        g.addEdge(new Link(v0, v2, 0.26), v0, v2);
        g.addEdge(new Link(v0, v4, 0.38), v0, v4);
        g.addEdge(new Link(v0, v7, 0.16), v0, v7);
        g.addEdge(new Link(v1, v2, 0.36), v1, v2);
        g.addEdge(new Link(v1, v3, 0.29), v1, v3);
        g.addEdge(new Link(v1, v5, 0.32), v1, v5);
        g.addEdge(new Link(v1, v7, 0.19), v1, v7);
        g.addEdge(new Link(v2, v3, 0.17), v2, v3);
        g.addEdge(new Link(v2, v7, 0.34), v2, v7);
        g.addEdge(new Link(v3, v6, 0.52), v3, v6);
        g.addEdge(new Link(v4, v5, 0.35), v4, v5);
        g.addEdge(new Link(v4, v7, 0.37), v4, v7);
        g.addEdge(new Link(v5, v7, 0.28), v5, v7);
        g.addEdge(new Link(v6, v2, 0.40), v6, v2);
        g.addEdge(new Link(v6, v0, 0.58), v6, v0);
        g.addEdge(new Link(v6, v4, 0.93), v6, v4);
        g.addEdge(new Link(v6, v4, 0.32), v6, v4);

        assertTrue(Utils.checkGraphEdgesUniqueness(g));

        g.addEdge(new Link(v3, v4, 0.16), v3, v4);

        assertFalse(Utils.checkGraphEdgesUniqueness(g));

    }

}
