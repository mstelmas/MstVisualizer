package org.gis.mstvisualizer;


import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import org.gis.mstvisualizer.Core.Algorithms.AlgorithmMST;
import org.gis.mstvisualizer.Core.Algorithms.KruskalMST;
import org.gis.mstvisualizer.Core.Algorithms.PrimMST;
import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Visualizer.GraphVisualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class App extends JFrame
{
    public static void main( String[] args ) {
        JFrame frame = new JFrame("MST Visualizer");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Graph g = loadGraph();

        JButton bKruskal = new JButton("Visualize Kruskal Algorithm");
        bKruskal.setVerticalTextPosition(AbstractButton.CENTER);
        bKruskal.setHorizontalTextPosition(AbstractButton.LEADING);

        bKruskal.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Krusk rusza ");
                final AlgorithmMST algorithmMST = new KruskalMST(g);
                System.out.println("MST by " + algorithmMST.getName());
                System.out.println("Size: " + algorithmMST.getWeight());
                System.out.println("List of edges in MST: ");
                algorithmMST.links().forEach(link -> System.out.println(g.getEndpoints(link)));
                final GraphVisualizer graphVisualizer = new GraphVisualizer(frame, g, algorithmMST.getAlgorithmEventStorage());
                graphVisualizer.run();

//                g.addEdge(new Link(0.33,Color.GREEN), 2, 5);
            }
        });
        JButton bPrim = new JButton("Visualize Prim Algorithm");
        bPrim.setVerticalTextPosition(AbstractButton.CENTER);
        bPrim.setHorizontalTextPosition(AbstractButton.LEADING);

        bPrim.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Prim rusza ");

                final AlgorithmMST algorithmMST2 = new PrimMST(g);
                System.out.println("MST by " + algorithmMST2.getName());
                System.out.println("Size: " + algorithmMST2.getWeight());
                System.out.println("List of edges in MST: ");
                algorithmMST2.links().forEach(link -> System.out.println(g.getEndpoints(link)));

                final GraphVisualizer graphVisualizer = new GraphVisualizer(frame, g, algorithmMST2.getAlgorithmEventStorage());
                graphVisualizer.run();

//                g.addEdge(new Link(0.33,Color.RED), 2, 5);
            }
        });
        JPanel layout = new JPanel();
        layout.setLayout(new FlowLayout());
        layout.add(bKruskal);
        layout.add(bPrim);
        frame.getContentPane().add(layout, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private static Graph loadGraph(){
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

        return g;
    }
}
