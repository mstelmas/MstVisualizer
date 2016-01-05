package org.gis.mstvisualizer;


import org.gis.mstvisualizer.Core.Algorithms.AlgorithmMST;
import org.gis.mstvisualizer.Core.Algorithms.KruskalMST;
import org.gis.mstvisualizer.Core.Algorithms.PrimMST;
import org.gis.mstvisualizer.Core.Graph.Edge;
import org.gis.mstvisualizer.Core.Graph.WeightedGraph;
import org.gis.mstvisualizer.Visualizer.GraphVisualizer;


public class App
{
    public static void main( String[] args ) {

        final WeightedGraph g = new WeightedGraph(8);

        g.addEdge(new Edge(4, 5, 0.35));
        g.addEdge(new Edge(4, 7, 0.37));
        g.addEdge(new Edge(5, 7, 0.28));
        g.addEdge(new Edge(0, 7, 0.16));
        g.addEdge(new Edge(1, 5, 0.32));
        g.addEdge(new Edge(0, 4, 0.38));
        g.addEdge(new Edge(2, 3, 0.17));
        g.addEdge(new Edge(1, 7, 0.19));
        g.addEdge(new Edge(0, 2, 0.26));
        g.addEdge(new Edge(1, 2, 0.36));
        g.addEdge(new Edge(1, 3, 0.29));
        g.addEdge(new Edge(2, 7, 0.34));
        g.addEdge(new Edge(6, 2, 0.40));
        g.addEdge(new Edge(3, 6, 0.52));
        g.addEdge(new Edge(6, 0, 0.58));
        g.addEdge(new Edge(6, 4, 0.93));

        final AlgorithmMST algorithmMST = new KruskalMST(g);

        System.out.println("MST by Kruskal: ");
        System.out.println("Size: " + algorithmMST.getWeight());
        System.out.println("List of edges in MST: ");

        algorithmMST.edges().forEach(System.out::println);


//        final AlgorithmMST algorithmMST2 = new PrimMST(g);
//
//        System.out.println("MST by Prim: ");
//        System.out.println("Size: " + algorithmMST2.getWeight());
//        System.out.println("List of edges in MST: ");
//
//        algorithmMST2.edges().forEach(System.out::println);

        final GraphVisualizer graphVisualizer = new GraphVisualizer(g, algorithmMST.getAlgorithmEventStorage());
        graphVisualizer.run();



    }
}
