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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class App extends JFrame {
    public static void main( String[] args ) {

        JFrame frame = new JFrame();

        String[] supportedAlgorithms = {
                "Kruskal Algorithm",
                "Prim Algorithm"
        };

        // TODO Possibility to choose a file
        Graph<Vertex, Link> g = loadGraphFromFile("graph.txt");

        JComboBox<String> algorithmList = new JComboBox<>(supportedAlgorithms);
        algorithmList.setLocation(0,100);
        algorithmList.setSize(200,20);
        algorithmList.setEditable(false);

        algorithmList.addActionListener(e -> {
            if (algorithmList.getSelectedItem().toString().toLowerCase().contains("kruskal")) {
                final AlgorithmMST algorithmMST = new KruskalMST(g);
                System.out.println("MST by " + algorithmMST.getName());
                System.out.println("Size: " + algorithmMST.getWeight());
                System.out.println("List of edges in MST: ");
                algorithmMST.links().forEach(link -> System.out.println(g.getEndpoints(link).getFirst() + " - " + g.getEndpoints(link).getSecond()));
                final GraphVisualizer graphVisualizer = new GraphVisualizer(frame, g, algorithmMST.getAlgorithmEventStorage());
                graphVisualizer.run();
            }else if(algorithmList.getSelectedItem().toString().toLowerCase().contains("prim")) {
                final AlgorithmMST algorithmMST2 = new PrimMST(g);
                System.out.println("MST by " + algorithmMST2.getName());
                System.out.println("Size: " + algorithmMST2.getWeight());
                System.out.println("List of edges in MST: ");
                algorithmMST2.links().forEach(link -> System.out.println(g.getEndpoints(link)));
                final GraphVisualizer graphVisualizer = new GraphVisualizer(frame, g, algorithmMST2.getAlgorithmEventStorage());
                graphVisualizer.run();
            }
        });

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
        frame.getContentPane().add(vv);
        frame.getContentPane().add(algorithmList,BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static Graph<Vertex, Link> loadGraphFromFile(String file){
        Graph<Vertex, Link> g = new UndirectedSparseGraph<Vertex, Link>(){};
        Map<Integer, Vertex> vertices = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {
                String[] s = line.split(" ");
                if(s.length == 3) {
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
                    g.addEdge(new Link(weight), vertices.get(i1), vertices.get(i2));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException!");
            e.printStackTrace();
        }
        return g;
    }
}
