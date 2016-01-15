package org.gis.mstvisualizer.Visualizer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;
import org.gis.mstvisualizer.Core.ConnectedComponents;
import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Graph.Vertex;
import org.gis.mstvisualizer.Core.Simulation.EventManager;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEvent;
import org.gis.mstvisualizer.Core.Simulation.IEventManager;
import org.gis.mstvisualizer.Core.Simulation.Storage.IAlgorithmEventStorage;
import org.gis.mstvisualizer.Core.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.util.Optional;

public final class GraphVisualizer {

    private final Graph G;

    private final IAlgorithmEventStorage algorithmEventStorage;

    private final IEventManager eventManager;

    private final JFrame frame;

    private final JPanel graphPanel = new JPanel();

    /* visualizaton Control Panel */
    private final JPanel visualizationControlPanel = new JPanel();
    private final JButton firstStepButton = new JButton("<<");
    private final JButton leftStepButton = new JButton("<");
    private final JButton autoStepButton = new JButton("<AUTO>");
    private final JButton rightStepButton = new JButton(">");
    private final JButton lastStepButton = new JButton(">>");
    private final JLabel eventNameLabel = new JLabel();


    /* Basic Graph Information Panel */
    private final JPanel graphInformationPanel = new JPanel(new GridLayout(0, 1));
    private final JLabel graphVertexCountLabel;
    private final JLabel graphEdgesCountLabel;
    private final JLabel graphMaxDegreeLabel;
    private final JLabel graphAvgDegreeLabel;
    private final JLabel graphConnectedComponentsLabel;
    /* TODO Implement */
    //private final JLabel graphSelfLoopsCount;

    private final JPanel algorithmStatusPanel = new JPanel();
    private final JLabel algorithmStatusLabel = new JLabel("Krok: ");
    private final JLabel algorithmEventNameLabel;


    private final JPanel rightInformationPanel = new JPanel();



    /* Menu components */
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu fileMenu;
    private final JMenuItem exitMenuItem, openGraphMenuItem;


    private final JFileChooser fileChooser = new JFileChooser();

    private BasicVisualizationServer<Vertex, Link> vv;

    public GraphVisualizer(final JFrame frame, final Graph G, final IAlgorithmEventStorage algorithmEventStorage) {
        this.frame = frame;

        this.algorithmEventStorage = algorithmEventStorage;
        this.G = G;
        this.eventManager = new EventManager(algorithmEventStorage);
        this.eventManager.firstEvent();

        firstStepButton.addActionListener(event -> {
            this.eventManager.firstEvent();
            updateGraph(this.eventManager.getCurrentEvent());
        });

        leftStepButton.addActionListener(event -> {
            AlgorithmEvent algorithmEvent;
            algorithmEvent = this.eventManager.prevEvent();
            updateGraph(algorithmEvent);
        });

        autoStepButton.addActionListener(event -> {
            /* TODO */
        });

        rightStepButton.addActionListener(event -> {
            AlgorithmEvent algorithmEvent;
            algorithmEvent = this.eventManager.nextEvent();
            updateGraph(algorithmEvent);
        });

        lastStepButton.addActionListener(event -> {
            this.eventManager.lastEvent();
            updateGraph(this.eventManager.getCurrentEvent());
        });

        visualizationControlPanel.add(firstStepButton);
        visualizationControlPanel.add(leftStepButton);
        visualizationControlPanel.add(autoStepButton);
        visualizationControlPanel.add(rightStepButton);
        visualizationControlPanel.add(lastStepButton);
        visualizationControlPanel.add(eventNameLabel);


        graphEdgesCountLabel = new JLabel("Ilość krawędzi: " + G.getEdgeCount());
        graphVertexCountLabel = new JLabel("Ilość wierzchołków: " + G.getVertexCount());
        graphMaxDegreeLabel = new JLabel("Stopień grafu: " + Utils.graphMaxDegree(G));
        graphAvgDegreeLabel = new JLabel("Średni stopień grafu: " + Utils.graphAvgDegree(G));

        final ConnectedComponents connectedComponents = new ConnectedComponents(G);
        graphConnectedComponentsLabel = new JLabel("Ilość spójnych składowych: " + connectedComponents.getCount());


        graphInformationPanel.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED),
                "Graf"
        ));
        graphInformationPanel.add(graphVertexCountLabel);
        graphInformationPanel.add(graphEdgesCountLabel);
        graphInformationPanel.add(graphMaxDegreeLabel);
        graphInformationPanel.add(graphAvgDegreeLabel);
        graphInformationPanel.add(graphConnectedComponentsLabel);


        algorithmEventNameLabel = new JLabel();
        algorithmStatusPanel.add(algorithmStatusLabel);
        algorithmStatusPanel.add(algorithmEventNameLabel);

        rightInformationPanel.add(algorithmStatusPanel);
        rightInformationPanel.add(graphInformationPanel);




        fileMenu = new JMenu("Plik");

        openGraphMenuItem = new JMenuItem("Otwórz");
        openGraphMenuItem.addActionListener((action -> {
            final int returnValue = fileChooser.showOpenDialog(frame);

            if(returnValue == JFileChooser.APPROVE_OPTION) {
                final File file = fileChooser.getSelectedFile();

                /* TODO: file loading */
            }
        }));



        exitMenuItem = new JMenuItem("Zakończ");
        exitMenuItem.addActionListener((action -> {
            System.exit(0);
        }));

        fileMenu.add(openGraphMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);
    }

    public void run() {
        CircleLayout<Vertex, Link> layout = new CircleLayout<>(this.G);
        layout.setSize(new Dimension(500,500));

        Transformer<Vertex,Paint> vertexPaint = Vertex::getColor;
        Transformer<Link, Paint> edgePaint = Link::getColor;

        vv = new BasicVisualizationServer<>(layout);
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<>());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<>());
        vv.getRenderContext().setEdgeDrawPaintTransformer(edgePaint);
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);

        graphPanel.add(vv);

        this.frame.getContentPane().removeAll();
        this.frame.getContentPane().setLayout(new BorderLayout());
        this.frame.getContentPane().add(graphPanel, BorderLayout.CENTER);
        this.frame.getContentPane().add(visualizationControlPanel, BorderLayout.PAGE_END);

        this.frame.getContentPane().add(algorithmStatusPanel, BorderLayout.PAGE_START);

        this.frame.getContentPane().add(rightInformationPanel, BorderLayout.EAST);

        this.frame.setJMenuBar(menuBar);

        this.frame.pack();
        this.frame.setVisible(true);
    }

    private void updateGraph(final AlgorithmEvent algorithmEvent) {
        Optional.ofNullable(algorithmEvent).ifPresent(event -> {
                processAlgorithmEvent(event);
                vv.repaint();
                algorithmEventNameLabel.setText(algorithmEvent.getDescription());
        });
    }


    private void processAlgorithmEvent(final AlgorithmEvent algorithmEvent) {
//        this.eventNameLabel.setText(algorithmEvent.toString());
    }


}
