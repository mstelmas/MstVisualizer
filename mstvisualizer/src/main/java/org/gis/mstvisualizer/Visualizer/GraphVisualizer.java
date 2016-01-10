package org.gis.mstvisualizer.Visualizer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import org.apache.commons.collections15.Transformer;
import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Simulation.EventManager;
import org.gis.mstvisualizer.Core.Simulation.IEventManager;
import org.gis.mstvisualizer.Core.Simulation.Storage.IAlgorithmEventStorage;

import javax.swing.*;
import java.awt.*;

public final class GraphVisualizer {

    private final Graph G;

    private final JFrame frame;

    private final IAlgorithmEventStorage algorithmEventStorage;

    private final IEventManager eventManager;

    public GraphVisualizer(final JFrame frame, final Graph G, final IAlgorithmEventStorage algorithmEventStorage) {
        this.frame = frame;
        this.algorithmEventStorage = algorithmEventStorage;
        this.G = G;
        this.eventManager = new EventManager(algorithmEventStorage);
    }

    public void run() {
        CircleLayout<Integer, Link> layout = new CircleLayout<>(this.G);
        layout.setSize(new Dimension(500,500));

        Transformer<Integer,Paint> vertexPaint = i -> Color.GREEN;
        Transformer<Link, Paint> edgePaint = Link::getColor;



        BasicVisualizationServer<Integer,Link> vv = new BasicVisualizationServer<>(layout);
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<>());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<>());
        vv.getRenderContext().setEdgeDrawPaintTransformer(edgePaint);
        //vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);

        this.frame.getContentPane().add(vv);
        this.frame.pack();
        this.frame.setVisible(true);


        /* For now, just print out all the algorithm events */

        System.out.println("Total number of events: " + algorithmEventStorage.count());
        //System.out.println(algorithmEventStorage);
    }
}
