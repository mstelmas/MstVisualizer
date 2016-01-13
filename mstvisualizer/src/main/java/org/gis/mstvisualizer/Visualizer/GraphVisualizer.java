package org.gis.mstvisualizer.Visualizer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;
import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Graph.Vertex;
import org.gis.mstvisualizer.Core.Simulation.EventManager;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEvent;
import org.gis.mstvisualizer.Core.Simulation.IEventManager;
import org.gis.mstvisualizer.Core.Simulation.Storage.IAlgorithmEventStorage;

import javax.swing.*;
import java.awt.*;
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

        this.frame.pack();
        this.frame.setVisible(true);
    }

    private void updateGraph(final AlgorithmEvent algorithmEvent) {
        System.out.println(algorithmEvent);

        Optional.ofNullable(algorithmEvent).ifPresent(event -> {
                processAlgorithmEvent(event);
                vv.repaint();
        });
    }


    private void processAlgorithmEvent(final AlgorithmEvent algorithmEvent) {
//        this.eventNameLabel.setText(algorithmEvent.toString());
    }


}
