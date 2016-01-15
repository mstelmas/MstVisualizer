package org.gis.mstvisualizer.Core.Simulation.Events.Queue;

import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEdgeEvent;
import org.gis.mstvisualizer.Core.Simulation.SimulationConstants;

public class EnqueueEdgeEvent extends AlgorithmEdgeEvent {

    public EnqueueEdgeEvent(final Link edge) {
        super(edge);
    }

    @Override
    public void onExecute() {
        getEdge().setColor(SimulationConstants.EDGE_ENQUEUE_COLOR);
    }

    @Override
    public String toString() {
        return super.toString() + "EnqueueEdgeEvent (Edge: " + getEdge() + ")\n";
    }

    @Override
    public String getDescription() {
        return "Odłozenie krawędzi: " + edge + " do kolejki";
    }
}
