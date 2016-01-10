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
import org.gis.mstvisualizer.Visualizer.GraphVisualizer;

import javax.swing.*;
import java.awt.*;


public class App extends JFrame
{
    public static void main( String[] args ) {
        JFrame frame = new JFrame("MST Visualizer");

        Graph<Integer, Link> g = new UndirectedSparseGraph<Integer, Link>(){};
        g.addVertex( 0 );
        g.addVertex( 1 );
        g.addVertex( 2 );
        g.addVertex( 3 );
        g.addVertex( 4 );
        g.addVertex( 5 );
        g.addVertex( 6 );
        g.addVertex( 7 );
        g.addEdge(new Link(0.26), 0, 2);
        g.addEdge(new Link(0.38), 0, 4);
        g.addEdge(new Link(0.16, Color.RED), 0, 7);
        g.addEdge(new Link(0.36), 1, 2);
        g.addEdge(new Link(0.29), 1, 3);
        g.addEdge(new Link(0.32), 1, 5);
        g.addEdge(new Link(0.19), 1, 7);
        g.addEdge(new Link(0.17), 2, 3);
        g.addEdge(new Link(0.34), 2, 7);
        g.addEdge(new Link(0.52), 3, 6);
        g.addEdge(new Link(0.35), 4, 5);
        g.addEdge(new Link(0.37), 4, 7);
        g.addEdge(new Link(0.28), 5, 7);
        g.addEdge(new Link(0.40), 6, 2);
        g.addEdge(new Link(0.58), 6, 0);
        g.addEdge(new Link(0.93), 6, 4);

        Transformer<Integer,Paint> vertexPaint = i -> Color.GREEN;

        Transformer<Link, Paint> edgePaint = Link::getColor;

        CircleLayout<Integer, Link> layout = new CircleLayout<>(g);
        layout.setSize(new Dimension(500,500));
        BasicVisualizationServer<Integer,Link> vv = new BasicVisualizationServer<>(layout);
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
        algorithmMST.links().forEach(link -> System.out.println(g.getEndpoints(link)));


        final AlgorithmMST algorithmMST2 = new PrimMST(g);
        System.out.println("MST by " + algorithmMST2.getName());
        System.out.println("Size: " + algorithmMST2.getWeight());
        System.out.println("List of edges in MST: ");
        algorithmMST2.links().forEach(link -> System.out.println(g.getEndpoints(link)));

        final GraphVisualizer graphVisualizer = new GraphVisualizer(g, algorithmMST2.getAlgorithmEventStorage());
        graphVisualizer.run();



    }
}
