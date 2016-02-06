package org.gis.mstvisualizer.Visualizer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;
import org.gis.mstvisualizer.Core.Algorithms.AlgorithmMST;
import org.gis.mstvisualizer.Core.ConnectedComponents;
import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Graph.Vertex;
import org.gis.mstvisualizer.Core.Simulation.EventManager;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEvent;
import org.gis.mstvisualizer.Core.Simulation.IEventManager;
import org.gis.mstvisualizer.Core.Simulation.SimulationConstants;
import org.gis.mstvisualizer.Core.Simulation.Storage.IAlgorithmEventStorage;
import org.gis.mstvisualizer.Core.Utils;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public final class GraphVisualizer {

    private final Graph G;

    private final IAlgorithmEventStorage algorithmEventStorage;

    private final IEventManager eventManager;

    private final AlgorithmMST algorithmMST;

    private final JFrame frame;

    private final JPanel graphPanel = new JPanel();

    /* visualizaton Control Panel */
    private final JPanel visualizationControlPanel = new JPanel();
    private final JButton firstStepButton = new JButton("<<");
    private final JButton leftStepButton = new JButton("<");
    private final JButton autoStepButton = new JButton("<AUTO>");
    private boolean paused = true;
    Timer timer = new Timer();
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
    private JLabel graphNotUniqueWarningLabel;


    private final JPanel mstInformationPanel = new JPanel(new GridLayout(0, 1));
    private final JLabel mstEdgesCountLabel;
    private final JLabel mstWeightLabel;
    private final JLabel mstAlgorithmNameLabel;
    private final JLabel mstStepsLabel;

    /* TODO Implement */
    //private final JLabel graphSelfLoopsCount;

    private final JPanel algorithmStatusPanel = new JPanel();
    private final JLabel algorithmStatusLabel = new JLabel("Krok: ");
    private final JLabel algorithmEventNameLabel;


    private final JPanel rightInformationPanel = new JPanel(new GridLayout(0, 1));




    private BasicVisualizationServer<Vertex, Link> vv;

    public GraphVisualizer(final JFrame frame, final Graph G, final IAlgorithmEventStorage algorithmEventStorage, final AlgorithmMST algorithmMST) {
        this.frame = frame;
        this.algorithmMST = algorithmMST;

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
            if(paused) {
                autoStepButton.setText("<PAUSE>");
                paused = false;
            }else{
                autoStepButton.setText("<AUTO>");
                paused = true;
            }
            autoStepButton.repaint();
        });
        timer.schedule(new TimerTask() {
            public void run() {
                if(!paused)
                    rightStepButton.doClick();
            }
        }, 0, 2*1000);

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


        final boolean isGraphUnique = Utils.checkGraphEdgesUniqueness(G);
        if(!isGraphUnique) {
            graphNotUniqueWarningLabel = new JLabel("UWAGA: Wagi krawędzi grafu nie są unikalne!");
        }


        graphInformationPanel.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED),
                "Graf"
        ));
        graphInformationPanel.add(graphVertexCountLabel);
        graphInformationPanel.add(graphEdgesCountLabel);
        graphInformationPanel.add(graphMaxDegreeLabel);
        graphInformationPanel.add(graphAvgDegreeLabel);
        graphInformationPanel.add(graphConnectedComponentsLabel);

        if(!isGraphUnique) {
            graphInformationPanel.add(graphNotUniqueWarningLabel);
        }


        algorithmEventNameLabel = new JLabel();
        algorithmStatusPanel.add(algorithmStatusLabel);
        algorithmStatusPanel.add(algorithmEventNameLabel);

        mstStepsLabel = new JLabel("Ilość kroków algorytmu: " + algorithmEventStorage.count());
        mstEdgesCountLabel = new JLabel("Ilość krawędzi: " + algorithmMST.count());
        mstWeightLabel = new JLabel("Całkowita waga: " + algorithmMST.getWeight());
        mstAlgorithmNameLabel = new JLabel("Algorytm: " + algorithmMST.getName());

        /* MST Information Panel */
        mstInformationPanel.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED),
                "MST"
        ));
        mstInformationPanel.add(mstAlgorithmNameLabel);
        mstInformationPanel.add(mstStepsLabel);
        mstInformationPanel.add(mstEdgesCountLabel);
        mstInformationPanel.add(mstWeightLabel);



        rightInformationPanel.add(algorithmStatusPanel);
        rightInformationPanel.add(mstInformationPanel);
        rightInformationPanel.add(graphInformationPanel);
    }

    public void run() {
        CircleLayout<Vertex, Link> layout = new CircleLayout<>(this.G);
        layout.setSize(new Dimension(500,500));

        Transformer<Vertex,Paint> vertexColorTransformer = Vertex::getColor;
        Transformer<Link, Paint> edgeColorTransformerPaint = Link::getColor;

        final Transformer<Link, String> edgeLabelTransformer = (edge) -> edge.getWeight().toString();
        final Transformer<Vertex, String> vertexLabelTransformer = (vertex) -> String.valueOf(vertex.getV());
        final Transformer<Link, Stroke> edgeStrokeTransformer = (edge) -> Optional.ofNullable(edge.getStroke()).orElse(SimulationConstants.BASIC_EDGE_STROKE);


        vv = new BasicVisualizationServer<>(layout);
        vv.getRenderContext().setVertexFillPaintTransformer(vertexColorTransformer);

        vv.getRenderContext().setVertexLabelTransformer(vertexLabelTransformer);
        vv.getRenderContext().setEdgeLabelTransformer(edgeLabelTransformer);


        vv.getRenderContext().setEdgeDrawPaintTransformer(edgeColorTransformerPaint);
        vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);

        graphPanel.add(vv);

        this.frame.getContentPane().removeAll();
        this.frame.getContentPane().setLayout(new BorderLayout());
        this.frame.getContentPane().add(graphPanel, BorderLayout.CENTER);
        this.frame.getContentPane().add(visualizationControlPanel, BorderLayout.PAGE_END);

        this.frame.getContentPane().add(algorithmStatusPanel, BorderLayout.PAGE_START);

        this.frame.getContentPane().add(rightInformationPanel, BorderLayout.EAST);

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
        Optional.ofNullable(algorithmEvent).ifPresent(event -> {
            algorithmEventNameLabel.setText(event.getDescription());
        });
    }


}
