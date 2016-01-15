package org.gis.mstvisualizer.Core.Simulation.Events.Queue;

import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEdgeEvent;
import org.gis.mstvisualizer.Core.Simulation.SimulationConstants;

public class DequeueEdgeEvent extends AlgorithmEdgeEvent {

    public DequeueEdgeEvent(final Link edge) {
        super(edge);
    }

    @Override
    public void onExecute() {
        getEdge().setColor(SimulationConstants.EDGE_DEQUEUE_COLOR);
    }

    @Override
    public String toString() {
        return super.toString() + "DequeueEdgeEvent (Edge: " + getEdge() + ")\n";
    }

    @Override
    public String getDescription() {

        final Link edge = getEdge();

        return "Pobranie krawędzi: " + edge + " z kolejki";
    }
}
