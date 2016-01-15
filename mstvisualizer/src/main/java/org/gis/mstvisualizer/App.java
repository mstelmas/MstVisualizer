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
import org.gis.mstvisualizer.Core.Simulation.SimulationConstants;
import org.gis.mstvisualizer.Core.Utils;
import org.gis.mstvisualizer.Visualizer.GraphVisualizer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;


public class App extends JFrame {

    private static final String[] supportedAlgorithms = {
            "Kruskal Algorithm",
            "Prim Algorithm"
    };

    /* Menu components */
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu fileMenu;
    private final JMenuItem exitMenuItem, openGraphMenuItem;

    private final JFileChooser fileChooser = new JFileChooser();


    /* Algorithm Management Panel */
    private final JPanel leftInformationPanel = new JPanel();
    private final JComboBox<String> algorithmList = new JComboBox<>(supportedAlgorithms);

    private App() {

        fileMenu = new JMenu("Plik");

        openGraphMenuItem = new JMenuItem("Otwórz");
        openGraphMenuItem.addActionListener((action -> {
            final int returnValue = fileChooser.showOpenDialog(this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                final File file = fileChooser.getSelectedFile();

                /* TODO: file loading */
            }
        }));

        exitMenuItem = new JMenuItem("Zakończ");
        exitMenuItem.addActionListener((action -> System.exit(0)));

        fileMenu.add(openGraphMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        this.setJMenuBar(menuBar);

        Graph<Vertex, Link> graph = null;
        try {
            graph = Utils.loadGraphFromFile("graph.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Cound not find a specified file!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Graph<Vertex, Link> g = graph;

        algorithmList.setEditable(false);
        algorithmList.addActionListener(e -> {
            if (algorithmList.getSelectedItem().toString().toLowerCase().contains("kruskal")) {
                final AlgorithmMST algorithmMST = new KruskalMST(g);
                System.out.println("MST by " + algorithmMST.getName());
                System.out.println("Size: " + algorithmMST.getWeight());
                System.out.println("List of edges in MST: ");
                algorithmMST.links().forEach(link -> System.out.println(g.getEndpoints(link).getFirst() + " - " + g.getEndpoints(link).getSecond()));
                final GraphVisualizer graphVisualizer = new GraphVisualizer(this, g, algorithmMST.getAlgorithmEventStorage(), algorithmMST);
                graphVisualizer.run();
            } else if (algorithmList.getSelectedItem().toString().toLowerCase().contains("prim")) {
                final AlgorithmMST algorithmMST2 = new PrimMST(g);
                System.out.println("MST by " + algorithmMST2.getName());
                System.out.println("Size: " + algorithmMST2.getWeight());
                System.out.println("List of edges in MST: ");
                algorithmMST2.links().forEach(link -> System.out.println(g.getEndpoints(link)));
                final GraphVisualizer graphVisualizer = new GraphVisualizer(this, g, algorithmMST2.getAlgorithmEventStorage(), algorithmMST2);
                graphVisualizer.run();
            }
        });


        leftInformationPanel.setLayout(new BorderLayout());
        leftInformationPanel.add(algorithmList, BorderLayout.PAGE_START);

        Transformer<Vertex, Paint> vertexPaint = Vertex::getColor;
        Transformer<Link, String> edgeLabel = (edge) -> edge.getWeight().toString();

        Transformer<Link, Paint> edgePaint = Link::getColor;
        Transformer<Link, Stroke> edgeStroke = (edge) -> {
            return Optional.ofNullable(edge.getStroke()).orElse(SimulationConstants.BASIC_EDGE_STROKE);
        };

        CircleLayout<Vertex, Link> layout = new CircleLayout(new UndirectedSparseGraph<Vertex, Link>());
        layout.setSize(new Dimension(500, 500));
        BasicVisualizationServer<Vertex, Link> vv = new BasicVisualizationServer<>(layout);
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<>());
        vv.getRenderContext().setEdgeLabelTransformer(edgeLabel);
        vv.getRenderContext().setEdgeDrawPaintTransformer(edgePaint);
        vv.getRenderContext().setEdgeStrokeTransformer(edgeStroke);
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);


        this.getContentPane().add(vv);
        this.getContentPane().add(leftInformationPanel, BorderLayout.PAGE_END);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }

}