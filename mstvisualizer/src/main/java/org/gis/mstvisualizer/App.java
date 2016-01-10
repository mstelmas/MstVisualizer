package org.gis.mstvisualizer;


import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;
import org.gis.mstvisualizer.Core.Algorithms.AlgorithmMST;
import org.gis.mstvisualizer.Core.Algorithms.KruskalMST;
import org.gis.mstvisualizer.Core.Algorithms.PrimMST;
import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Graph.Vertex;
import org.gis.mstvisualizer.Visualizer.GraphVisualizer;

import javax.swing.*;
import java.awt.*;


public class App extends JFrame {
    public static void main( String[] args ) {

        JFrame frame = new JFrame();

        Graph<Vertex, Link> g = new UndirectedSparseGraph<Vertex, Link>(){};

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

        g.addEdge(new Link(0.26), v0, v2);
        g.addEdge(new Link(0.38), v0, v4);
        g.addEdge(new Link(0.16), v0, v7);
        g.addEdge(new Link(0.36), v1, v2);
        g.addEdge(new Link(0.29), v1, v3);
        g.addEdge(new Link(0.32), v1, v5);
        g.addEdge(new Link(0.19), v1, v7);
        g.addEdge(new Link(0.17), v2, v3);
        g.addEdge(new Link(0.34), v2, v7);
        g.addEdge(new Link(0.52), v3, v6);
        g.addEdge(new Link(0.35), v4, v5);
        g.addEdge(new Link(0.37), v4, v7);
        g.addEdge(new Link(0.28), v5, v7);
        g.addEdge(new Link(0.40), v6, v2);
        g.addEdge(new Link(0.58), v6, v0);
        g.addEdge(new Link(0.93), v6, v4);

        Transformer<Vertex, Paint> vertexPaint = Vertex::getColor;

        Transformer<Link, Paint> edgePaint = Link::getColor;

        CircleLayout<Vertex, Link> layout = new CircleLayout<>(g);
        layout.setSize(new Dimension(500,500));
        BasicVisualizationServer<Vertex,Link> vv = new BasicVisualizationServer<>(layout);
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<>());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<>());
        vv.getRenderContext().setEdgeDrawPaintTransformer(edgePaint);
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);


        final AlgorithmMST algorithmMST = new KruskalMST(g);
        System.out.println("MST by " + algorithmMST.getName());
        System.out.println("Size: " + algorithmMST.getWeight());
        System.out.println("List of edges in MST: ");
        algorithmMST.links().forEach(link -> System.out.println(g.getEndpoints(link).getFirst() + " - " + g.getEndpoints(link).getSecond()));


        final AlgorithmMST algorithmMST2 = new PrimMST(g);
        System.out.println("MST by " + algorithmMST2.getName());
        System.out.println("Size: " + algorithmMST2.getWeight());
        System.out.println("List of edges in MST: ");
        algorithmMST2.links().forEach(link -> System.out.println(g.getEndpoints(link)));

        final GraphVisualizer graphVisualizer = new GraphVisualizer(frame, g, algorithmMST2.getAlgorithmEventStorage());
        graphVisualizer.run();
    }
}
