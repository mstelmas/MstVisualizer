package org.gis.mstvisualizer.Core.Simulation.Events;

import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Simulation.SimulationConstants;

public class EdgeVisitedEvent extends AlgorithmEdgeEvent {

    public EdgeVisitedEvent(final Link edge) {
        super(edge);
    }

    @Override
    public void onExecute() {
        edge.setColor(SimulationConstants.EDGE_VISITED_COLOR);
    }

    @Override
    public String toString() {
        return super.toString() + "EdgeVisitedEvent (Edge: " + edge + ")\n";
    }

    @Override
    public String getDescription() {
        return "Odwiedzenie krawędzi: " + getEdge();
    }
}
