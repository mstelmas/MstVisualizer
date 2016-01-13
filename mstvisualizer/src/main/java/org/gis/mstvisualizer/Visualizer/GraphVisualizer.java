package org.gis.mstvisualizer.Visualizer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import org.apache.commons.collections15.Transformer;
import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Graph.Vertex;
import org.gis.mstvisualizer.Core.Simulation.EventManager;
import org.gis.mstvisualizer.Core.Simulation.Events.*;
import org.gis.mstvisualizer.Core.Simulation.Events.Mst.AddEdgeToMstEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.Queue.DequeueEdgeEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.Queue.DequeueVertexEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.Queue.EnqueueEdgeEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.Queue.EnqueueVertexEvent;
import org.gis.mstvisualizer.Core.Simulation.IEventManager;
import org.gis.mstvisualizer.Core.Simulation.SimulationConstants;
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
    private final JButton leftStepButton = new JButton("<");
    private final JButton rightStepButton = new JButton(">");
    private final JLabel eventNameLabel = new JLabel();

    private BasicVisualizationServer<Vertex, Link> vv;

    public GraphVisualizer(final JFrame frame, final Graph G, final IAlgorithmEventStorage algorithmEventStorage) {
        this.frame = frame;

        this.algorithmEventStorage = algorithmEventStorage;
        this.G = G;
        this.eventManager = new EventManager(algorithmEventStorage);

        this.eventManager.firstEvent();

        leftStepButton.addActionListener(event -> {

            /* TEMPORARY! used for skipping Queue events */
            AlgorithmEvent algorithmEvent;

            do {
                algorithmEvent = this.eventManager.prevEvent();
            } while(algorithmEvent instanceof DequeueEdgeEvent || algorithmEvent instanceof DequeueVertexEvent ||
                    algorithmEvent instanceof EnqueueEdgeEvent || algorithmEvent instanceof EnqueueVertexEvent);

            updateGraph(algorithmEvent);
        });

        rightStepButton.addActionListener(event -> {
            /* TEMPORARY! used for skipping Queue events */
            AlgorithmEvent algorithmEvent;

            do {
                algorithmEvent = this.eventManager.nextEvent();
            } while(algorithmEvent instanceof DequeueEdgeEvent || algorithmEvent instanceof DequeueVertexEvent ||
                    algorithmEvent instanceof EnqueueEdgeEvent || algorithmEvent instanceof EnqueueVertexEvent);

            updateGraph(algorithmEvent);
        });

        visualizationControlPanel.add(leftStepButton);
        visualizationControlPanel.add(rightStepButton);
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
        //vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);


        graphPanel.add(vv);

        this.frame.getContentPane().setLayout(new BorderLayout());
        this.frame.getContentPane().add(graphPanel, BorderLayout.CENTER);
        this.frame.getContentPane().add(visualizationControlPanel, BorderLayout.PAGE_END);

        this.frame.pack();
        this.frame.setVisible(true);
    }

    private void updateGraph(final AlgorithmEvent algorithmEvent) {

        Optional.ofNullable(algorithmEvent).ifPresent(event -> {
                processAlgorithmEvent(event);
                vv.repaint();
        });
    }


    private static void processAlgorithmEvent(final AlgorithmEvent algorithmEvent) {

        if(algorithmEvent instanceof EdgeVisitedEvent) {
            final Link edge = ((EdgeVisitedEvent)algorithmEvent).getEdge();
            edge.setColor(SimulationConstants.EDGE_VISITED_COLOR);
        } else if(algorithmEvent instanceof VertexPickedEvent) {
            final Vertex vertex = ((VertexPickedEvent)algorithmEvent).getVertex();
            vertex.setColor(SimulationConstants.VERTEX_PICKED_COLOR);
        } else if(algorithmEvent instanceof VertexVisitedEvent) {
            final Vertex vertex = ((VertexVisitedEvent)algorithmEvent).getVertex();
            vertex.setColor(SimulationConstants.VERTEX_VISITED_COLOR);
        } else if(algorithmEvent instanceof AddEdgeToMstEvent) {
            final Link edge = ((AddEdgeToMstEvent)algorithmEvent).getEdge();
            edge.setColor(SimulationConstants.EDGE_MST_COLOR);
        } else if(algorithmEvent instanceof ColorEdgeEvent) {
            final Link edge = ((ColorEdgeEvent)algorithmEvent).getEdge();
            edge.setColor(((ColorEdgeEvent)algorithmEvent).getColor());
        } else if(algorithmEvent instanceof ColorVertexEvent) {
            final Vertex vertex = ((ColorVertexEvent)algorithmEvent).getVertex();
            vertex.setColor(((ColorVertexEvent)algorithmEvent).getColor());
        }
    }


}
